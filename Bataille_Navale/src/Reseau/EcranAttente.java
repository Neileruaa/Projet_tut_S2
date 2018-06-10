package Reseau;

import javax.swing.*;
import java.awt.*;

public class EcranAttente extends JPanel {

    //Panel qu'on renvoie avec cette classe
    JPanel panGen;

    JLabel labAffichageEtat;

    public EcranAttente() {

        init();

        addComponents();

        add(panGen);
    }

    private void init(){
        panGen = new JPanel(new BorderLayout());

        labAffichageEtat = new JLabel("N'est pas encore initialisé");
    }

    private void addComponents(){
        panGen.add(labAffichageEtat);
    }
}
