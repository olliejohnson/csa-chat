package io.oliverj.chat.server.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private static ConnectionManager INSTANCE;

    private ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private ConnectionManager() {}

    public static ConnectionManager getInstance() {
        if (INSTANCE == null) INSTANCE = new ConnectionManager();
        return INSTANCE;
    }

    public void connect(ChannelHandlerContext ctx) {
        clients.add(ctx.channel());
    }

    public void disconnect(ChannelHandlerContext ctx) {
        clients.remove(ctx.channel());
    }

    public void broadcast(ByteBuf msg) {
        clients.write(msg);
    }
}
