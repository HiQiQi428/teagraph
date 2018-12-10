package org.luncert.tkglb;

import javax.annotation.PostConstruct;

import org.luncert.tkglb.cluster.DownInitializer;
import org.luncert.tkglb.cluster.TaskDispatcher;
import org.luncert.tkglb.cluster.UpInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Core {

    @Autowired
    private TaskDispatcher taskDispatcher;

    @Autowired
    private UpInitializer upInitializer;

    @Autowired
    private DownInitializer downInitializer;

    /**
     * upEngine面向DB节点,直接传输Task和Result
     * downEngine面向客户端驱动,基于http协议
     */
    private NettyEngine upEngine, downEngine;

    @PostConstruct
    public void init() {
        upEngine = new NettyEngine(8898, upInitializer);
        downEngine = new NettyEngine(8899, downInitializer);
        upEngine.submit(taskDispatcher);
    }

    public void run() {
        new Thread(() -> {
            downEngine.run_forever();
        }).start();
        upEngine.run_forever();
    }

}