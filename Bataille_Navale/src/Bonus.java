import javax.swing.*;
import org.newdawn.slick.Image;

/*
    Nous n'avons pas eu le temps d'intégrer les bonus
    Les classes sont crées dans le dossier "ClasseAnnexe"
 */

public class Bonus {
    private String specialite;
    private boolean etat;
    private Image img;

    public void Bonus(){} //Bonus vide pour certain Bateau

    public Bonus(String specialite, boolean etat, Image img) {
        this.specialite = specialite;
        this.etat=etat;
        this.img=img;
    }


    /* public void Deverrouiller(){
        this.etat=true;
        this.img= new Image(); // mettre l'image en couleur
       }
    public void Utiliser(){
       si on clique sur le bouton, actionne l'effet
       si on passe juste dessus avec la souris affiche un message d'instruction
    }
      public void Verrouiller(){
        this.etat=false;
        this.img = new Image(); // mettre l'image grisé
       }
    */
}
