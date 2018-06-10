package Reseau;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlButtonNextTurn extends Listener implements ActionListener {

    ClientProg fenetre;
    Client client;



    public ControlButtonNextTurn(ClientProg fenetre, Client client) {
        this.fenetre = fenetre;
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int etatActuel = fenetre.getEtat();
        int etatAMettre = 2;

        String nom = fenetre.ecranJeu.getTfNomText();
        String age = fenetre.ecranJeu.getTfAgeText();
        int nbBateau=Integer.parseInt(fenetre.ecranJeu.getTfNbBateauText());


        if (etatActuel == 0){
            //On est donc en attente et on veut basculer sur l'écran de jeu
            etatAMettre = 1;
        }else if (etatActuel == 1){

            Network.gameParameter gameParameter = new Network.gameParameter();

            gameParameter.playerName = nom;
            gameParameter.playerAge = age;
            gameParameter.shipNumber = nbBateau;

            client.sendTCP(gameParameter);

            etatAMettre = 0;
        }else
            System.out.println("probleme dans les etats");

        System.out.println(fenetre.numJoueur);
        fenetre.changeEtat(etatAMettre, fenetre.getPanPartieJeu());
    }
}
