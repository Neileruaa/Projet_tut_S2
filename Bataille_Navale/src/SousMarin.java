public class SousMarin extends Bateau{
    public SousMarin() {
        boolean[] vie;
        vie = new boolean[]{true, true, true};
        Bonus bonus = new Bonus("SousMarin");
        // Coordonnee position = new Coordonnee(placeBateau);
        // Image img = new Image(png);
        super(5, vie, bonus, position, img);
    }
}
