class LeurreBonus extends Bonus{
  private BateauLeurre leurre;
  public Leurre(){
    this.leurre=new BateauLeurre();
    Image img = new Image();
    super("Bateau Leurre", false, img);
  }
}
