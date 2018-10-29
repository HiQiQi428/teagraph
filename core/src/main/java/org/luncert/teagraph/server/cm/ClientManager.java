package org.luncert.teagraph.server.cm;

import org.luncert.teagraph.util.NettyServer;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientManager {

    private class CMHandler extends ChannelHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String cypher = (String)msg;
            ReferenceCountUtil.release(msg);
        }
    
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    
    }

    NettyServer clientServer;

    public ClientManager(int port) throws Exception {
        clientServer = new NettyServer(port, new CMHandler());
        clientServer.start();
    }

}