package org.luncert.tkglb.cluster;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.sf.json.JSONObject;

public class DBCluster {

    private class ServerHandler extends ChannelHandlerAdapter {
        
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelRegistered();
        }

        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelUnregistered();
        }

        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelActive();
        }

        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            ctx.fireChannelInactive();
        }

        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.fireChannelRead(msg);
        }
        
    }

    private EventLoopGroup bossGroup, workerGroup;
    private ChannelFuture cf;

    private DBCluster() {}

    private void init() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LoggingHandler(LogLevel.INFO));
                        p.addLast(new ReadTimeoutHandler(5));
                        p.addLast(new ServerHandler());
                    }

                });
        
        cf = bootstrap.bind(8899);
    }

    @Override
    public void finalize() {
        try {
            cf.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public JSONObject execute(String query) {
        return null;
    }

    public static DBCluster getInstance() {
        return null;
    }

    private static class DBClusterInner {
        static final DBCluster INSTANCE;
        static {
            INSTANCE = new DBCluster();
            try {
                INSTANCE.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}