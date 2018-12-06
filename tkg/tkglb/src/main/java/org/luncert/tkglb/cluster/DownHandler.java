package org.luncert.tkglb.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * HTTP ChannelHandler
 * 异步响应客户端数据库操作请求
 */
@Component
public class DownHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Autowired
    private Processor processor;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        String query = req.content().toString(CharsetUtil.UTF_8);
        // 通过回调函数,异步响应请求
        processor.execute(query, (result) -> {
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