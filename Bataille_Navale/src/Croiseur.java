public class Croiseur extends Bateau{
    public Croiseur() {
        boolean[] vie;
        vie = new boolean[]{true, true, true, true};
        Bonus bonus = new Bonus("Croiseur");
        // Coordonnee position = new Coordonnee(placeBateau);
        // Image img = new Image(png);
        super(4, vie, bonus, position, img);
    }
}