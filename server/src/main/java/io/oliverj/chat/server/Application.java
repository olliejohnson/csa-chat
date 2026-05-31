package io.oliverj.chat.server;

import io.oliverj.chat.server.network.ChatServer;
import io.oliverj.chat.server.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Application {
    public static void main(String[] args) throws Exception {
        ChatServer.start();
    }
}
