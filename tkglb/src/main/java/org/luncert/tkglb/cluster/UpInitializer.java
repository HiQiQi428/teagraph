package org.luncert.tkglb.cluster;

import org.luncert.tkglb.util.MarshallingCodeCFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

@Component
public class UpInitializer
        extends ChannelInitializer<SocketChannel>
        implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
        p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        // p.addLast(new LoggingHandler(LogLevel.INFO));
        
        /*
        每次开始一个新的channel，都需要一个新的
        UpHandler，不然netty会报错，所以UpHandler
        本身也是多例模式的。
        */
        p.addLast(ctx.getBean(UpHandler.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(ctx == null) {
            ctx = applicationContext;
        }
	}

}