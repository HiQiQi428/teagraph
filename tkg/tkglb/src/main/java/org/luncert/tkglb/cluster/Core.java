package org.luncert.tkglb.cluster;

import java.util.function.Consumer;

import org.luncert.tkglb.cypher.CypherAnalyser;
import org.luncert.tkglb.cypher.CPiece.PieceType;

import io.netty.channel.ChannelHandler;

public class Core {

    private DBPool dbs = new DBPool();
    private TaskQueue taskQueue = new TaskQueue();
    private TaskPool taskPool = new TaskPool();
    private NettyEngine nettyEngine;
    private Task curTask;

    private Core() {
        Handler1 clientHandler = new Handler1();
        Handler2 dbHandler = new Handler2(dbs, taskPool);
        nettyEngine = new NettyEngine(8899,
                            new ChannelHandler[]{clientHandler, dbHandler});
        nettyEngine.submit(() -> {
            try {
                dispatchTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void run() {
        nettyEngine.run_forever();
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
            // 读任务
            if (task.getPiece().getPieceType() == PieceType.Read) {
                taskPool.addWaitingTask(task);
                node = dbs.getReadyDBNode();
                node.execute(task);
            }
            // 写任务
            else {
                curTask = task;
                for (DBNode tmp : dbs) {
                    if (!tmp.getStatus().equals(NodeStatus.Ready)) {
                        // 先等待节点ready,然后执行写任务
                        // 这里会添加很多个相同的写任务到任务组中!
                        taskPool.addWaitingTask(task);
                        dbs.newAction(tmp.getChannel(), NodeStatus.Ready, (n) -> n.execute(curTask));
                    }
                    else {
                        tmp.execute(task);
                    }
                    // 再等待写任务执行完
                    dbs.newAction(tmp.getChannel(), NodeStatus.Ready, (n) -> {});
                }
                // 等待所有动作完成
                dbs.waitAllActionDone();
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