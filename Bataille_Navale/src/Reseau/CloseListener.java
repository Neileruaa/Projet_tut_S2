package Reseau;

import com.esotericsoftware.kryonet.Client;

public class CloseListener implements Runnable {

    Client client;

    public CloseListener(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        client.stop();
    }
}
