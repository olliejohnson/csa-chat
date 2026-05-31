package io.oliverj.chat.client;

import com.formdev.flatlaf.FlatDarculaLaf;
import io.oliverj.chat.client.network.ChatClient;
import io.oliverj.chat.client.network.MessagePublisher;
import io.oliverj.chat.client.ui.ChatScreen;

import javax.swing.*;

public class Application {
    public static void main(String[] args) throws Exception {
        FlatDarculaLaf.setup();
        MessagePublisher.setUsername("ollie");

        ChatClient client = new ChatClient("127.0.0.1", 1020);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client.start();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        SwingUtilities.invokeLater(() -> {
            ChatScreen chatScreen = new ChatScreen();

            chatScreen.setSize(800, 600);
            chatScreen.setVisible(true);

//            ConnectDialog dialog = new ConnectDialog();
//            dialog.setVisible(true);
        });
    }
}
