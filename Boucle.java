import java.io.*;
import java.util.*;

public class Boucle {
  static final Scanner input = new Scanner(System.in);


  public static void main(String[] args){
    int x=0;
    int y=0;
    String depose; // variable de l'utilisateur (choix du bateau)
    String PA="PA"; // porte avion
    String PC="PC"; // Croiseur
    String PCu="PCu"; // Cuirasse
    String PS="PS"; // Sous-marin
    String PCo="PCo"; // Corvette
    int colonne=0;
    int ligne=0;

//les 1 permettent de contourner le problèmes des out of range
//les 1 empeche les bateaux de sortir du plateau de jeu (donc de la matrice/tableau)
    int plateau[][] = {{0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0,0,0,0,1},
                       {1,1,1,1,1,1,1,1,1,1,1}
                     };

    System.out.println("x = ");
    x=input.nextInt();
    System.out.println("y = ");
    y=input.nextInt();

    // verifie si l'utilisateur ne clique pas en dehors du plateau de jeu
    if((100<x && x<600) && (100<y && y<600)){
        colonne=((x-100)/50); // dans le calcule des lignes colonnes rajouter les bordures une fois fenetre faites
        ligne=((y-100)/50); // on retire 100 pour ne pas sortir du tableau

        System.out.println("case : ["+colonne+"]["+ligne+"]");

        // test placement des bateaux juste en remplissant le tableau
        // test avec un bateau de 5 cases a l'horizontal

        // le if verifie si un autre bateau n'occupe pas l'une des cases que devra occuper
        // le bateau en question

        // PORTE AVION (HORIZONTAL vers la droite)
        if (plateau[colonne][ligne]==0 && plateau[colonne+1][ligne]==0
            && plateau[colonne+2][ligne]==0 && plateau[colonne+3][ligne]==0
            && plateau[colonne+4][ligne]==0) {

            plateau[colonne][ligne]=1; //on remplis le tableau pour le placer
            plateau[colonne+1][ligne]=1;
            plateau[colonne+2][ligne]=1;
            plateau[colonne+3][ligne]=1;
            plateau[colonne+4][ligne]=1;
        }
        System.out.println(plateau[colonne][ligne]);
    }else{
        System.out.println("sortie du tableau");
    }

    // serais bien de faire un if -> une fonction --> un bateau
    // car + lisible a l'ecran
    // la fonction sera appeler dans le if(100<x ...) qui verifie les coordonnees
    // puis place le bateau que veux poser l utilisateur
    // (choix fait grace a la variable DEPOSE)

    int modulo = nbClic%4;
    int tailleBateau;

    public Image switchDepose(String depose, Image bateauIm){ // pour le choix des bateau il change les images
        switch (depose)
        {
            case "PA":
                bateauIm =croiseur;
                break;
            case "Cu":
                bateauIm =croiseur;
                break;
            case "C":
                bateauIm =croiseur;
                break;
            case "S":
                bateauIm =croiseur;
                break;
            case "Co":
                bateauIm =croiseur;
                break;
        }
        return bateauIm;
    }


    switch(modulo)
    {
        case 0:
            for(int i=0; i<tailleBateau;i++){
              plateau[colonne+i][ligne]=bateau.idBateau;
            }
        case 1:
        case 2:
        case 3:
    }

/* pour le remplissage de la matrice on remplira avec un numero specifique a chaque
bateaux (un identifiant donner dans la classe de chaque bateaux)*/
/* -------------------------------HORIZONTAL--------------------------------- */

    // PORTE AVION (HORIZONTAL vers la droite)
    if (plateau[colonne][ligne]==0 && plateau[colonne+1][ligne]==0
        && plateau[colonne+2][ligne]==0 && plateau[colonne+3][ligne]==0
        && plateau[colonne+4][ligne]==0) {

          for(int i=0; i<5;i++){
            plateau[colonne+i][ligne]=1;
          }
        // plateau[colonne][ligne]=1;
        // plateau[colonne+1][ligne]=1;
        // plateau[colonne+2][ligne]=1;
        // plateau[colonne+3][ligne]=1;
        // plateau[colonne+4][ligne]=1;
    }

    // CUIRASSE (HORIZONTAL vers la droite)
    if (plateau[colonne][ligne]==0 && plateau[colonne+1][ligne]==0
        && plateau[colonne+2][ligne]==0 && plateau[colonne+3][ligne]==0) {

          for(int i=0; i<4;i++){
            plateau[colonne+i][ligne]=1;
          }

        // plateau[colonne][ligne]=1;
        // plateau[colonne+1][ligne]=1;
        // plateau[colonne+2][ligne]=1;
        // plateau[colonne+3][ligne]=1;
    }

    // CROISEUR (HORIZONTAL vers la droite)
    if (plateau[colonne][ligne]==0 && plateau[colonne+1][ligne]==0
        && plateau[colonne+2][ligne]==0) {

          for(int i=0; i<3;i++){
            plateau[colonne+i][ligne]=1;
          }
        plateau[colonne][ligne]=1;
        plateau[colonne+1][ligne]=1;
        plateau[colonne+2][ligne]=1;
    }

    // SOUS-MARIN (HORIZONTAL vers la droite)
    if (plateau[colonne][ligne]==0 && plateau[colonne+1][ligne]==0
        && plateau[colonne+2][ligne]==0) {

        plateau[colonne][ligne]=1;
        plateau[colonne+1][ligne]=1;
        plateau[colonne+2][ligne]=1;
    }

    // CORVETTE (HORIZONTAL vers la droite)
    if (plateau[colonne][ligne]==0 && plateau[colonne+1][ligne]==0) {

        plateau[colonne][ligne]=1;
        plateau[colonne+1][ligne]=1;
    }


/* --------------------------------VERTICAL---------------------------------- */
    // PORTE AVION (VERTICAL vers le bas)
    if (plateau[colonne][ligne]==0 && plateau[colonne][ligne+1]==0
        && plateau[colonne][ligne+2]==0 && plateau[colonne][ligne+3]==0
        && plateau[colonne][ligne+4]==0) {

        plateau[colonne][ligne]=1;
        plateau[colonne][ligne+1]=1;
        plateau[colonne][ligne+2]=1;
        plateau[colonne][ligne+3]=1;
        plateau[colonne][ligne+4]=1;
    }

    // CUIRASSE (VERTICAL vers le bas)
    if (plateau[colonne][ligne]==0 && plateau[colonne][ligne+1]==0
        && plateau[colonne][ligne+2]==0 && plateau[colonne][ligne+3]==0) {

        plateau[colonne][ligne]=1;
        plateau[colonne][ligne+1]=1;
        plateau[colonne][ligne+2]=1;
        plateau[colonne][ligne+3]=1;
    }

    // CROISEUR (VERTICAL vers le bas)
    if (plateau[colonne][ligne]==0 && plateau[colonne][ligne+1]==0
        && plateau[colonne][ligne+2]==0) {

        plateau[colonne][ligne]=1;
        plateau[colonne][ligne+1]=1;
        plateau[colonne][ligne+2]=1;
    }

    // SOUS-MARIN (VERTICAL vers le bas)
    if (plateau[colonne][ligne]==0 && plateau[colonne][ligne+1]==0
        && plateau[colonne][ligne+2]==0) {

        plateau[colonne][ligne]=1;
        plateau[colonne][ligne+1]=1;
        plateau[colonne][ligne+2]=1;
    }

    // CORVETTE (VERTICAL vers le bas)
    if (plateau[colonne][ligne]==0 && plateau[colonne][ligne+1]==0) {

        plateau[colonne][ligne]=1;
        plateau[colonne][ligne+1]=1;
    }


  }
}
