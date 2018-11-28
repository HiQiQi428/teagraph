package org.luncert.tkglb.cluster;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;

import org.luncert.tkglb.cluster.DBNode.NodeStatus;
import org.luncert.tkglb.cypher.CPiece;
import org.luncert.tkglb.cypher.CypherAnalyser;
import org.luncert.tkglb.cypher.CPiece.PieceType;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class DBCluster {

    private class ServerHandler extends ChannelHandlerAdapter {
        
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            dbs.newDBNode(ctx.channel());
            ctx.fireChannelRegistered();
        }

        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            dbs.delete(ctx.channel());
            ctx.fireChannelUnregistered();
        }

        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof TaskResult) {
                resultPool.addTaskResult((TaskResult) msg);
                dbs.getDBNode(ctx.channel()).changeStatus(NodeStatus.Ready);
            }
            else System.out.println(msg);
            ctx.fireChannelRead(msg);
        }
        
    }

    private DBPool dbs = new DBPool();
    private TaskQueue taskQueue = new TaskQueue();
    private ResultPool resultPool = new ResultPool();
    private EventLoopGroup bossGroup, workerGroup;
    private ChannelFuture cf;

    /**
     * 阀门,遇到写任务时置true,写任务及之前的所有任务执行完成后才为false,此前不进行任务调度
     */
    private boolean valve = false;

    private DBCluster() {}

    private void init() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LoggingHandler(LogLevel.INFO));
                        p.addLast(new ReadTimeoutHandler(5));
                        p.addLast(new ServerHandler());
                    }

                });
        
        cf = bootstrap.bind(8899);
    }

    /**
     * 任务调度
     * @throws InterruptedException
     */
    void dispatchTask() throws InterruptedException {
        Task task;
        DBNode node;
        while (true) {
            if (taskQueue.size() == 0)
                taskQueue.wait();
            task = taskQueue.deQueue();
            resultPool.addWaitingTask(task);
            if (task.getPiece().getPieceType() == PieceType.Read) {
                while ((node = dbs.getReadyDBNode()) == null) {
                    // 等待netty的有read事件发生,这时会有节点因为执行完任务进入Ready状态
                    this.wait();
                }
                node.execute(task);
                dbs.update(node);
            }
            else {
                valve = true;
                Iterator<DBNode> iterator = dbs.iterator();
                while (iterator.hasNext()) {
                    node = iterator.next();
                    node.execute(task);
                }
            }
        }
    }

    /**
     * 清理netty资源
     */
    @Override
    public void finalize() {
        try {
            cf.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    /**
     * 异步操作,仅添加新任务到任务队列
     * 
     * @param query
     * @return 执行id,和query绑定.通过id可以从ResultPool中获取执行结果
     * @throws Exception cypher解析异常
     */
    public void execute(String query, Consumer<String> callback) throws Exception {
        int gid = taskQueue.enQueue(CypherAnalyser.analyse(query));
        resultPool.newGroup(gid, callback);
    }

    private static class DBClusterInner {
        static final DBCluster INSTANCE;
        static {
            INSTANCE = new DBCluster();
            try {
                INSTANCE.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static DBCluster getInstance() {
        return DBClusterInner.INSTANCE;
    }

}