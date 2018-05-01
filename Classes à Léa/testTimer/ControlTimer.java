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
        if (e.getSource()==f.bTimer){
            timer.start();
        }
        f.temps.setText("Temps restants : "+temps+ "s");
        temps--;
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
        if (e.getSource()==f.bBonusTimer){
            temps=10;
        }
    }
}
