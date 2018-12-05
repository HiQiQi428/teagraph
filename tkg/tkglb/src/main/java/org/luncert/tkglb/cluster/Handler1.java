package org.luncert.tkglb.cluster;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

/**
 * HTTP ChannelHandler
 * 异步响应客户端数据库操作请求
 */
public class Handler1 extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static Core cluster = Core.getInstance();

    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        String query = new String(req.content().array());
        // 通过回调函数,异步响应请求
        cluster.execute(query, (result) -> {
            HttpResponse rep;
            if (result != null) {
                ByteBuf content = Unpooled.copiedBuffer(result.toString().getBytes());
                rep = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, content);
            }
            else {
                rep = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.INTERNAL_SERVER_ERROR);
            }
            ctx.writeAndFlush(rep);
        });
    }

}