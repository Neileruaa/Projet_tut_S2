import javax.swing.*;
import java.awt.*;

class Fenetre extends JFrame{
    JButton bTimer;
    JLabel joueur;
    JButton finTour;
    ControlTimer cTimer;
    JLabel temps= new JLabel();
    JButton bBonusTimer;

    public Fenetre(){
      creerWidget();
      ajouterWidget();
      this.setTitle("Timer");
      pack();
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
    }
    public void creerWidget(){
      this.bTimer = new JButton("Start");
      this.joueur = new JLabel("Joueur1");
      this.finTour = new JButton("Terminer le tour");
      this.bBonusTimer = new JButton("Bonus Timer");
      this.cTimer = new ControlTimer();
    }
    public void ajouterWidget(){
      JPanel p = new JPanel();
      p.add(bTimer);
      p.add(temps);
      p.add(finTour);
      p.add(joueur);
      JPanel bonus = new JPanel();
      bonus.add(bBonusTimer);
      p.add(bonus);
      ControlTimer cTimer= new ControlTimer(this,60);
      bTimer.addActionListener(cTimer);
      finTour.addActionListener(cTimer);
      bBonusTimer.addActionListener(cTimer);
      this.setContentPane(p);
    }
}
