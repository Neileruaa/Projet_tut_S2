public class Bateau {
    private int taille;
    private boolean[] vie;
    private Bonus bonus;
    private Coordonnee position;
    private Image img;
    private boolean estCoule=false;

    public Bateau(int taille, boolean[] vie, Bonus bonus, Coordonnee position, Image img) {
        this.taille = taille;
        this.vie = vie;
        this.bonus = bonus;
        this.position = position;
        this.img = img;
    }

    public int getTaille() {
        return taille;
    }

    public boolean[] getVie() {
        return vie;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public Coordonnee getPosition() {
        return position;
    }

    public Image getImg() {
        return img;
    }
    public boolean estTouche() {
        for (int i=0; i<vie.length; i++) {
            if (!vie[i]) {
                //changerImage()
                return true;
            }
        }
        return false;
    }

    public boolean estCoule(){
        for (int j=0 ; j<vie.length;  j++){
            if (vie[j]) {
                // addPointJoueur()
                return false;
            }
        }
        return true;
    }
}
