class BateauLeurre extends Bateau{

  public BateauLeurre(){
    boolean[] vie;
    vie = new boolean[]{true, true};
    Bonus bonus = new Bonus();
    // Coordonnee position = new Coordonnee(placeBateau);
    Image img = new Image();
    super(5, vie, bonus, position, img);
  }
}
