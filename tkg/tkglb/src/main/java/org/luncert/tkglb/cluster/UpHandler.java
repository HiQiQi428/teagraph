package org.luncert.tkglb.cluster;

import org.luncert.tkglb.cluster.bean.DBNode;
import org.luncert.tkglb.cluster.bean.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * 用于连接数据库节点
 */
@Component
public class UpHandler extends ChannelHandlerAdapter {

    public static final AttributeKey<DBNode> NETTY_DBNODE_KEY = AttributeKey.valueOf("netty.dbnode");


    @Autowired
    private DBPool dbs;

    @Autowired
    private ResultPool resultPool;

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Attribute<DBNode> attr = ctx.attr(NETTY_DBNODE_KEY);
        DBNode dbNode = dbs.newDBNode(ctx.channel());
        attr.setIfAbsent(dbNode);
        ctx.fireChannelActive();
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Attribute<DBNode> attr = ctx.attr(NETTY_DBNODE_KEY);
        DBNode dbNode = attr.getAndRemove();
        dbs.delete(dbNode.getId());
        ctx.fireChannelInactive();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        assert(msg instanceof Result);
        Attribute<DBNode> attr = ctx.attr(NETTY_DBNODE_KEY);
        resultPool.addTaskResult((Result) msg);
        attr.get().executeFinished();
    }

}