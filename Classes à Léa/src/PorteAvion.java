public class PorteAvion extends Bateau{
    public PorteAvion() {
        boolean[] vie;
        vie = new boolean[]{true, true, true, true, true};
        Bonus bonus = new Bombe();
        Image imgAvant = new Image();
        Image imgApres = new Image();
        super(1,5,"Porte avion",vie, bonus,0,0, imgAvant, imgApres, true);
    }
}
