public class ContreTorpilleur extends Bateau{
    public ContreTorpilleur() {
        boolean[] vie;
        vie = new boolean[]{true, true, true};
        Bonus bonus = new Bonus("ContreTorpilleur");
        // Coordonnee position = new Coordonnee(placeBateau);
        // Image img = new Image(png);
        super(5, vie, bonus, position, img);
    }
}
