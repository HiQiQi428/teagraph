package org.luncert.tkglb.cluster;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.luncert.tkglb.cluster.DBNode.NodeStatus;

import io.netty.channel.Channel;

/**
 * 最小堆存储DBNode
 */
public class DBPool implements Iterable<DBNode> {

    private final static int DEFAULT_OPACITY = 16;
    private DBNode[] nodes;
    private int size;
    Map<Channel, Integer> index = new HashMap<>();

    public DBPool() {
        nodes = new DBNode[DEFAULT_OPACITY];
    }

    /**
     * 调整堆数组容量
     */
    private void adjustCapacity() {
        if (size + 1 == nodes.length) {
            DBNode[] tmp = new DBNode[nodes.length + nodes.length >>> 1];
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
        DBNode parent = nodes[p], child = nodes[c];
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
        DBNode node = new DBNode(channel);
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

    /**
     * 返回min节点
     * @return readTime最小的节点
     */
    public DBNode getMin() {
        sizeCheck();
        return nodes[0];
    }

    /**
     * node的readTime增加了,需要下滤
     */
    public void update(DBNode node) {
        int i = index.get(node.getChannel());
        percolateDown(i);
    }

    @Override
    public Iterator<DBNode> iterator() {
        return new Itr();
    }
    
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