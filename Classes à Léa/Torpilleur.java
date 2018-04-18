public class Torpilleur extends Bateau{
    public Torpilleur() {
        boolean[] vie;
        vie = new boolean[]{true, true};
        Bonus bonus = new TimerBonus();
        // Coordonnee position = new Coordonnee(placeBateau);
        Image img = new Image();
        super(5, vie, bonus, position, img);
    }
}
