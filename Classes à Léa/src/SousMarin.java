public class SousMarin extends Bateau{
    public SousMarin() {
        boolean[] vie;
        vie = new boolean[]{true, true, true};
        Bonus bonus = new Bonus();
        Image imgAvant = new Image();
        Image imgApres = new Image();
        super(4,3,"Sous-marin", vie, bonus,0,0, imgAvant, imgApres, true);
    }
}
