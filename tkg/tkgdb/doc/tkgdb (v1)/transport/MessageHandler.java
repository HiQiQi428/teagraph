package org.luncert.tkgdb.transport;

import io.netty.channel.ChannelHandlerContext;

public abstract class MessageHandler implements Runnable {

    private Message message;
    private ChannelHandlerContext ctx;

    public final void setContext(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public final void setMessage(Message message) {
        this.message = message;
    }

    public final void run() {
        handle(message);
    }

    protected void reply(Message msg) {
        ctx.writeAndFlush(msg);
    }

    protected abstract void handle(Message message);

}