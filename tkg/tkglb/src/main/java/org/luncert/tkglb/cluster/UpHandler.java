package org.luncert.tkglb.cluster;

import org.luncert.tkglb.cluster.bean.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 用于连接数据库节点
 */
@Component
public class UpHandler extends ChannelHandlerAdapter {

    @Autowired
    private DBPool dbs;

    @Autowired
    private TaskPool taskPool;

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        dbs.newDBNode(ctx.channel());
        ctx.fireChannelActive();
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        dbs.delete(ctx.channel());
        ctx.fireChannelInactive();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        assert(msg instanceof Result);
        taskPool.addTaskResult((Result) msg);
        dbs.getDBNode(ctx.channel()).executeFinished();
    }

}