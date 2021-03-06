import org.newdawn.slick.Image;

import java.awt.*;

public class Bateau {
    private int type;
    private int taille;
    private int xPosB;
    private int yPosB;
    
    /*
        première idée :

        checker la rotation du bateau true ou false;
        le nom sera mis dans screen player, détail d'amélioration
        un tableau de vie qui devait checker si le bateau est encore en vie ou pas
        Un bonus, devait être dévoilé un bonus si on détruisait un bateau
     */
    private Image imgAvant;
    private Image imgApres;
    private boolean rotation;
    private String nom;
    private boolean[] vie; // peut-être une liste
    private Bonus bonus;

    public Bateau(int type, int taille, int x, int y, Image img1) { //String nom, boolean[] vie, Bonus bonus, ... , Image img2 ICI TOUT EST A REMETTRE DANS LA SIGNATURE DE LA METHODE APRES QUE TOUS LES ELEMENTS MARCHENT
        this.type = type;
        this.taille = taille;
//        this.nom=nom;
//        this.vie = vie;
//        this.bonus = bonus;
        this.xPosB=x;
        this.yPosB=y;
        this.imgAvant = img1;
//        this.imgApres = img2;
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
