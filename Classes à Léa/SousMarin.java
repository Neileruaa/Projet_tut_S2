public class SousMarin extends Bateau{
    public SousMarin() {
        boolean[] vie;
        vie = new boolean[]{true, true, true};
        Bonus bonus = new Bonus();
        // Coordonnee position = new Coordonnee(placeBateau);
        Image img = new Image();
        super(5, vie, bonus, position, img);
    }
}
