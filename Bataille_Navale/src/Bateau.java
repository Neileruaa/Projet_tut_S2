import java.awt.*;

public class Bateau {
    private int type;
    private int taille;
    private String nom;
    private boolean[] vie; // peut-être une liste
    private Bonus bonus;
    private int xPosB;
    private int yPosB;
    private Image imgAvant;
    private Image imgApres;
    private boolean rotation;

    public Bateau(int type, int taille, String nom, boolean[] vie, Bonus bonus, int x, int y, Image img1, Image img2) {
        this.type = type;
        this.taille = taille;
        this.nom=nom;
        this.vie = vie;
        this.bonus = bonus;
        this.xPosB=x;
        this.yPosB=y;
        this.imgAvant = img1;
        this.imgApres = img2;
    }

    public int getType() {
        return type;
    }

    public int getTaille() {
        return taille;
    }

    public String getNom() {
        return nom;
    }

    public boolean[] getVie() {
        return vie;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public int getxPosB() {
        return xPosB;
    }

    public int getyPosB() {
        return yPosB;
    }

    public Image getImgAvant() {
        return imgAvant;
    }

    public Image getImgApres() {
        return imgApres;
    }

    public boolean isHorintal() {
        return rotation;
    }
    public boolean isVertical(){
        return !rotation;
    }
    public void setRotation(boolean b){
        rotation=b;
    }

    public void setPosition(int x, int y){
        xPosB=x;
        yPosB=y;
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
                return false;
            }
        }
        return true;
    }

    public boolean dejaTouche(){
        // si il est deja touche return true, sinon return false et active estTouche()
        return true;
    }

    public void draw(){
        // interface
    }

}
