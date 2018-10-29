package org.luncert.teagraph;

import org.neo4j.graphdb.Result;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MessageHandler extends ChannelHandlerAdapter {
	
    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String stmt = (String) msg;
		Result result = DBProxy.getInstance().handle(stmt);
		ctx.writeAndFlush(result);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
    }
    
}