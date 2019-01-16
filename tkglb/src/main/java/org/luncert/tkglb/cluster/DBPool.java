package org.luncert.tkglb.cluster;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

import org.luncert.tkglb.cluster.bean.DBNode;
import org.luncert.tkglb.cluster.bean.NodeStatus;
import org.luncert.tkglb.datastruct.AdjacencyTable;
import org.luncert.tkglb.datastruct.BiHeap;
import org.luncert.tkglb.datastruct.BiHeap.Node;
import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * <li>{@link #methodInterceptor}动态代理DBNode,监听execute和executeFinished方法被调用导致的状态改变
 * ,以及readTime增加需要更新最小堆</li>
 * <li>{@link #index}向最小堆注册了一个监听器,监听节点索引变动,由此同步哈希索引表</li>
 * <li>{@link #actions}邻接表,存储每个DBNode接下来要执行的动作,添加action和删除action的操作可能发生在两个线程中</li>
 */
@Component
public class DBPool implements Iterable<DBNode> {

    /**
     * 使用最小堆支持选择读次数最少的可用节点的操作 {@link #getReadyDBNode()}
     * 存在一个问题,最小堆同胞节点不是有序的,而取可用节点的操作会先查看左孩子,再查看右孩子,所以可能返回的不是readTime最小的节点
     */
    private BiHeap<DBNode> dbs = new BiHeap<>(true);
    
    /**
     * 索引表,映射nodeId到其绑定的DBNode在最小堆中的位置
     */
    private Map<String, Integer> index = new HashMap<>();

    /**
     * DBNode方法拦截器
     */
    private MethodInterceptor methodInterceptor = new DBNMethodInterceptor();

    /**
     * actions邻接表,每个nodeId绑定一个链表,链表中顺序存放接下来DBNode要执行的操作
     */
    private AdjacencyTable<String, Action> actions = new AdjacencyTable<>();

    private volatile boolean waitingReadyNode;

    /**
     * DBNode方法拦截器
     */
    private class DBNMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            String name = method.getName();
            DBNode node = (DBNode) obj;
            Object object = proxy.invokeSuper(obj, args);

            // 拦截execute，executeFinished，disconnected调用，对节点状态进行更新
            if (name.equals("execute")) {
                Integer i = index.get(node.getId());
                dbs.update(i);
                triggerAction(node);
            }
            else if (name.equals("executeFinished")) {
                triggerAction(node);
            }
            else if (name.equals("disconnected")) {
                delete(node.getId());
            }

            return object;
		}

    }

    /**
     * 触发action
     * @param node
     */
    private void triggerAction(DBNode node) {
        String nodeId = node.getId();
        Action action = actions.get(nodeId);
        if (action != null && action.status.equals(node.getStatus())) {
            action.execute(node);
            actions.dequeue(nodeId);
            // 该action队列完成，通知阻塞在getReadyDBNode方法的线程
            if (actions.getQueueSize(nodeId) == 0 && waitingReadyNode) {
                synchronized(this) {
                    notify();
                }
            }
        }
    }

    /**
     * 当监听的节点状态转变为期待的状态时,执行回调
     */
    public static class Action {
        private final NodeStatus status;
        private final Consumer<DBNode> callback;

        Action(NodeStatus status, Consumer<DBNode> callback) {
            this.status = status;
            this.callback = callback;
        }

        /**
         * 执行回调,通知其他调用了{@link #sync()}的线程
         * @param node
         */
        private void execute(DBNode node) {
            callback.accept(node);
            synchronized(this) {
                notify();
            }
        }

        /**
         * 同步,等待action被执行
         */
        public void sync() {
            synchronized(this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public DBPool() {
        dbs.registerListener(
            (newIndex, node) -> 
                index.put(node.getId(), newIndex));
    }

    /**
     * 创建与channel绑定的DBNode
     * @param channel
     * @return DBNode
     */
    public DBNode newDBNode(Channel channel) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DBNode.class);
        enhancer.setCallback(methodInterceptor);
        DBNode node = (DBNode) enhancer.create(new Class[]{Channel.class}, new Object[]{channel});
        dbs.insert(node);
        return node;
    }

    /**
     * 根据nodeId获取DBNode,首先根据nodeId从索引表获取索引,在用索引从最小堆中获取DBNode
     * @param nodeId
     * @return DBNode
     */
    public DBNode getDBNode(String nodeId) {
        Integer i = index.get(nodeId);
        return (i != null) ? (DBNode) dbs.get(i) : null;
    }

    /**
     * 为指定DBNode创建action,会影响到{@link #getReadyDBNode()}
     * @param nodeId 节点id
     * @param status 出发action的节点状态
     * @param callback 回调,实际操作
     * @return Action, 用于调用sync进行同步
     */
    public Action newAction(String nodeId, NodeStatus status, Consumer<DBNode> callback) {
        Action action = new Action(status, callback);
        actions.enqueue(nodeId, action);
        return action;
    }

    private DBNode getReadyDBNodeNonBlock() {
        DBNode dbNode;
        for (Node node : dbs) {
            dbNode = (DBNode) node;
            if (dbNode.getStatus() == NodeStatus.Ready
                && actions.getQueueSize(dbNode.getId()) == 0)
            {
                return dbNode;
            }
        }
        return null;
    }

    /**
     * <li>遍历最小堆,返回找到的第一个可用节点,如果没有便阻塞当前线程</li>
     * <li>可用:状态为Ready且action队列为空{@link #getReadyDBNodeNonBlock()}</li>
     * <li>(有点不合理,action队列应该是可以不为空的)</li>
     * 
     * @return DBNode
     * @throws InterruptedException
     */
    public DBNode getReadyDBNode() throws InterruptedException {
        DBNode dbNode = getReadyDBNodeNonBlock();
        if (dbNode == null) {
            synchronized(this) {
                waitingReadyNode = true;
                wait(); // 在triggerAction被唤醒
                waitingReadyNode = false;
            }
            dbNode = getReadyDBNodeNonBlock();
            if (dbNode == null)
                throw new RuntimeException("unexpected error");
        }
        return dbNode;
    }

    public int size() {
        return dbs.size();
    }

    /**
     * 删除节点
     * @param nodeId 节点id
     * @return
     */
    public DBNode delete(String nodeId) {
        Integer i = index.get(nodeId);
        if (i != null) {
            DBNode node = dbs.delete(i);
            index.remove(nodeId);
            actions.remove(nodeId);
            return node;
        }
        return null;
    }

    public void printHeap() {
        System.out.println(dbs);
    }

    public Iterator<DBNode> iterator() {
        return new Itr();
    }
    
    private class Itr implements Iterator<DBNode> {

        Iterator<Node> iterator = dbs.iterator();

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public DBNode next() {
            return (DBNode) iterator.next();
        }

    }

}