package org.luncert.teagraph.util;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.luncert.teagraph.util.MarshallingCodeCFactory;

public class NettyServer {

    int port;
    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    ServerBootstrap bootstrap;

    public NettyServer(int port, ChannelHandler...handler) {
        this.port = port;
        bossGroup = new NioEventLoopGroup(); // 线程组:处理网络事件(接受客户端连接)
        workerGroup = new NioEventLoopGroup(); // 线程组:进行网络通讯读写
    
        // 配置参数
        bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class) // 注册服务端channel
            .option(ChannelOption.SO_BACKLOG, 1024) // 设置日志
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    sc.pipeline().addLast(handler);
                }
            })
            .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    public void start() throws InterruptedException {
        ChannelFuture cf = bootstrap.bind(port).sync();
        cf.channel().closeFuture().sync(); // closeFuture:异步关闭,sync:同步等待操作完成
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}