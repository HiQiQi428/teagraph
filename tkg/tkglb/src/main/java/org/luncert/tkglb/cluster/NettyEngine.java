package org.luncert.tkglb.cluster;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;

public class NettyEngine {

    private EventLoopGroup bossGroup, workerGroup;
    private ServerBootstrap bootstrap;

    public NettyEngine(int port, ChannelHandler[] handlers) {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        p.addLast(new LoggingHandler(LogLevel.INFO));
                        for (ChannelHandler handler : handlers)
                            p.addLast(handler);
                    }

                });
    }

    public void run_forever() {
        try {
            bootstrap.bind(8899).channel().close().sync();
        } catch (InterruptedException e) {
            clear();
        }
    }

    /**
     * 提交任务到netty主事件循环组执行
     * @param task
     * @return Future
     */
    public Future<?> submit(Runnable task) {
        return bossGroup.submit(task);
    }

    /**
     * 释放netty资源
     * @throws InterruptedException
     */
    private void clear() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}