package org.luncert.tkglb.datastruct;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

/**
 * 二叉堆
 */
public class BiHeap<T extends BiHeap.Node> implements Iterable<BiHeap.Node> {

    public static abstract class Node {

        public abstract int compare(Node node);

    }

    private final static int DEFAULT_OPACITY = 16;
    private T[] data;
    private int size;
    private boolean isMinHeap;
    private BiConsumer<Integer, T> indexListener;

    /**
     * @param isMinHeap
     * @param compare
     */
    @SuppressWarnings("unchecked")
    public BiHeap(boolean isMinHeap) {
        this.isMinHeap = isMinHeap;
        data = (T[]) new Node[DEFAULT_OPACITY];
    }

    @SuppressWarnings("unchecked")
    private void adjustCapacity() {
        if (size + 1 == data.length) {
            T[] tmp = (T[]) new Node[data.length + data.length >>> 1];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
    }

    /**
     * 设置一个(只能一个)监听器,监听元素pos改变,用于更新索引
     * @param consumer
     */
    public void registerListener(BiConsumer<Integer, T> indexListener) {
        this.indexListener = indexListener;
    }

    private void indexChanged(int newIndex, T node) {
        if (indexListener != null)
            indexListener.accept(newIndex, node);
    }

    private boolean percolatePart(int p, int c) {
        T tmp;
        int r = data[p].compare(data[c]);
        if (isMinHeap && r > 0 || !isMinHeap && r < 0) {
            tmp = data[p];
            data[p] = data[c];
            data[c] = tmp;
            indexChanged(p, data[p]);
            indexChanged(c, data[c]);
            return true;
        }
        return false;
    }

    /**
     * 下滤
     * @param i
     */
    private void percolateDown(int i) {
        int lc = i * 2 + 1, rc = i * 2 + 2;
        if (lc < size && percolatePart(i, lc))
            percolateDown(lc);
        if (rc < size && percolatePart(i, rc))
            percolateDown(rc);
    }

    /**
     * 上滤
     * @param i
     */
    private void percolateUp(int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && percolatePart(parent, i)) {
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    /**
     * @param node
     */
    public void insert(T node) {
        adjustCapacity();
        data[size] = node;
        if (indexListener != null)
            indexListener.accept(size, node);
        percolateUp(size++);
    }

    /**
     * @param index
     */
    public void update(int index) {
        indexCheck(index);
        percolateUp(index);
        percolateUp(index);
    }

    private void sizeCheck() {
        if (size == 0)
            throw new UnsupportedOperationException("operation on empty heap");
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size)
            throw new NoSuchElementException("index: " + index);
    }

    private int getFristLeafNode() {
        // 计算前 h - 1 层的容量和最后一层的节点数
        int s1 = (int) (size - (Math.pow(Math.log(size), 2) - 1)), l = size - s1;
        // 计算 h - 1 层叶子节点数
        return (l % 2 == 0 ? l / 2 : l / 2 + 1) - 1;
    }

    private int getMinFromLeafNode() {
        int min = getFristLeafNode();
        for (int i = min + 1; i < size; i++)
            if (data[min].compare(data[i]) > 0)
                min = i;
        return min;
    }

    private int getMaxFromLeafNode() {
        int max = getFristLeafNode();
        for (int i = max + 1; i < size; i++)
            if (data[max].compare(data[i]) < 0)
                max = i;
        return max;
    }

    public Node get(int index) {
        indexCheck(index);
        return data[index];
    }

    /**
     * @param i
     * @return
     */
    public T delete(int i) {
        if (i < 0 || i >= size)
            throw new NoSuchElementException("index: " + i);
        T node = data[i];
        data[i] = data[--size];
        data[size] = null;
        percolateDown(i);
        return node;
    }

    /**
     * @return min
     */
    public T deleteMin() {
        sizeCheck();
        if (isMinHeap) return delete(0);
        else return delete(getMinFromLeafNode());
    }

    /**
     * @return max
     */
    public T deleteMax() {
        sizeCheck();
        if (!isMinHeap) return delete(0);
        else return delete(getMaxFromLeafNode());
    }

    /**
     * @return min
     */
    public T getMinimum() {
        sizeCheck();
        if (isMinHeap) return data[0];
        else return data[getMinFromLeafNode()];
    }

    /**
     * @return max
     */
    public T getMaximum() {
        sizeCheck();
        if (!isMinHeap)
            return data[0];
        else return data[getMaxFromLeafNode()];
    }

    /**
     * @return size
     */
    public int size() {
        return size;
    }

    public void clear() {
        data = null;
        size = 0;
    }

    /**
     * 堆排序
     * @param data
     * @param useMinHeap
     * @param compare
     */
    public static <E extends BiHeap.Node> void sort(E[] data, boolean useMinHeap) {
        BiHeap<E> adt = new BiHeap<>(useMinHeap);
        for (E d : data)
            adt.insert(d);
        for (int i = 0; i < data.length; i++) {
            if (useMinHeap) {
                data[i] = adt.deleteMin();
            }
            else {
                data[i] = adt.deleteMax();
            }
        }
    }
    
    /**
     * 返回一个非线程安全的迭代器
     */
    @Override
    public Iterator<Node> iterator() {
        return new Itr();
    }
    
    /**
     * 非线程安全
     */
    private class Itr implements Iterator<Node> {

        int cursor = 0;

        public boolean hasNext() {
            return cursor < size;
        }

        public Node next() {
            if (cursor == size)
                throw new NoSuchElementException("index: " + cursor);
            return data[cursor++];
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (isMinHeap)
        builder.append("MinHeap [\n");
        else
        builder.append("MaxHeap [\n");
        
        int i = 0;
        Node node;
        Iterator<Node> iterator = iterator();
        while (iterator.hasNext()) {
            node = iterator.next();
            builder.append(i)
                .append(" - ")
                .append(node)
                .append('\n');
            i++;
        }
        return (i > 0) ?
            builder.append("]\n").toString()
            : builder.insert(builder.length() - 1, "]\n").toString();

    }

}