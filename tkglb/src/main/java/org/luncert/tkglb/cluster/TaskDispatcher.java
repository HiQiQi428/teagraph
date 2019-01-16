package org.luncert.tkglb.cluster;

import org.luncert.tkglb.cluster.bean.DBNode;
import org.luncert.tkglb.cluster.bean.NodeStatus;
import org.luncert.tkglb.cluster.bean.Task;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 任务分发器，在这里实现负载均衡负责从TaskQueue
 * 获取任务，如果是读任务，则选择一个数据库节点读，
 * 否则选择所有节点执行任务。当对整个数据库进行一
 * 次写时，TaskDispatcher并不会等待节点完成操作
 * ，仅仅分发任务。
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
                // 是读任务
                if (task.getPiece().getPieceType().equals(PieceType.Read)) {
                    // 从最小堆中获取一个读次数最少的节点
                    node = dbs.getReadyDBNode();
                    node.execute(task);
                }
                /*
                是写任务，写操作有返回值。所有节点都会执行一次写任务,但是执行结果
                应该是相同的。所以第一个执行完的节点返回执行结果后,如果该写任务
                是任务组最后一个任务，TaskPool会认为任务组执行结束,这里的不合
                理设计总体上没什么影响
                */
                else {
                    curTask = task;
                    for (DBNode tmp : dbs) {
                        if (!tmp.getStatus().equals(NodeStatus.Ready)) {
                            // 在DBNode上添加新的action，等待节点转变为ready状态,然后执行写任务
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