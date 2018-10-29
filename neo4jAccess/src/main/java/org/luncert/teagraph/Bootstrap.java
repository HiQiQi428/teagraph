package org.luncert.teagraph;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class Bootstrap {

    public static void main(String[] argv) throws InterruptedException {

        EventLoopGroup pGroup = new NioEventLoopGroup(); // 线程组:处理网络事件(接受客户端连接)
        EventLoopGroup cGroup = new NioEventLoopGroup(); // 线程组:进行网络通讯读写
    
        // 配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(pGroup, cGroup)
            .channel(NioServerSocketChannel.class) // 注册服务端channel
            .option(ChannelOption.SO_BACKLOG, 1024) // 设置日志
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel sc) throws Exception {
                    sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    sc.pipeline().addLast(new ReadTimeoutHandler(5)); // 5s没有交互就关闭channel
                    sc.pipeline().addLast(new MessageHandler());
                }
            });
        ChannelFuture cf = bootstrap.bind(8765).sync();
        cf.channel().closeFuture().sync(); // closeFuture:异步关闭,sync:同步等待操作完成
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();
    }

}