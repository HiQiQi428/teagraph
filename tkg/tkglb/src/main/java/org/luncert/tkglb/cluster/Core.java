package org.luncert.tkglb.cluster;

import java.util.function.Consumer;

import org.luncert.mullog.Mullog;
import org.luncert.tkglb.cypher.CypherAnalyser;
import org.luncert.tkglb.cypher.CPiece.PieceType;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Core {

    private static Mullog mullog = new Mullog();

    private DBPool dbs = new DBPool();
    private TaskQueue taskQueue = new TaskQueue();
    private TaskPool taskPool = new TaskPool();

    /**
     * upEngine面向DB节点,直接传输Task和Result
     * downEngine面向客户端驱动,基于http协议
     */
    private NettyEngine upEngine, downEngine;
    private Task curTask;

    private Core() {
        upEngine = new NettyEngine(8898,
            new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    p.addLast(new LoggingHandler(LogLevel.INFO));
                    p.addLast(new Handler1(dbs, taskPool));
                }
            });
        upEngine.submit(() -> {
            try {
                dispatchTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        downEngine = new NettyEngine(8899,
            new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new HttpResponseEncoder());
                    p.addLast(new HttpRequestDecoder());
                    p.addLast(new HttpObjectAggregator(10*1024*1024));
                    p.addLast(new Handler2());
                }
            });
    }

    public void run() {
        new Thread(() -> {
            downEngine.run_forever();
        }).start();
        upEngine.run_forever();
    }

    /**
     * 任务调度
     * @throws InterruptedException
     */
    private void dispatchTask() throws InterruptedException {
        Task task;
        DBNode node;
        while (true) {
            if (taskQueue.size() == 0)
                taskQueue.waitForTask();
            task = taskQueue.deQueue();
            mullog.info("handle task: " + task);
            taskPool.addWaitingTask(task);
            // 读任务
            if (task.getPiece().getPieceType() == PieceType.Read) {
                taskPool.addWaitingTask(task);
                node = dbs.getReadyDBNode();
                mullog.info("selec DB node: " + node.getId());
                node.execute(task);
            }
            // 写任务,写操作有返回值
            else {
                curTask = task;
                for (DBNode tmp : dbs) {
                    if (!tmp.getStatus().equals(NodeStatus.Ready)) {
                        // 先等待节点ready,然后执行写任务
                        dbs.newAction(tmp.getChannel(), NodeStatus.Ready, (n) -> n.execute(curTask));
                    }
                    else {
                        tmp.execute(task);
                    }
                    // 再等待写任务执行完
                    dbs.newAction(tmp.getChannel(), NodeStatus.Ready, (n) -> {});
                }
            }
        }
    }

    /**
     * 异步操作,仅添加新任务到任务队列
     * 
     * @param query
     * @return 执行id,和query绑定.通过id可以从ResultPool中获取执行结果
     * @throws Exception cypher解析异常
     */
    public void execute(String query, Consumer<String> callback) throws Exception {
        int gid = taskQueue.enQueue(CypherAnalyser.analyse(query));
        taskPool.newGroup(gid, callback);
    }

    private static class CoreInner {
        static final Core INSTANCE = new Core();
    }

    public static Core getInstance() {
        return CoreInner.INSTANCE;
    }

}