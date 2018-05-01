class BateauLeurre extends Bateau{

  public BateauLeurre(){
    boolean[] vie;
    vie = new boolean[]{true, true};
    Bonus bonus = new Bonus();
    Image imgAvant = new Image();
    Image imgApres = new Image();
    super(6,2,"Bateau leurre", vie, bonus,0,0, imgAvant, imgApres, true);
  }
}
