public class Croiseur extends Bateau{
    public Croiseur() {
        boolean[] vie;
        vie = new boolean[]{true, true, true, true};
        Bonus bonus = new Bonus();
        // Coordonnee position = new Coordonnee(placeBateau);
        Image img = new Image();
        super(4, vie, bonus, position, img);
    }
}
