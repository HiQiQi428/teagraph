package org.luncert.tkgdb.transport;

import java.net.URI;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class MessageServer {
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 1;

    private RouteTree<Class<? extends MessageHandler>> routeTree = new RouteTree<>();
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5));

    private class ServerHandler extends SimpleChannelInboundHandler<Message> {

        @Override
        protected void messageReceived(ChannelHandlerContext ctx, Message msg) throws Exception {
            URI uri = new URI(msg.uri());
            Class<? extends MessageHandler> handlerClass = routeTree.match(uri.getPath());
            if (handlerClass != null) {
                MessageHandler handler = handlerClass.newInstance();
                handler.setMessage(msg);
                handler.setContext(ctx);
                threadPoolExecutor.submit(handler);
            }
        }

    }

    public void addHandler(String path, Class<? extends MessageHandler> handlerClass) {
        routeTree.add(path, handlerClass);
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        pipeline.addLast(new ReadTimeoutHandler(5));
                        pipeline.addLast(new ServerHandler());
                    }
            });
        try {
            ChannelFuture ChannelFuture = bootstrap.bind(10).sync();
            ChannelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}