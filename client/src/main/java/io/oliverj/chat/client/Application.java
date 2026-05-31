package io.oliverj.chat.client;

import com.formdev.flatlaf.FlatDarculaLaf;
import io.oliverj.chat.client.network.ChatClient;
import io.oliverj.chat.client.network.MessagePublisher;
import io.oliverj.chat.client.ui.ChatScreen;
import io.oliverj.chat.client.ui.ConnectDialog;

import javax.swing.*;

public class Application {
    public static void main(String[] args) throws Exception {
        FlatDarculaLaf.setup();

        SwingUtilities.invokeLater(() -> {
            ChatScreen chatScreen = new ChatScreen();

            chatScreen.setSize(800, 600);
            chatScreen.setVisible(true);

            ConnectDialog dialog = new ConnectDialog();
            dialog.setLocationRelativeTo(chatScreen);
            dialog.pack();
            dialog.setVisible(true);

            while (dialog.isVisible()) {}

            Thread serverThread = new Thread(() -> {
                try {
                    ChatClient.getClient().start();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            serverThread.start();
        });
    }
}
