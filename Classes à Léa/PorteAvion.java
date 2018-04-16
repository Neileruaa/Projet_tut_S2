public class PorteAvion extends Bateau{
    public PorteAvion() {
        boolean[] vie;
        vie = new boolean[]{true, true, true, true, true};
        Bonus bonus = new Bombe();
        // Coordonnee position = new Coordonnee(placeBateau);
        // Image img = new Image(png);
        super(5, vie, bonus, position, img);
    }
}
