package org.luncert.tkglb.cluster;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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

public class DBCluster {

    private class ServerHandler extends ChannelHandlerAdapter {
        
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            // dbs.newDBNode(ctx.channel());
            ctx.fireChannelRegistered();
        }

        public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
            dbs.delete(ctx.channel());
            ctx.fireChannelUnregistered();
        }

        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof TaskResult) {
                resultPool.addTaskResult((TaskResult) msg);
                dbs.getDBNode(ctx.channel()).executeFinished((TaskResult) msg);
            }
            else System.out.println(msg);
            ctx.fireChannelRead(msg);
        }
        
    }

    private DBPool dbs = new DBPool();
    private TaskQueue taskQueue = new TaskQueue();
    private ResultPool resultPool = new ResultPool();
    private AtomicInteger updatingNodeNum;

    private EventLoopGroup bossGroup, workerGroup;
    private ChannelFuture cf;

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
                        p.addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        p.addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        p.addLast(new LoggingHandler(LogLevel.INFO));
                        p.addLast(new ServerHandler());
                    }

                });
        
        cf = bootstrap.bind(8899);
        bossGroup.submit(() -> {
            try {
                dispatchTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
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
                taskQueue.waitForTask();
            task = taskQueue.deQueue();
            // reading task
            if (task.getPiece().getPieceType() == PieceType.Read) {
                resultPool.addWaitingTask(task);
                while ((node = dbs.getReadyDBNode()) == null) {
                    // 等待netty的有read事件发生,这时会有节点因为执行完任务进入Ready状态
                    this.wait();
                }
                node.execute(task); // execute后readTime自增了
            }
            // updating task
            else {
                updatingNodeNum.set(dbs.size());
                synchronized(this) {
                    wait();
                }
            }
        }
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