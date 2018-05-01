class TimerBonus extends Bonus{
  private Fenetre fenetre;
  private ControlTimer ct;
  public TimerBonus(){
    Image img = new Image();
    super("Temps Surprise", false, img);
  }
  public void changerTimer(){
    ControlTimer ct = new ControlTimer(fenetre,10);
//    f.bTimer.addActionListener(ct);
  }
}
