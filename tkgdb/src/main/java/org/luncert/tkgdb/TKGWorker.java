package org.luncert.tkgdb;

import org.luncert.tkgdb.bean.CPiece;
import org.luncert.tkgdb.bean.Dict;
import org.luncert.tkgdb.util.LoadAvgUtil;
import org.luncert.tkgdb.util.MarshallingCodeCFactory;

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

    private String host;
    private int port;

    public TKGWorker(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private class ClientHandler extends ChannelInboundHandlerAdapter {

        private final GraphDB graphDB = GraphDB.getInstance();

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            Dict task = Dict.fromJSONString((String) msg);
            String query = task.getAttr("pieces", CPiece.class).getContent();
            Dict rep = new Dict();
            rep.addAttr("taskId", task.getAttr("taskId"));
            rep.addAttr("groupId", task.getAttr("groupId"));
            rep.addAttr("loadAvg", LoadAvgUtil.refresh().getLoadAvg15());
            graphDB.execute(query, (result) -> {
                rep.addAttr("result", result == null ? null : result.resultAsString());
                ctx.writeAndFlush(rep.toJSONString());
            });
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
