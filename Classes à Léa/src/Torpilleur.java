public class Torpilleur extends Bateau{
    public Torpilleur() {
        boolean[] vie;
        vie = new boolean[]{true, true};
        Bonus bonus = new TimerBonus();
        Image imgAvant = new Image();
        Image imgApres = new Image();
        super(5,2,"Torpilleur",vie, bonus,0,0, imgAvant, imgApres, true);
    }
}
