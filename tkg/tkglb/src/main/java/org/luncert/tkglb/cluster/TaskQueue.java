package org.luncert.tkglb.cluster;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.luncert.tkglb.cluster.bean.Task;
import org.luncert.tkglb.cypher.CPiece;
import org.springframework.stereotype.Component;

/**
 * 任务队列
 */
@Component
public class TaskQueue {

    private static class TaskNode extends Task {

        TaskNode next;

        TaskNode(int taskId, int groupId, CPiece piece) {
            super(taskId, groupId, piece);
        }

        @Override
        public String toString() {
            return super.toString();
        }

    }

    TaskNode head, tail;
    int size;
    int gidCount;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    /**
     * 添加为CPiece数组创建任务组
     * @param pieces
     * @return 组ID
     */
    public int enQueue(List<CPiece> pieces) {
        TaskNode task;
        int limit = pieces.size();
        if (limit > 0) {
            synchronized(this) {
                for (int i = 0; i < limit; i++) {
                    task = new TaskNode(i, gidCount, pieces.get(i));
                    if (head != null)
                        tail = tail.next = task;
                    else // 创建第一个节点
                        head = tail = task;
                    size++;
                }
                notify();
                return gidCount++;
            }
        }
        throw new InvalidParameterException("empty list");
    }

    public Task deQueue() {
        synchronized (this) {
            if (size == 0)
                throw new NoSuchElementException("deQueue from empty queue");
    
                TaskNode task = head;
            if (size == 1)
                head = tail = null;
            else
                head = head.next;
            size--;
            return task;
        }
    }

    public void waitForTask() throws InterruptedException {
        synchronized(this) {
            if (size == 0)
                wait();
        }
    }

    /**
     * 从尾部开始遍历输出所有元素
     */
    public String toString() {
        StringBuilder builder = new StringBuilder().append('[');
        TaskNode task = head;
        while (task != null) {
            builder.append(task).append(',');
            task = task.next;
        }
        int i;
        return (i = builder.length()) > 1 ? builder.replace(i - 1, i, "]").toString() : "[]";
    }

    /**
     * 迭代取出所有元素,如果在迭代期间队列有增删操作,可能会出错
     */
    public Iterator<Task> iterator() { return new Itr(); }
    
    private class Itr implements Iterator<Task> {

        public boolean hasNext() { return size > 0; }

        public Task next() { return deQueue(); }

    }
    
}