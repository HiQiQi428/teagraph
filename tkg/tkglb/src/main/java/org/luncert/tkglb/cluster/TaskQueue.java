package org.luncert.tkglb.cluster;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.luncert.tkglb.cypher.CPiece;

public class TaskQueue {

    private static class TaskNode extends Task {

        TaskNode next;

        TaskNode(int taskId, int groupId, CPiece piece) {
            super(taskId, groupId, piece);
        }

    }

    TaskNode head, tail;
    int size;
    int gidCount;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    private void enQueue(TaskNode task) {
        synchronized (this) {
            if (head != null)
                tail = tail.next = task;
            else // 创建第一个节点
                head = tail = task;
            size++;
            notify();
        }
    }

    public int enQueue(List<CPiece> pieces) {
        TaskNode task;
        for (int i = 0, limit = pieces.size(); i < limit; i++) {
            task = new TaskNode(i, gidCount, pieces.get(i));
            enQueue(task);
        }
        return gidCount++;
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
     * 迭代取出所有元素
     */
    public Iterator<Task> iterator() { return new Itr(); }
    
    private class Itr implements Iterator<Task> {

        public boolean hasNext() { return size > 0; }

        public Task next() { return deQueue(); }

    }
    
}