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
            {1,5,0,0,0,0,0,0,0,0,0,1},
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

    // IL FAUDRA UN READER POUR LIRE LES PLATEAUX
    
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

/* methode de tire classique */
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
                    if(look(idTrouve,plateau)){
                        System.out.println("vous avez coule le "+switchBateauTrouve(idTrouve));
                    }
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

  /* regarde dans la matrice si le bateau est dans la matrice ou non
  si il est DEJA DEDANS renvoie FALSE
  */
     public static boolean look(int idBateau, int[][] plateau) {
         for(int i=0; i<plateau.length;i++){
             for(int j=0; j<plateau[i].length;j++) {
                 if(plateau[i][j]==idBateau){return false;}
             }
         }
         return true; // true car il n'est pas dans la matrice
     }

     /* retourne le bateau en fonction de l'id donné en parametre*/
     public static String switchBateauTrouve(int idTrouve){
         switch(idTrouve){
              case 2:
                    return "corvette";
              case 3:
                    return "sous marin";
              case 4:
                    return "croiseur";
              case 5:
                    return "cuirasse";
              case 6:
                    return "porte avion";
         }
         return "";
     }




}
