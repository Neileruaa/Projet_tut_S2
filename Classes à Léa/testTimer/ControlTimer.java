import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
class ControlTimer implements ActionListener {
    public int temps;
    private Color vert= new Color(0,255,0);
    private Timer timer;
    public Fenetre f;
    public ControlTimer() {
    }

    public ControlTimer(Fenetre f,int temps) {
      this.temps=temps;
      this.f = f;
      this.timer=new Timer(1000, this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==f.bTimer){ // bouton start
            timer.start();
        }
        f.temps.setText("Temps restants : "+temps+ "s");
        temps--;
        /*
            Si le temps arrive à -1 (sinon on voit pas le 0) ou si le bouton fin du tour est activé alors on stop le timer,
            on regarde si le timer est bien stop,
            dans ce cas on change de joueur, on repasse le temps à 60 puis on restart
         */
        if (temps<=-1 || e.getSource()==f.finTour) {
            timer.stop();
            if (!timer.isRunning()){
                if (f.joueur.getText()=="Joueur1"){
                    f.joueur.setText("Joueur2");
                }else{
                    f.joueur.setText("Joueur1");
                }
                temps=60;
                timer.restart();
            }
        }
        /*
        Si le bouton Bonus est activé le temps passe à 10 (faudra mettre pour le joueur d'après)
         */
        if (e.getSource()==f.bBonusTimer){
            temps=10;
        }
    }
}
