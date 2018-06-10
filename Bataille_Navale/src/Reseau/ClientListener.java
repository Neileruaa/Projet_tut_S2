package Reseau;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.awt.*;

public class ClientListener extends Listener {

    Client client;
    Chat chat;
    String name;
    ClientProg fenetre;

    public ClientListener(Client client, Chat chat, String name, ClientProg fenetre) {
        this.client = client;
        this.chat = chat;
        this.name = name;
        this.fenetre = fenetre;
    }

    public void connected (Connection connection) {
        fenetre.setNumJoueur(client.getID());
        Network.RegisterName registerName = new Network.RegisterName();
        registerName.name = name;
        client.sendTCP(registerName);
        fenetre.setTitle("Client numero : "+String.valueOf(client.getID()));
    }

    public void received (Connection connection, Object object) {
        if (object instanceof Network.UpdateNames) {
            Network.UpdateNames updateNames = (Network.UpdateNames)object;
            chat.setNames(updateNames.names);
            return;
        }

        if (object instanceof Network.ChatMessage) {
            Network.ChatMessage chatMessage = (Network.ChatMessage)object;
            chat.addMessage(chatMessage.text);
            return;
        }

        if (object instanceof Network.gameParameter){
            Network.gameParameter gameParameter = (Network.gameParameter)object;

            //Objectif : afficher l'age dans le label du haut
            String text = "Nom du joueur : " + gameParameter.playerName+
                    "\n Age du joueur : " + gameParameter.playerAge+
                    "\n NB BATO : " + gameParameter.shipNumber;
            fenetre.setMessageLabelText(fenetre.labAffMessage, text);
        }
    }

    public void disconnected (Connection connection) {
        EventQueue.invokeLater(new Runnable() {
            public void run () {
                // Closing the frame calls the close listener which will stop the client's update thread.
                chat.getFenetre().dispose();
            }
        });
    }
}
