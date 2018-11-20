package org.luncert.tkglb.server;

import org.luncert.tkglb.cluster.DBCluster;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import net.sf.json.JSONObject;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static DBCluster cluster = DBCluster.getInstance();

    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        String query = new String(req.content().array());
        JSONObject result = cluster.execute(query);
        HttpResponse rep;
        if (result != null) {
            ByteBuf content = Unpooled.copiedBuffer(result.toString().getBytes());
            rep = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, content);
        }
        else {
            rep = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
        ctx.writeAndFlush(rep);
    }

}