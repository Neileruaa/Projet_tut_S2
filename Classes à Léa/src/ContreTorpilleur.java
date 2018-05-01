public class ContreTorpilleur extends Bateau{
    public ContreTorpilleur() {
        boolean[] vie;
        vie = new boolean[]{true, true, true};
        Bonus bonus = new LeurreBonus();
        Image imgAvant = new Image();
        Image imgApres = new Image();
        super(3,3,"Contre torpilleur", vie, bonus,0,0, imgAvant, imgApres, true);
    }
}
