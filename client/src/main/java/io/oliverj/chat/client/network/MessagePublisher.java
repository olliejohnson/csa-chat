package io.oliverj.chat.client.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MessagePublisher {
    private static Channel channel;

    private static final List<Consumer<String>> consumers = new ArrayList<>();

    private static String username = "";

    public static void init(ChannelHandlerContext ctx) {
        MessagePublisher.channel = ctx.channel();
    }

    public static void send(String msg) {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeCharSequence(username + ": " + msg, StandardCharsets.UTF_8);
        channel.writeAndFlush(buffer);
    }

    public static void systemMessage(String msg) {
        consumers.getFirst().accept(msg);
    }

    public static void registerHandler(Consumer<String> messageConsumer) {
        consumers.add(messageConsumer);
    }

    public static void onReceive(ByteBuf buffer) {
        String message = buffer.toString(StandardCharsets.UTF_8);
        consumers.forEach(stringConsumer -> stringConsumer.accept(message));
    }

    public static void setUsername(String username) {
        MessagePublisher.username = username;
    }
}
