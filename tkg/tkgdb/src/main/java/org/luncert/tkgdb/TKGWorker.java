package org.luncert.tkgdb;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TKGWorker {

    private static String host = "127.0.0.1";
    private static int port = 8899;

    private class ClientHandler extends SimpleChannelInboundHandler<Task> {

        @Override
        protected void messageReceived(ChannelHandlerContext ctx, Task task) throws Exception {
            System.out.println(task);
            TaskResult result = new TaskResult(task, task.getPiece().getContent());
            ctx.writeAndFlush(result);
        }

    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
            .handler(new LoggingHandler(LogLevel.INFO))
            .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        pipeline.addLast(new ClientHandler());
                    }
            });
        Channel channel = bootstrap.connect(host, port).sync().channel();
        channel.closeFuture().sync();
    }

}