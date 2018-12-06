package org.luncert.tkglb.datastruct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * HashMap实现的邻接表,用链表实现队列作为键值,相同键名不同键值不会放在同一个槽中,而是放入同一个队列的不同位置,做了一定程度的同步
 * @param <K>
 * @param <V>
 */
public class AdjacencyTable<K, V> {

    Map<K, List<V>> map = new HashMap<>();
    volatile int size;

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * @param key
     * @return key对应的队列头元素
     */
    public V get(K key) {
        List<V> list = map.get(key);
        synchronized(list) {
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    public int getQueueSize(K key) {
        List<V> list = map.get(key);
        return list != null ? list.size() : 0;
    }

    /**
     * 放入key对应的队列的末尾
     * @param key
     * @param value
     */
    public void enqueue(K key, V value) {
        List<V> list = map.get(key);
        if (list == null) {
            list = new LinkedList<>();
            map.put(key, list);
        }
        synchronized(list) {
            size++;
            list.add(value);
        }
    }

    /**
     * @param key
     * @return key对应的队列头元素
     */
    public V dequeue(K key) {
        List<V> list = map.get(key);
        synchronized(list) {
            if (list != null && !list.isEmpty()) {
                V v = list.get(0);
                list.remove(0);
                size--;
                return v;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    /**
     * 删除队列
     * @param key
     */
    public void remove(K key) {
        List<V> list = map.remove(key);
        if (list != null)
            size -= list.size();
    }

    public void clear() {
        map.clear();
    }

}