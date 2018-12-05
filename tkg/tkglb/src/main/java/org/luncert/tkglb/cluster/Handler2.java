package org.luncert.tkglb.cluster;

import org.luncert.mullog.Mullog;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 用于连接数据库节点
 */
public class Handler2 extends ChannelHandlerAdapter {

    private static Mullog mullog = new Mullog();

    private final DBPool dbs;

    private final TaskPool taskPool;

    public Handler2(DBPool pool, TaskPool taskPool) {
        dbs = pool;
        this.taskPool = taskPool;
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DBNode node = dbs.newDBNode(ctx.channel());
        mullog.info("new DB node connected:", node.getId());
        ctx.fireChannelActive();
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        DBNode node = dbs.delete(ctx.channel());
        mullog.info("DB node disconnected:", node.getId());
        ctx.fireChannelInactive();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        assert(msg instanceof Result);
        taskPool.addTaskResult((Result) msg);
        dbs.getDBNode(ctx.channel()).executeFinished();
    }

}