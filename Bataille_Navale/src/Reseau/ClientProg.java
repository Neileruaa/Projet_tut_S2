package Reseau;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;

import javax.swing.*;
import java.awt.*;

public class ClientProg extends JFrame {
    //Panel general Layout : border
    JPanel panel;

    //Ce JPanel affichera soit l'ecran de jeu soit l'ecran d'attente
    JPanel panPartieJeu;
    JPanel partieGauche;

    //Contenu test
    JLabel labelTest;

    //Bouton pour passer son tour et changer d'écran
    JButton butNextTurn;

    //Module pour le chat
    Chat chat;

    //Etat de la fenetre 0-> attente 1->jouer
    int etat;

    //Module simulation écran de jeu
    EcranJeu ecranJeu;

    //Module pour attente que le joueur adverse est finis
    EcranAttente ecranAttente;

    //Informations réseau
    Client client;
    String name;
    String host;

    //Affichage message en haut de la fenetre
    JLabel labAffMessage;

    //Initialisation des listeners
    Listener clientListener;
    ControlButtonNextTurn controlButtonNextTurn;

    //numClient pour pouvoir identifier si c'est le joueur
    // 1 ou 2, de base il sera mis à 0 (-> conduis à une erreur si
    // il n'est pas mis a jour, sinon prendra la valeur de l'id
    // qu'il a lors de la connection (soit 1 ou 2 pour la plupart
    // des cas.
    int numJoueur = 0;

    public ClientProg(){
        super("client");
        //A faire avant initialisation de la fenetre
        //Connection au server (JOptionPane)
        connectToServer();

        //Demander le nom de l'utilisateur pour ce client (JOptionPane)
        askUserName();

        //Initialisation des composants
        init();


        //ajout des composants de la fenetre
        ajoutComponents();
        System.out.println(numJoueur);
        client.start();

        //Parametre de la fenetre
        setSize(640, 200);
        setLocation(1700,800);
        System.out.println(numJoueur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void init(){
        client = new Client();
        Network.register(client);

        chat = new Chat(this,host,client, name);

        clientListener = new ClientListener(client, chat, name, this);
        client.addListener(clientListener);

        etat = 1;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        labAffMessage = new JLabel("Message de base");

        labelTest = new JLabel("TEST");
        labelTest.setText(name);

        ecranJeu = new EcranJeu();

        ecranAttente = new EcranAttente();

        butNextTurn = new JButton("Next turn");

        controlButtonNextTurn = new ControlButtonNextTurn(this, client);
        butNextTurn.addActionListener(controlButtonNextTurn);

    }

    private void ajoutComponents(){
        //panel general
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Partie Centre
        panel.add(labelTest,BorderLayout.CENTER);

        //Partie Gauche
        partieGauche = new JPanel();
        ajoutEcranJeu();
        partieGauche.add(panPartieJeu);
        partieGauche.add(butNextTurn, BorderLayout.SOUTH);
        panel.add(partieGauche, BorderLayout.WEST);

        //Partie haute
        panel.add(labAffMessage, BorderLayout.NORTH);

        //Partie droite
        ajoutChatDansFenetre();

        setContentPane(panel);
    }

    public void ajoutEcranJeu(){
        panPartieJeu = new JPanel(new BorderLayout());
        panPartieJeu.add(ecranJeu,BorderLayout.CENTER);
    }

    public void ajoutEcranAttente(){
        panPartieJeu = new JPanel(new BorderLayout());
        panPartieJeu.add(ecranAttente,BorderLayout.CENTER);
    }

    public void changeEtat(int state, JPanel pan){ //0 pour attente et 1 pour jouer
        Container parentDuPan = pan.getParent();
        parentDuPan.remove(pan);

        if(state == 0){ //si on veut mettre l'etat a 0 -> pendant que l'autre joue
            ajoutEcranAttente();
            partieGauche.add(panPartieJeu);
            panel.add(partieGauche, BorderLayout.WEST);
            this.etat=0;
        }else if (state == 1){ //si on veut mettre l'etat a 1 -> pour pouvoir jouer
            ajoutEcranJeu();
            partieGauche.add(panPartieJeu);
            panel.add(partieGauche, BorderLayout.WEST);
            this.etat = 1;
        }else {
            System.out.println("Le numero d'etat que vous avez demande n'est pas possible");
        }
        revalidate();
    }

    private void ajoutChatDansFenetre(){
        panel.add(chat, BorderLayout.EAST);
    }

    private void connectToServer(){
        String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE,
                null, null, "localhost");
        if (input == null || input.trim().length() == 0) System.exit(1);
        host = input.trim(); //permet d'enlever les espaces et d'uniformisé le texte
    }

    private void askUserName(){
        String input = (String)JOptionPane.showInputDialog(null, "Name:", "Connect to chat server", JOptionPane.QUESTION_MESSAGE, null,
                null, "Test");
        if (input == null || input.trim().length() == 0) System.exit(1);
        name = input.trim();
    }

    public int getEtat() {
        return etat;
    }

    public JPanel getPanPartieJeu() {
        return panPartieJeu;
    }

    public JPanel getPartieGauche() {
        return partieGauche;
    }

    public void setMessageLabelText(final JLabel label, final String text){
        label.setText(text);
        label.paintImmediately(label.getVisibleRect());
    }

    public void setNumJoueur(int numJoueur) {
        this.numJoueur = numJoueur;
    }

    public void starterEcran(int numJoueur){
        System.out.println(numJoueur);
        if(numJoueur == 1){
            System.out.println("Vous etes le client 1 ");
        }else if(numJoueur == 2 ){
            System.out.println("Vous etes le client 2 ");
        }else
            System.out.println("Veuillez redémarrez server + client");
    }

    public static void main(String[] args) {
        ClientProg test = new ClientProg();
    }
}
