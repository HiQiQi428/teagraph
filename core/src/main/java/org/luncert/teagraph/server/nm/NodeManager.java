package org.luncert.teagraph.server.nm;

import java.util.ArrayList;
import java.util.List;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import org.luncert.teagraph.server.nm.NMMessage.Command;
import org.luncert.teagraph.util.NettyServer;

public class NodeManager {

    private class NMHandler extends ChannelHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            nodeList.add(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            NMMessage nmsg = (NMMessage) msg;
            Command cmd = nmsg.getCmd();
            if (cmd.equals(Command.Register)) {
                nodeList.add(ctx);
            }
            else if (cmd.equals(Command.Logoff)) {
                nodeList.remove(ctx);
            }
            ReferenceCountUtil.release(msg);
        }
    
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
            nodeList.remove(ctx);
        }
    
    }

    NettyServer nodeServer;

    List<ChannelHandlerContext> nodeList = new ArrayList<>();

    public NodeManager(int port) throws Exception {
        nodeServer = new NettyServer(port, new NMHandler());
        nodeServer.start();
    }

}