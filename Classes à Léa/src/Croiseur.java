public class Croiseur extends Bateau{
    public Croiseur() {
        boolean[] vie;
        vie = new boolean[]{true, true, true, true};
        Bonus bonus = new Bonus();
        Image imgAvant = new Image();
        Image imgApres = new Image();
        super(2,4,"Croiseur", vie, bonus,0,0, imgAvant, imgApres, true);
    }
}
