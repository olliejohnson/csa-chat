package io.oliverj.chat.server;

import io.oliverj.chat.server.network.ChatServer;

public class Application {
    public static void main(String[] args) throws Exception {
        ChatServer.start();
    }
}
