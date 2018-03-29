public class Torpilleur extends Bateau{
    public Torpilleur() {
        boolean[] vie;
        vie = new boolean[]{true, true};
        Bonus bonus = new Bonus("Torpilleur");
        // Coordonnee position = new Coordonnee(placeBateau);
        // Image img = new Image(png);
        super(5, vie, bonus, position, img);
    }
}
