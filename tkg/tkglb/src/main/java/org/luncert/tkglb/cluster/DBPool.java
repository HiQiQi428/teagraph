package org.luncert.tkglb.cluster;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.luncert.tkglb.datastruct.AdjacencyTable;
import org.luncert.tkglb.datastruct.BiHeap;
import org.luncert.tkglb.datastruct.BiHeap.Node;

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
public class DBPool implements Iterable<DBNode> {

    /**
     * 使用最小堆支持选择读次数最少的可用节点的操作 {@link #getReadyDBNode()}
     * 存在一个问题,最小堆同胞节点不是有序的,而取可用节点的操作会先查看左孩子,再查看右孩子,所以可能返回的不是readTime最小的节点
     */
    private BiHeap<Integer, DBNode> dbs = new BiHeap<>(true,
        (a, b) -> (a > b ? 1 : (a < b ? -1 : 0)));
    
    /**
     * 索引表,映射channel到其绑定的DBNode在最小堆中的位置
     */
    private Map<Channel, Integer> index = new HashMap<>();

    /**
     * DBNode方法拦截器
     */
    private MethodInterceptor methodInterceptor = new DBNMethodInterceptor();

    /**
     * actions邻接表,每个channel绑定一个链表,链表中顺序存放接下来DBNode要执行的操作
     */
    private AdjacencyTable<Channel, Action> actions = new AdjacencyTable<>();

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
            // 拦截execute,executeFinished,disconnected调用
            if (name.equals("execute")) {
                Integer i = index.get(node.getChannel());
                dbs.update(i, node.getReadTime());
                triggerAction(node);
            }
            else if (name.equals("executeFinished")) {
                // 通知阻塞在getReadyDBNode方法的线程
                if (waitingReadyNode) {
                    synchronized(this) {
                        notify();
                    }
                }
                triggerAction(node);
            }
            else if (name.equals("disconnected")) {
                delete(node.getChannel());
            }
            return object;
		}

    }

    /**
     * 触发action
     * @param node
     */
    private void triggerAction(DBNode node) {
        Channel channel = node.getChannel();
        Action action = actions.get(channel);
        if (action != null && action.status.equals(node.getStatus())) {
            action.execute(node);
            actions.dequeue(channel);
            // 所有action完成
            // if (actions.size() == 0) {
            //     synchronized(this) {
            //         notify();
            //     }
            // }
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
        dbs.registerListener((newIndex, node) -> 
            index.put(node.getChannel(), newIndex));
    }

    private String genUUID(int n) {
        String ret = "";
        int loopTime = n / 32 + ((n % 32 == 0) ? 0 : 1);
        for (int i = 0, rest = n;; i++, rest -= 32) {
            if (i < loopTime - 1)
                ret += UUID.randomUUID().toString().trim().replaceAll("-", "");
            else {
                ret += UUID.randomUUID().toString().trim().replaceAll("-", "").substring(0, rest);
                break;
            }
        }
        return ret;
    }

    /**
     * 生成node ID
     * @return
     */
    private String genNodeID() {
        return genUUID(8);
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
        DBNode node = (DBNode) enhancer.create(new Class[]{String.class, Channel.class}, new Object[]{genNodeID(), channel});
        dbs.insert(node.getReadTime(), node);
        return node;
    }

    /**
     * 根据channel获取DBNode,首先根据channel从索引表获取索引,在用索引从最小堆中获取DBNode
     * @param channel
     * @return DBNode
     */
    public DBNode getDBNode(Channel channel) {
        Integer i = index.get(channel);
        return (i != null) ? dbs.get(i) : null;
    }

    /**
     * 为指定DBNode创建action,会影响到{@link #getReadyDBNode()}
     * @param channel 节点channel
     * @param status 出发action的节点状态
     * @param callback 回调,实际操作
     * @return Action, 用于调用sync进行同步
     */
    public Action newAction(Channel channel, NodeStatus status, Consumer<DBNode> callback) {
        Action action = new Action(status, callback);
        actions.enqueue(channel, action);
        return action;
    }

    /**
     * 等待所有排队的操作被执行完
     * @throws InterruptedException
     */
    // public void waitAllActionDone() throws InterruptedException {
    //     if (this.actions.size() > 0) {
    //         synchronized(this) {
    //             wait();
    //         }
    //     }
    // }

    private DBNode getReadyDBNodeNonBlock() {
        DBNode dbNode;
        for (Node<Integer, DBNode> node : dbs) {
            dbNode = node.getValue();
            if (dbNode.getStatus() == NodeStatus.Ready
                && actions.getQueueSize(dbNode.getChannel()) == 0)
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
                wait();
                waitingReadyNode = false;
            }
            dbNode = getReadyDBNodeNonBlock();
            if (dbNode == null)
                throw new RuntimeException("unexpected error");
        }
        return dbNode;
    }

    public boolean contians(Channel channel) {
        return index.containsKey(channel);
    }

    public int size() {
        return dbs.size();
    }

    public DBNode delete(Channel channel) {
        Integer i = index.get(channel);
        if (i != null) {
            DBNode node = dbs.delete(i);
            index.remove(channel);
            actions.remove(channel);
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

        Iterator<Node<Integer, DBNode>> iterator = dbs.iterator();

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public DBNode next() {
            return iterator.next().getValue();
        }

    }

}