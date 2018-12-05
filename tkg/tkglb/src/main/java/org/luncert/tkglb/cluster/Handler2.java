package org.luncert.tkglb.cluster;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 用于连接数据库节点
 */
public class Handler2 extends ChannelHandlerAdapter {

    private final DBPool dbs;

    private final TaskPool taskPool;

    public Handler2(DBPool pool, TaskPool taskPool) {
        dbs = pool;
        this.taskPool = taskPool;
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        dbs.newDBNode(ctx.channel());
        ctx.fireChannelActive();
    }

    public void channelInActive(ChannelHandlerContext ctx) throws Exception {
        dbs.delete(ctx.channel());
        ctx.fireChannelInactive();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        assert(msg instanceof Result);
        taskPool.addTaskResult((Result) msg);
        dbs.getDBNode(ctx.channel()).executeFinished();
    }

}