package io.oliverj.chat.client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatClient {
    private final String host;
    private final int port;

    private static ChatClient INSTANCE;

    public static void makeClient(String host, int port, String username) {
        INSTANCE = new ChatClient(host, port);
        MessagePublisher.setUsername(username);
    }

    public static ChatClient getClient() {
        return INSTANCE;
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new ChatClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(host, port).sync();
            System.out.println("Chat client started");
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
