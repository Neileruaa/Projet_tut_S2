package Reseau;

import javax.swing.*;
import java.awt.*;

public class EcranJeu extends JPanel {
    //Panel qu'on renvoie avec cette classe
    JPanel panGen;

    //Liste de composants dont on va synchroniser le contenu
    JTextField tfNom;
    JTextField tfAge;
    JTextField tfNbBateau;

    public EcranJeu() {

        init();

        addComponents();

        add(panGen);
    }

    private void init(){

        tfNom = new JTextField("Votre nom");
        tfNom.setColumns(10);
        tfAge = new JTextField("Votre age");
        tfAge.setColumns(10);
        tfNbBateau = new JTextField("Nb de bateau");
        tfNbBateau.setColumns(10);
    }

    private void addComponents(){
        //Initialisation panel general qu'on renvoie
        panGen = new JPanel(new BorderLayout());

        //Panel pour les composants à synchroniser
        JPanel panComp = new JPanel();
        panComp.setLayout(new BoxLayout(panComp, BoxLayout.Y_AXIS));

        panComp.add(tfNom);
        panComp.add(tfAge);
        panComp.add(tfNbBateau);

        //Ajout des composants au panel principales pour faire un module
        panGen.add(panComp,BorderLayout.CENTER);
    }

    public String getTfNomText() {
        return tfNom.getText();
    }

    public String getTfAgeText() {
        return tfAge.getText();
    }

    public String getTfNbBateauText() {
        return tfNbBateau.getText();
    }
}
