import java.io.*;
import java.util.*;

public class test {
  static final Scanner input = new Scanner(System.in);

  //bateau = new Bateau(6,5,0,0,porteAvion);
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

    /* matrice vide ou l'on met les bateaux */
    int plateau[][] = {     // j'ai rajouter des 1 pour l'utilisation du switchLook
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {1,7,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1}
    };

    System.out.println(totalLook(plateau));
    tire(plateau,1,1); // comme il y a les 1 c'est relou
    System.out.println(totalLook(plateau));
    // a modif le look(bateau) en look(int)
    // ou je ferai bateau.getType()
    // je modif le IF avec le int
  }

  /* retournera true si un bateau est encore présent
     donc false si quelqu'un a perdu
  */
  public static boolean totalLook(int[][] plateau){ // reutiliser look apres
    for(int i=0; i<plateau.length;i++){
        for(int j=0; j<plateau[i].length;j++) {
            if(plateau[i][j]==2 || plateau[i][j]==3 || plateau[i][j]==4
                || plateau[i][j]==5 || plateau[i][j]==6){
              return true;
            }
        }
    }
    return false;
  }

  public static void tire(int[][] plateau, int x , int y){
    int idTrouve;
    for(int i=0; i<plateau.length;i++){
        for(int j=0; j<plateau[i].length;j++) {
            if(x==i && y==j){
                if((plateau[i][j]==2 || plateau[i][j]==3 || plateau[i][j]==4
                  || plateau[i][j]==5 || plateau[i][j]==6)
                ){
                    System.out.println("touche "+plateau[i][j]); //touche donc on modif
                    idTrouve=plateau[i][j];
                    plateau[i][j]=7; // on modif la matrice pour montrer que cette case a deja été touché
                    System.out.println("modif "+plateau[i][j]);
                    //look(idTrouve);
                }else if(plateau[i][j]==7){
                    System.out.println("case deja touche choisissez en une autre");
                }else if(plateau[i][j]==0){
                    System.out.println("raté "+plateau[i][j]);
                    plateau[i][j]=7;
                    System.out.println("modif "+plateau[i][j]);
                }
                // switch(plateau[i][j]){
                //   case 2:
                //   case 3:
                //   case 4:
                //   case 5:
                //   case 6:
                // }
            }
        }
    }
  }

  /* regarde dans la matrice si le bateau est dans la matrice ou non si il est DEJA DEDANS renvoie FALSE*/
    // public static boolean look(int idBateau) {
    //     for(int i=0; i<plateau.length;i++){
    //         for(int j=0; j<plateau[i].length;j++) {
    //             if(plateau[i][j]==idBateau){return false;}
    //         }
    //     }
    //     return true; // true car il n'est pas dans la matrice
    // }




}
