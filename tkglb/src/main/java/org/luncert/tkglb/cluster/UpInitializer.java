package org.luncert.tkglb.cluster;

import org.luncert.tkglb.util.MarshallingCodeCFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

@Component
public class UpInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    UpHandler upHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
        p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
        // p.addLast(new LoggingHandler(LogLevel.INFO));
        p.addLast(upHandler);
    }

}