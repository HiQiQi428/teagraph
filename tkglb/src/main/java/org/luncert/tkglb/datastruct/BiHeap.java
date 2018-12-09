package org.luncert.tkglb.datastruct;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * 二叉堆
 */
public class BiHeap<K, V> implements Iterable<BiHeap.Node<K, V>> {

    public static class Node<K, V> {
        private K key;
        private V value;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private final static int DEFAULT_OPACITY = 16;
    private Node<K,V>[] data;
    private int size;
    private boolean isMinHeap;
    private BiFunction<K, K, Integer> compare;
    private BiConsumer<Integer, V> indexListener;

    /**
     * @param isMinHeap
     * @param compare
     */
    @SuppressWarnings("unchecked")
    public BiHeap(boolean isMinHeap, BiFunction<K, K, Integer> compare) {
        this.isMinHeap = isMinHeap;
        this.compare = compare;
        data = (Node<K,V>[]) new Node[DEFAULT_OPACITY];
    }

    @SuppressWarnings("unchecked")
    private void adjustCapacity() {
        if (size + 1 == data.length) {
            Node<K,V>[] tmp = new Node[data.length + data.length >>> 1];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
    }

    /**
     * 设置一个(只能一个)监听器,监听元素pos改变,用于更新索引
     * @param consumer
     */
    public void registerListener(BiConsumer<Integer, V> indexListener) {
        this.indexListener = indexListener;
    }

    private void indexChanged(int newIndex, V v) {
        if (indexListener != null)
            indexListener.accept(newIndex, v);
    }

    private boolean percolatePart(int p, int c) {
        Node<K, V> tmp;
        int r = compare.apply(data[p].key, data[c].key);
        if (isMinHeap && r > 0 || !isMinHeap && r < 0) {
            tmp = data[p];
            data[p] = data[c];
            data[c] = tmp;
            indexChanged(p, data[p].value);
            indexChanged(c, data[c].value);
            return true;
        }
        return false;
    }

    private void percolateDown(int i) {
        int lc = i * 2 + 1, rc = i * 2 + 2;
        if (lc < size && percolatePart(i, lc))
            percolateDown(lc);
        if (rc < size && percolatePart(i, rc))
            percolateDown(rc);
    }

    private void percolateUp(int i) {
        int parent = (i - 1) / 2;
        while (i > 0 && percolatePart(parent, i)) {
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    /**
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        adjustCapacity();
        data[size] = new Node<>(key, value);
        if (indexListener != null)
            indexListener.accept(size, value);
        percolateUp(size++);
    }

    /**
     * @param index
     * @param newKey
     */
    public void update(int index, K newKey) {
        indexCheck(index);
        K oldKey = data[index].key;
        data[index].key = newKey;
        int ret = compare.apply(oldKey, newKey);
        if (ret > 0) {
            if (isMinHeap) percolateUp(index);
            else percolateDown(index);
        }
        else if (ret < 0) {
            if (isMinHeap) percolateDown(index);
            else percolateUp(index);
        }
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
            if (compare.apply(data[min].key, data[i].key) > 0)
                min = i;
        return min;
    }

    private int getMaxFromLeafNode() {
        int max = getFristLeafNode();
        for (int i = max + 1; i < size; i++)
            if (compare.apply(data[max].key, data[i].key) < 0)
                max = i;
        return max;
    }

    public V get(int index) {
        indexCheck(index);
        return data[index].value;
    }

    /**
     * @param i
     * @return
     */
    public V delete(int i) {
        if (i < 0 || i >= size)
            throw new NoSuchElementException("index: " + i);
        V value = data[i].value;
        data[i] = data[--size];
        data[size] = null;
        percolateDown(i);
        return value;
    }

    /**
     * @param key
     * @return
     */
    public V delete(K key) {
        sizeCheck();
        Node<K,V> node;
        for (int i = 0; i < size; i++) {
            node = data[i];
            if (node.key == key || node.key.equals(key)) {
                V ret = node.value;
                data[i] = data[--size];
                data[size] = null;
                percolateDown(i);
                return ret;
            }
        }
        return null;
    }

    /**
     * @return min
     */
    public V deleteMin() {
        sizeCheck();
        if (isMinHeap) return delete(0);
        else return delete(getMinFromLeafNode());
    }

    /**
     * @return max
     */
    public V deleteMax() {
        sizeCheck();
        if (!isMinHeap) return delete(0);
        else return delete(getMaxFromLeafNode());
    }

    /**
     * @return min
     */
    public V getMinimum() {
        sizeCheck();
        if (isMinHeap) return data[0].value;
        else return data[getMinFromLeafNode()].value;
    }

    /**
     * @return max
     */
    public V getMaximum() {
        sizeCheck();
        if (!isMinHeap)
            return data[0].value;
        else return data[getMaxFromLeafNode()].value;
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
    public static <E> void sort(E[] data, boolean useMinHeap, BiFunction<E, E, Integer> compare) {
        BiHeap<E, E> adt = new BiHeap<>(useMinHeap, compare);
        for (E d : data)
            adt.insert(d, d);
        for (int i = 0; i < data.length; i++)
            if (useMinHeap) data[i] = adt.deleteMin();
            else data[i] = adt.deleteMax();
    }
    
    /**
     * 返回一个非线程安全的迭代器
     */
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Itr();
    }
    
    /**
     * 非线程安全
     */
    private class Itr implements Iterator<Node<K, V>> {

        int cursor = 0;

        public boolean hasNext() {
            return cursor < size;
        }

        public Node<K, V> next() {
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
        Node<K, V> node;
        Iterator<Node<K, V>> iterator = iterator();
        while (iterator.hasNext()) {
            node = iterator.next();
            builder.append(i).append(" - ")
                    .append(node.key).append(':')
                    .append(node.value).append('\n');
            i++;
        }
        return (i > 0) ?
                    builder.append("]\n").toString()
                    : builder.insert(builder.length() - 1, "]\n").toString();

    }

}