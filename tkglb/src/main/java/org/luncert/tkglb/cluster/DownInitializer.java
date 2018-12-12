package org.luncert.tkglb.cluster;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

@Component
public class DownInitializer
        extends ChannelInitializer<SocketChannel>
        implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpResponseEncoder());
        p.addLast(new HttpRequestDecoder());
        p.addLast(new HttpObjectAggregator(10 * 1024 * 1024));
        p.addLast(ctx.getBean(DownHandler.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ctx == null)
            ctx = applicationContext;
	}

}