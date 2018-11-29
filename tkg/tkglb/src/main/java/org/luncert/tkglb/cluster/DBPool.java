package org.luncert.tkglb.cluster;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

import org.luncert.tkglb.cluster.DBNode.NodeStatus;

import io.netty.channel.Channel;

/**
 * 最小堆存储DBNode
 */
public class DBPool implements Iterable<DBNode> {

    private final static int DEFAULT_OPACITY = 16;
    private ExtDBNode[] nodes;
    private int size;
    Map<Channel, Integer> index = new HashMap<>();

    /**
     * action和一个status绑定,当node状态变为status时,action被触发
     */
    private static class Action<E> {
        NodeStatus waitingStatus;
        E arg;
        BiConsumer<DBNode, E> callback;
        Action<Object> next;
        Action(NodeStatus waitingStatus, E arg, BiConsumer<DBNode, E> callback) {
            this.waitingStatus = waitingStatus;
            this.arg = arg;
            this.callback = callback;
        }
        void execute(DBNode node) {
            callback.accept(node, arg);
        }
    }

    public class ExtDBNode extends DBNode {
        DBNode node;
        Action<Object> firstAction, lastAction;

        private ExtDBNode(Channel channel) {
            super (channel);
        }

        public void execute(Task task) {
            super.execute(task);
            // node的readTime增加了,需要下滤
            int i = index.get(node.getChannel());
            DBPool.this.percolateDown(i);
        }

        @SuppressWarnings("unchecked")
        public <E>void addAction(NodeStatus status, E arg, BiConsumer<DBNode, E> callback) {
            if (getStatus() == status)
                callback.accept(this, arg);
            else {
                Action<Object> newAction = (Action<Object>) new Action<E>(status, arg, callback);
                if (firstAction == null)
                    lastAction = firstAction = newAction;
                else
                    lastAction = lastAction.next = newAction;
            }
        }

        /**
         * 状态改变,触发action
         */
        public void changeStatus(NodeStatus status) {
            super.changeStatus(status);
            if (firstAction != null && firstAction.waitingStatus == status) {
                firstAction.execute(this);
                firstAction = firstAction.next;
                if (firstAction == null)
                    firstAction = lastAction = null;
            }
        }
    }

    public DBPool() {
        nodes = new ExtDBNode[DEFAULT_OPACITY];
    }

    /**
     * 调整堆数组容量
     */
    private void adjustCapacity() {
        if (size + 1 == nodes.length) {
            ExtDBNode[] tmp = new ExtDBNode[nodes.length + nodes.length >>> 1];
            System.arraycopy(nodes, 0, tmp, 0, size);
            nodes = tmp;
        }
    }

    /**
     * 比较父子节点,通过交换使堆符合最小堆性质
     * @param p 父亲节点索引
     * @param c 子节点索引
     * @return 是否进行了交换
     */
    private boolean percolatePart(int p, int c) {
        ExtDBNode parent = nodes[p], child = nodes[c];
        if (parent.getReadTime() > child.getReadTime()) {
            nodes[p] = child;
            index.put(child.getChannel(), p);
            nodes[c] = parent;
            index.put(parent.getChannel(), c);
            return true;
        }
        return false;
    }

    /**
     * @param i 需要下滤的节点的索引
     */
    private void percolateDown(int i) {
        int leftChild = i * 2 + 1, rightChild = leftChild + 1;
        if (leftChild < size && percolatePart(i, leftChild))
            percolateDown(leftChild);
        if (rightChild < size && percolatePart(i, rightChild))
            percolateDown(rightChild);
    }

    /**
     * @param i 需要上滤的节点的索引
     */
    private void percolateUp(int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && percolatePart(parent, i)) {
            i = parent;
            parent = (i - 2) / 2;
        }
    }

    /**
     * 创建新节点绑定channel,将新节点添加到堆数组末尾,然后执行上滤
     */
    public void newDBNode(Channel channel) {
        adjustCapacity();
        ExtDBNode node = new ExtDBNode(channel);
        nodes[size] = node;
        index.put(channel, size);
        percolateUp(size);
        size++;
    }

    public DBNode getDBNode(Channel channel) {
        Integer i = index.get(channel);
        return (i != null) ? nodes[i] : null;
    }

    public boolean contians(Channel channel) {
        return index.containsKey(channel);
    }

    private DBNode delete(int i) {
        DBNode node = nodes[i];
        nodes[i] = nodes[--size];
        nodes[size] = null;
        percolateDown(i);
        return node;
    }

    public DBNode delete(Channel channel) {
        Integer i = index.get(channel);
        DBNode node = null;
        if (i != null) {
            node = delete(i);
            index.remove(channel);
        }
        return node;
    }

    public DBNode getReadyDBNode() {
        DBNode node = null;
        for (int i = 0; i < size; i++) {
            if ((node = nodes[i]).getStatus() == NodeStatus.Ready)
                return node;
        }
        return node;
    }

    private void sizeCheck() {
        if (size == 0)
            throw new UnsupportedOperationException("operation on empty heap");
    }

    public int size() {
        return size;
    }

    /**
     * 返回min节点
     * @return readTime最小的节点
     */
    public DBNode getMin() {
        sizeCheck();
        return nodes[0];
    }

    @Override
    public Iterator<DBNode> iterator() {
        return new Itr();
    }
    
    /**
     * 非线程安全
     */
    private class Itr implements Iterator<DBNode> {

        int cursor = 0;

        public boolean hasNext() {
            return cursor < size;
        }

        public DBNode next() {
            if (cursor == size)
                throw new NoSuchElementException("index: " + cursor);
            return nodes[cursor++];
        }

    }

}