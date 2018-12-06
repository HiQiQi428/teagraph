package org.luncert.tkglb.cluster;

import org.luncert.tkglb.cluster.bean.DBNode;
import org.luncert.tkglb.cluster.bean.NodeStatus;
import org.luncert.tkglb.cluster.bean.Task;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDispatcher implements Runnable {

    @Autowired
    private DBPool dbs;

    @Autowired
    private TaskQueue taskQueue;

    @Autowired
    private TaskPool taskPool;

    private Task curTask;

    @Override
    public void run() {
        Task task;
        DBNode node;
        try {
            while (true) {
                if (taskQueue.size() == 0)
                    taskQueue.waitForTask();
                task = taskQueue.deQueue();
                taskPool.addWaitingTask(task);
                // 读任务
                if (task.getPiece().getPieceType() == PieceType.Read) {
                    taskPool.addWaitingTask(task);
                    node = dbs.getReadyDBNode();
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
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}