package Reseau;

import com.esotericsoftware.kryonet.Client;

public class SendListener implements Runnable {
    Chat chat;
    Client client;

    public SendListener(Chat chat, Client client) {
        this.chat = chat;
        this.client = client;
    }

    @Override
    public void run() {
        Network.ChatMessage chatMessage = new Network.ChatMessage();
        chatMessage.text = chat.getSendText();
        client.sendTCP(chatMessage);
    }
}
