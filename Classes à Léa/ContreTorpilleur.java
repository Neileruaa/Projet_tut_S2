public class ContreTorpilleur extends Bateau{
    public ContreTorpilleur() {
        boolean[] vie;
        vie = new boolean[]{true, true, true};
        Bonus bonus = new LeurreBonus();
        // Coordonnee position = new Coordonnee(placeBateau);
        // Image img = new Image(png);
        super(5, vie, bonus, position, img);
    }
}
