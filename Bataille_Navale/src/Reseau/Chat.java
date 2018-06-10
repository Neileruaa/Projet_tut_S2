package Reseau;

import com.esotericsoftware.kryonet.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Chat extends JPanel {
    //Initialisation des composants JSwing
    JList messageList;
    JTextField sendText;
    JButton sendButton;
    JList nameList;

    Client client;
    String host;
    ClientProg fenetre;
    String name;

    //Initialisation des Listener que j'utilise
    Runnable sendListener;
    Runnable closeListener;


    public Chat(ClientProg fenetre, String host, Client client, String name) {
        this.fenetre = fenetre;
        this.host = host;
        this.client = client;
        this.name = name;

        this.setLayout(new BorderLayout());

        init();
        ajoutComposant();
        connectionToServer();
    }

    private void init(){
        sendButton = new JButton("Envoyer");
        sendText = new JTextField();
        sendText.setColumns(15);

        sendText.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                sendButton.doClick();
            }
        });

        messageList = new JList();
        messageList.setModel(new DefaultListModel());

        nameList = new JList();
        nameList.setModel(new DefaultListModel());

        DefaultListSelectionModel disableSelections = new DefaultListSelectionModel() {
            public void setSelectionInterval (int index0, int index1) {
            }
        };
        messageList.setSelectionModel(disableSelections);
        nameList.setSelectionModel(disableSelections);


        //Partie Listener
        sendListener = new SendListener(this, client);
        setSendListener(sendListener);

        closeListener = new CloseListener(client);
        setCloseListener(closeListener, fenetre);


    }

    private void ajoutComposant() {
        JPanel chat = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JPanel bottomPanel = new JPanel();

        topPanel.add(new JScrollPane(messageList));
        topPanel.add(new JScrollPane(nameList));

        bottomPanel.add(sendText);
        bottomPanel.add(sendButton);

        chat.add(topPanel);
        chat.add(bottomPanel,BorderLayout.SOUTH);

        add(chat);
    }

    private void setSendListener (final Runnable listener) {
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent evt) {
                if (getSendText().length() == 0) return;
                listener.run();
                sendText.setText("");
                sendText.requestFocus();
            }
        });
    }

    private void setCloseListener (final Runnable listener , ClientProg fen) {
        fen.addWindowListener(new WindowAdapter() {
            public void windowClosed (WindowEvent evt) {
                listener.run();
            }

            public void windowActivated (WindowEvent evt) {
                sendText.requestFocus();
            }
        });
    }

    private void connectionToServer(){
        Thread threadConnection = new Thread("Connect"){
            @Override
            public void run() {
                try {
                    client.connect(5000, host, Network.port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        threadConnection.start();
    }

    public void addMessage (final String message) {
        EventQueue.invokeLater(new Runnable() {
            public void run () {
                DefaultListModel model = (DefaultListModel)messageList.getModel();
                model.addElement(message);
                messageList.ensureIndexIsVisible(model.size() - 1);
            }
        });
    }


    //getters and setters
    public void setNames (final String[] names) {
        // This listener is run on the client's update thread, which was started by client.start().
        // We must be careful to only interact with Swing components on the Swing event thread.
        EventQueue.invokeLater(new Runnable() {
            public void run () {
                DefaultListModel model = (DefaultListModel)nameList.getModel();
                model.removeAllElements();
                for (String name : names)
                    model.addElement(name);
            }
        });
    }

    public ClientProg getFenetre() {
        return fenetre;
    }

    public String getSendText() {
        return sendText.getText().trim();
    }
}
