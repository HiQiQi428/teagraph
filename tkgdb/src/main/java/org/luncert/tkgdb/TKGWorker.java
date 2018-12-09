package org.luncert.tkgdb;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class TKGWorker {

    private static String host = "127.0.0.1";
    private static int port = 8898;

    private class ClientHandler extends ChannelInboundHandlerAdapter {

        private final GraphDatabaseService graphDB = GraphDB.getInstance();
        
        private Result execute(String query) {
            Result result = null;
            Transaction tx = graphDB.beginTx();
            try {
                result = graphDB.execute(query);
                tx.success();
            } catch (Exception e) {
                tx.failure();
            } finally {
                tx.close();
            }
            return result;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Task task = Task.fromJSONString((String) msg);
            Result execResult = execute(task.getPiece().getContent());
            TaskResult result = new TaskResult(task.getTaskId(), task.getGroupId(),
                        execResult == null ? null : execResult.resultAsString());
            ctx.writeAndFlush(result.toJSONString());
        }

    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {

                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        pipeline.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast(new ClientHandler());
                    }

            });
        Channel channel = bootstrap.connect(host, port).sync().channel();
        channel.closeFuture().sync();
    }

}
