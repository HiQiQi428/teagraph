package org.luncert.tkglb.cluster;

import java.net.SocketAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.luncert.tkglb.cluster.bean.DBNode;
import org.luncert.tkglb.cluster.bean.NodeStatus;
import org.luncert.tkglb.cluster.bean.Task;
import org.luncert.tkglb.cypher.CPiece;
import org.luncert.tkglb.cypher.CPiece.PieceType;
import org.luncert.tkglb.cypher.CPiece.UnionType;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

@RunWith(JUnit4.class)
public class TestDBPool {

    private DBPool pool = new DBPool();
    private DBNode node, node1, node2, node3;

    static class MyChannel implements Channel {

        @Override
        public <T> Attribute<T> attr(AttributeKey<T> key) {
            return null;
        }

        @Override
        public <T> boolean hasAttr(AttributeKey<T> key) {
            return false;
        }

        @Override
        public int compareTo(Channel o) {
            return 0;
        }

        @Override
        public ChannelId id() {
            return null;
        }

        @Override
        public EventLoop eventLoop() {
            return null;
        }

        @Override
        public Channel parent() {
            return null;
        }

        @Override
        public ChannelConfig config() {
            return null;
        }

        @Override
        public boolean isOpen() {
            return false;
        }

        @Override
        public boolean isRegistered() {
            return false;
        }

        @Override
        public boolean isActive() {
            return false;
        }

        @Override
        public ChannelMetadata metadata() {
            return null;
        }

        @Override
        public SocketAddress localAddress() {
            return null;
        }

        @Override
        public SocketAddress remoteAddress() {
            return null;
        }

        @Override
        public ChannelFuture closeFuture() {
            return null;
        }

        @Override
        public boolean isWritable() {
            return false;
        }

        @Override
        public Unsafe unsafe() {
            return null;
        }

        @Override
        public ChannelPipeline pipeline() {
            return null;
        }

        @Override
        public ByteBufAllocator alloc() {
            return null;
        }

        @Override
        public ChannelPromise newPromise() {
            return null;
        }

        @Override
        public ChannelProgressivePromise newProgressivePromise() {
            return null;
        }

        @Override
        public ChannelFuture newSucceededFuture() {
            return null;
        }

        @Override
        public ChannelFuture newFailedFuture(Throwable cause) {
            return null;
        }

        @Override
        public ChannelPromise voidPromise() {
            return null;
        }

        @Override
        public ChannelFuture bind(SocketAddress localAddress) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress) {
            return null;
        }

        @Override
        public ChannelFuture disconnect() {
            return null;
        }

        @Override
        public ChannelFuture close() {
            return null;
        }

        @Override
        public ChannelFuture deregister() {
            return null;
        }

        @Override
        public ChannelFuture bind(SocketAddress localAddress, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture connect(SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture disconnect(ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture close(ChannelPromise promise) {
            return null;
        }

        @Override
        public ChannelFuture deregister(ChannelPromise promise) {
            return null;
        }

        @Override
        public Channel read() {
            return null;
        }

        @Override
        public ChannelFuture write(Object msg) {
            return null;
        }

        @Override
        public ChannelFuture write(Object msg, ChannelPromise promise) {
            return null;
        }

        @Override
		public Channel flush() {
			return null;
		}

		@Override
		public ChannelFuture writeAndFlush(Object msg, ChannelPromise promise) {
			return null;
		}

		@Override
		public ChannelFuture writeAndFlush(Object msg) {
			return null;
		}

    }

    private Task newTask() {
        CPiece piece = CPiece.builder()
                        .pieceType(PieceType.Read)
                        .unionType(UnionType.None)
                        .content("MATCH (n:Pig) RETURN n")
                        .build();
        return new Task(0, 0, piece);
    }

    @Before
    public void before() {
        node = pool.newDBNode(new MyChannel());
        node1 = pool.newDBNode(new MyChannel());
        node2 = pool.newDBNode(new MyChannel());
        node3 = pool.newDBNode(new MyChannel());
    }

    @Test
    public void testGetReadyDBNode() throws InterruptedException {
        // ready -> reading
        node1.execute(newTask());
        node2.execute(newTask());
        node3.execute(newTask());

        // reading -> ready
        node1.executeFinished();
        node2.executeFinished();
        node3.executeFinished();

        // read -> reading
        node1.execute(newTask());
        node2.execute(newTask());

        // reading -> ready
        node1.executeFinished();
        node2.executeFinished();

        node1.execute(newTask());
        node1.executeFinished();

        pool.printHeap();
        System.out.println(pool.getReadyDBNode());
    }

    @Test
    public void testAction() {
        Channel channel = node.getChannel();
        
        pool.newAction(channel, NodeStatus.Reading, (n) -> System.out.println("node is reading"));
        
        node.execute(newTask());

        pool.newAction(channel, NodeStatus.Ready, (n) -> {
            System.out.println("node is ready");
            n.execute(null);
        });

        node.executeFinished();
    }

    @Test
    public void testActionConcurrent() throws InterruptedException {
        // 模拟异步
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node.execute(newTask());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            node.executeFinished();
        }).start();

        pool.newAction(node.getChannel(), NodeStatus.Reading, (node) -> System.out.println("node is reading")).sync();

        pool.newAction(node.getChannel(), NodeStatus.Ready, (node) -> System.out.println("node is ready")).sync();
    }

}