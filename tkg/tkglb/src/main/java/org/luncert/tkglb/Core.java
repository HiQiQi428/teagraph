package org.luncert.tkglb;

import javax.annotation.PostConstruct;

import org.luncert.tkglb.util.MarshallingCodeCFactory;
import org.luncert.tkglb.cluster.DownHandler;
import org.luncert.tkglb.cluster.TaskDispatcher;
import org.luncert.tkglb.cluster.UpHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

@Component
public class Core {

    @Autowired
    private TaskDispatcher taskDispatcher;

    @Autowired
    private UpHandler upHandler;

    @Autowired
    private DownHandler downHandler;

    /**
     * upEngine面向DB节点,直接传输Task和Result
     * downEngine面向客户端驱动,基于http协议
     */
    private NettyEngine upEngine, downEngine;

    @PostConstruct
    public void init() {
        upEngine = new NettyEngine(8898,
            new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    // p.addLast(new LoggingHandler(LogLevel.INFO));
                    p.addLast(upHandler);
                }
            });
        upEngine.submit(taskDispatcher);
        downEngine = new NettyEngine(8899,
            new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new HttpResponseEncoder());
                    p.addLast(new HttpRequestDecoder());
                    p.addLast(new HttpObjectAggregator(10 * 1024 * 1024));
                    p.addLast(downHandler);
                }
            });
    }

    public void run() {
        new Thread(() -> {
            downEngine.run_forever();
        }).start();
        upEngine.run_forever();
    }

}