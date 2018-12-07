package org.luncert.tkglb.cluster;

import org.luncert.tkglb.cluster.bean.DBNode;
import org.luncert.tkglb.cluster.bean.NodeStatus;
import org.luncert.tkglb.cluster.bean.Task;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 任务调度器,作为任务提交到netty的主事件组中
 */
@Component
public class TaskDispatcher implements Runnable {

    @Autowired
    private DBPool dbs;

    @Autowired
    private TaskQueue taskQueue;

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
                // 读任务
                if (task.getPiece().getPieceType().equals(PieceType.Read)) {
                    node = dbs.getReadyDBNode();
                    node.execute(task);
                }
                // 写任务,写操作有返回值
                // 所有节点都会执行一次写任务,但是执行结果应该是相同的
                // 所以第一个执行完的节点返回执行结果后,如果该写任务是任务组最后一个任务,TaskPool会认为任务组执行结束,这里的不合理设计总体上没什么影响
                else {
                    curTask = task;
                    for (DBNode tmp : dbs) {
                        if (!tmp.getStatus().equals(NodeStatus.Ready)) {
                            // 先等待节点ready,然后执行写任务
                            dbs.newAction(tmp.getId(), NodeStatus.Ready, (n) -> n.execute(curTask));
                        }
                        else tmp.execute(task);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}