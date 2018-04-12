package Map;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TestTiledMap extends BasicGame {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private TiledMap map;
    private ArrayList<Tile> tiles;

    private BatoTEST bateau;
    private Image croiseur;

    private int[] space = {90,180,270,360,450,540,630,720,810,900};

    private String depose;
    /* variable depose (pour les changement de bateaux : PA = porte avion ; Cu = cuirassé ;
        SM = sous-marin ; Co = corvette
        changera en fonction du bouton sélectionné
    */
    private Image img;
    private Image imgDefaut;

    /* matrice vide ou l'on met les bateaux */
    int plateau[][] = {
            {0,0,0,0,0,0,0,0,0,0,1},
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


    public TestTiledMap()
    {
        super("Test de map");
    }
    public static void main(String[] arguments) {
        try     {
            AppGameContainer app = new AppGameContainer(new TestTiledMap());
            //app.setDisplayMode(screenSize.width, screenSize.height, true); //=> Full screen

            app.setDisplayMode(1440 , 900, false);
            app.setShowFPS(true); // true for display the numbers of FPS
            app.setVSync(true); // false for disable the FPS synchronize
            app.setShowFPS(true); // true for display the numbers of FPS
            app.start();

        }   catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException{
        map = new TiledMap("res/Map/Map900x900.tmx");
        img = new Image("res/Images/croiseur.png");
        /*img.draw(500,950,150,150);*/
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        /*graphics.fillRect(500,500,50,50);*/
        croiseur.destroy();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        depose = "C"; // je simule le fait que le joueur à choisit le croiseur en cliquant sur le bouton "poser le croiseur"

        croiseur = new Image("res/Images/croiseur.png");
        /* porteAvion = ... */
        imgDefaut = new Image("res/Images/icon.ico"); // une image par defaut pour init la variable (rend compilable)

        int posX = Mouse.getX();
        int posY = 900 - Mouse.getY();
        map.render(0,0,0,0,900,900);

        graphics.drawImage(img,950,90); // image fixe

        int[] caseSup = findIdTile(posX, posY);
        if((posX>caseSup[0]-90 && posX<caseSup[0])
                && (posY>caseSup[1]-90 && posY<caseSup[1]) ){
            graphics.setColor(new Color(255,0,255,0.5f));
            graphics.fillRect(caseSup[0]-90, caseSup[1]-90, 90, 90);
        }
        if (Mouse.isButtonDown(1) && !Mouse.isButtonDown(0)){ // empeche l'apparition de 2 bateaux lorsque l'on clique sur le bouton droit et gauche
            Image bateauIm=imgDefaut;
            /*if(depose.equals("C")) {bateauIm =croiseur;}*/

            bateauIm=switchDepose(depose,bateauIm);

            bateauIm.rotate(90);

            int posX1 = Mouse.getX();
            int posY1 = 900 - Mouse.getY();
            bateau = new BatoTEST(posX1,posY1,croiseur);
            //graphics.drawImage(croiseurRotated,caseSup[0]-180, caseSup[1]-180);
            graphics.drawImage(bateauIm,caseSup[0]-180, caseSup[1]-180);
            System.out.println(bateau.toString());
        }



        /* Pour apres il faudrait cliquer (gauche) ensuite l'image reste a la case que l'on veut puis avec le clique droit changer
         * de direction. Ou alors on garde le glissement et lors du premier clique Gauche on "valide" la case de depart.
         *
         * DANS TT LES CAS :
         * -------> utilisation des modulo avec une variable qui compte le nombre de clique droit :
          * par ex: au depart on a :
          *             int nbClicD=0;
          *             si on a un nb impaire (modulo 2 different de 0) on aura forcement une position horizontal
          *             on suppose que la pos de depart c'est la vertical vers le bas (celle faite par aurel)
          *             et que le bateau tourne dans le sens des aiguilles d'une montre
          *
          *             on part de ce principe donc :
          *             ++
          *             nbClicD=1 --> position horizontal : 1[2] != 0
          *             en revanche avec modulo 2 on a que 2 valeur possible : 0 (vertical) et 1 (horizontal)
          *             ds le mm principe : x modulo 3 --> modulo 3 = 3 valeur possible 0 , 1 , 2
          *             or nous a 4 position possible donc :
          *             x[4] --> 4 valeur possibles --> 0, 1, 2, 3
          *             si 0 = position de base (vertical vers le bas)
          *             si 1 = position horizontal vers la gauche
          *             si 2 = position vertical vers le haut
          *             si 3 = position horizontal vers la droite
          *             apres retour à 0 donc position de base et ainsi de suite
          *
          *             -----------------------------------------------------------------------
          *             il reste à savoir la modification des coordonnées (en terme de grille) :
          *
          *             pos de base = (baseX , baseY) (ici c'est uniquement le "deuxieme" points qui nous interesse
          *                                          car le premier point (ou case) c'est celle ou clique l'utilisateur
          *                                          "l'origine" )
          *             tailleBateau = c'est la taille du bateau en nb de cases
          *             tCase = ici 90 UTILISE UNIQUEMENT POUR LES COORD PAS DANS LA MATRICE CAR INUTILE
          *
          *             -> pour la matrice c'est le mm principe sans les *tCase)
          *             -> pour les coord pas besoin de for donc on remplace le i par le tailleBateau mais on garde le *tCase
          *                     comme ca on obtient uniquement le "deuxieme" point
          *
          *             switch {
          *                 case 0 : (for i allant de 0 à tailleBateau) : baseX=baseX         ; baseY=baseY+i*tCase
          *                 case 1 : (for i allant de 0 à tailleBateau) : baseX=baseX-i*tCase ; baseY=baseY
          *                 case 2 : (for i allant de 0 à tailleBateau) : baseX=baseX         ; baseY=baseY-i*tCase
          *                 case 3 : (for i allant de 0 à tailleBateau) : baseX=baseX+i*tCase ; baseY=baseY
          *             ]
          *
          *
          * PS : Aurel explique vite fait ton caseSup stp que je puisse le reutilisé pcq il est bien fait comme la position est fixe
          *                 par rapport a la case et pas par rapport au clique de l'utilisateur ;
          *                 sinon mon switch de au dessus (ou je calcule le "deuxieme" point est ambigue dans le sens
          *                 ou avec le rotate on en aura peut etre pas besoin mais ca peut etre utile pour des eventuels
          *                 remplissement de case (matrice graphique ou de la grille etc) enfin j'expliquerai ca plus tard
          *      je vais mettre mes boucles NON MODIFIE (je dois modif les tailles des cases des grilles etc dans les boucles).
          *      je dois faire le switch aussi du coup ca serai mieux je pense (moin de boucle en modifiant la
          *      variable tailleBateau en fct de la variable depose (expliquee au dessus)
            */
        if(Mouse.isButtonDown(0) && !Mouse.isButtonDown(1)){
            Image bateauIm=imgDefaut;
            /*if(depose.equals("C")) {bateauNRotated =croiseur;}*/

            bateauIm=switchDepose(depose,bateauIm);

            int posX1 = Mouse.getX();
            int posY1 = 900 - Mouse.getY();

            bateau = new BatoTEST(posX1,posY1,croiseur);
            graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-90);   // bateau fixed
            System.out.println(bateau.toString());
        }
    }


    /* regarde dans la matrice si le bateau est déjà dans la matrice ou non si il est DEJA DEDANS renvoie FALSE*/
    public boolean look(BatoTEST bateau) {
        /*for(int i=0; i<plateau.length;i++){
            for(int j=0; j<plateau[i].length;j++) {
                if (plateau[i][j]==bateau.idBateau){return false;}
            }
        }*/
        return true; // true car pret a etre placé (il n'est pas dans la matrice)
    }

    /* si il n'y a rien qui gene pour le placer (bord ou autres bateaux) retourne true */
    public boolean switchLook(int modulo, BatoTEST bateau ) {
        /* switch(modulo)
        {
            case 0: // vertical vers le bas
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne][ligne+i]!=0){return false;}
                }
            case 1: // horizontal vers la gauche
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne-i][ligne]!=0){return false;}
                }
            case 2: // vertical vers le haut
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne][ligne-i]!=0){return false;}
                }
            case 3: // vertical vers la droite
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne+i][ligne]!=0){return false;}
                }
        }*/
        return true;
    }

    /* on remplis la matrice avec l'id du bateau contenu dans les classes de léa
       on a :
       id porte avion = 6
       id Cuirasse = 5
       id croiseur = 4
       id sous marin = 3
       id Corvette = 2
    */
    public void switchRempli(int modulo, BatoTEST bateau ) { // mise dans la matrice du bateau qui vient d'etre posé
        /*switch(modulo)
        {
            case 0: // vertical vers le bas
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne][ligne+i]=bateau.idBateau;
                }
            case 1: // horizontal vers la gauche
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne-i][ligne]=bateau.idBateau;
                }
            case 2: // vertical vers le haut
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne][ligne-i]=bateau.idBateau;
                }
            case 3: // vertical vers la droite
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne+i][ligne]=bateau.idBateau;
                }
        }*/
    }

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
        return bateauIm; // retourne l'image du bateau
    }

    public int[] findIdTile(int posX, int posY){
        int[] coord= new int[2];
        for (int i = 0; i<space.length;i++){
            if (posX < space[i]){
                for (int j =0; j<space.length;j++){
                    if (posY < space[j]){
                        coord[0]=space[i];
                        coord[1]=space[j];
                        return coord;
                    }
                }
            }
        }
        return coord;
    }
}
