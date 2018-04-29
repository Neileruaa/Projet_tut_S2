import Map.BatoTEST;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;


public class ScreenPlayer extends BasicGameState{
    private boolean finPartie;
    private Image ctrl;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private TiledMap map;
//    private ArrayList<Tile> tiles;

    private BatoTEST bateau;
    private Image croiseur;

    private int posX;
    private int posY;


    private int[] space = {90,180,270,360,450,540,630,720,810,900};

    private String depose;
    /* variable depose (pour les changement de bateaux : PA = porte avion ; Cu = cuirassé ;
        SM = sous-marin ; Co = corvette
        changera en fonction du bouton sélectionné
    */
    private Image img;
    private Image imgDefaut;

    /* matrice vide ou l'on met les bateaux */
    int plateau[][] = {     // j'ai rajouter des 1 pour l'utilisation du switchLook
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,1},
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

    boolean doubleClic= false;
    int modulo;
    int nbClicDroit;

    public ScreenPlayer(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
//        grille=new Image("res/Images/900.jpg");
        ctrl=new Image("res/Images/540.jpg");
        map = new TiledMap("res/Map/Map900x900.tmx");
        img = new Image("res/Images/croiseur.png");

        img.rotate(90);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
//        graphics.drawImage(grille, 0,0);
        graphics.drawImage(ctrl,900,0);
        Input input = gameContainer.getInput(); // pour le clic

        int posX= Mouse.getX();
        int posY=900-Mouse.getY();
//        switchDepose(choixBateau(posX,posY));

        depose = "C"; // je simule le fait que le joueur à choisit le croiseur en cliquant sur le bouton "poser le croiseur"

        croiseur = new Image("res/Images/croiseur.png");
        /* porteAvion = ... */
        imgDefaut = new Image("res/Images/icon.ico"); // une image par defaut pour init la variable (rend compilable)

        map.render(0,0,0,0,900,900);
//        img.rotate(90);
        if (bateauPose()==false) {
            graphics.drawImage(img, 990, 90); // images des bateaux pour leur placement (à gauche de la fenêtre)
            graphics.drawImage(img, 990, 175);
            graphics.drawImage(img, 990, 255);
            graphics.drawImage(img, 990, 335);
            graphics.drawImage(img, 990, 415);
        }
        int[] caseSup = findIdTile(posX, posY);
        if((posX>caseSup[0]-90 && posX<caseSup[0])
                && (posY>caseSup[1]-90 && posY<caseSup[1]) ){
            graphics.setColor(new Color(255,0,255,0.5f));
            graphics.fillRect(caseSup[0]-90, caseSup[1]-90, 90, 90);
        }


        if(Mouse.isButtonDown(1) && !Mouse.isButtonDown(0) && doubleClic==false){ // empeche l'apparition de 2 bateaux lorsque l'on clique sur le bouton droit et gauche
            posX = Mouse.getX();
            posY = 900 - Mouse.getY();
            Image bateauIm=imgDefaut;

            bateauIm=switchDepose(depose);
            bateauIm.rotate(90); // BOGUE QD ON PLACE LE BATEAU CELA ROTATE AUSSI LE BATEAU DEJA POSE
            // CAR CELA ROTATE AUSSI L'image du switchDepose(depose) donc croiseur

            bateau = new BatoTEST(caseSup[0]-180,caseSup[1]-180,croiseur);
            graphics.drawImage(bateauIm,caseSup[0]-180, caseSup[1]-180);
            System.out.println(bateau.toString());
            System.out.println(caseSup[0]);
            System.out.println(caseSup[1]);

            bateauIm.rotate(-90); // retour à la normal comme ca CORRIGE le BOGUE

        }


        /*mouseClicked(0,200,300,2);*/
        if (doubleClic==true) { // si on fait un double clic
            /*System.out.println("double clic");*/
            if(input.isMousePressed(input.MOUSE_RIGHT_BUTTON) && posX>0 && posX<900 && posY>0 && posY<900) { // si on fait un click DROIT dans la grille
                System.out.println("bouton droit");
                nbClicDroit++;
                modulo=nbClicDroit%4;
            }
            if (switchLook(modulo,bateau,caseSup) && look(bateau)){ //si pas 2 fois le mm bateau et que rien ne gene
                /*System.out.println("vous pouvez placer le bateau");*/
                caseSup = findIdTile(posX, posY);
                switchView(modulo,bateau,graphics,caseSup);
                if(input.isMousePressed(input.MOUSE_LEFT_BUTTON) && posX>0 && posX<900 && posY>0 && posY<900){ // si on fait un click GAUCHE dans la grille
                    System.out.println("bouton gauche");
                    switchRempli(modulo, bateau,caseSup); // on rempli la matrice

                    nbClicDroit=0; // on remet tt à zero
                    modulo=0;
                    doubleClic=false;
                }
            } else {
                if (look(bateau)==false){
                    System.out.println("Se bateau est deja posé");
                    doubleClic=false;
                }
                if (switchLook(modulo,bateau,caseSup)==false){
                    System.out.println("quelque chose gene le placement de bateau (un bord ou un autre bateau)");
                }
            }
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

        if(Mouse.isButtonDown(0) && !Mouse.isButtonDown(1) && doubleClic==false){
            posX = Mouse.getX();
            posY = 900 - Mouse.getY();
            Image bateauIm=imgDefaut;
            bateauIm=switchDepose(depose);

            bateau = new BatoTEST(caseSup[0]-90,caseSup[1]-90,croiseur);
            graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-90);   // bateau fixed
            System.out.println(bateau.toString());
        }


        pose(graphics); // pose les bateaux


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //dans une condition il y aura un test pour savoir si la partie est finie, si c'est le cas alors la variable  finPartie sera mis à true
        if (finPartie){
            //besoin de créer une méthode pour savoir si le joueur à gagner ou non (pour l'écran de fin
            stateBasedGame.enterState(4);
        }
    }

    @Override
    public int getID() {
        return 1;
    }
//    public boolean verifGagne(){
//        if (/** ici : si il un des joueurs n'a plus de bateaux alors true**/){
//            return true;
//        }
//        else return false;
//    }
/* regarde dans la matrice si le bateau est déjà dans la matrice ou non si il est DEJA DEDANS renvoie FALSE*/
public boolean look(BatoTEST bateau) {
    for(int i=0; i<plateau.length;i++){
        for(int j=0; j<plateau[i].length;j++) {
            if (plateau[i][j]==bateau.idBateau){return false;}
        }
    }
    return true; // true car pret a etre placé (il n'est pas dans la matrice)
}

    /* si il n'y a rien qui gene pour le placer (bord ou autres bateaux) retourne true */
    public boolean switchLook(int modulo, BatoTEST bateau,int[] caseSup) {
        int colonne=caseSup[0]/90; // ici on calcule les indices en fct des coordonnées pour pouvoir les réutilisés dans la matrice
        int ligne=caseSup[1]/90;
        /*System.out.println(colonne);
        System.out.println(ligne);*/

        switch(modulo) // pour le moment comme on a que le croiseur
        {
            case 0: // vertical vers le bas
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne][ligne+i]!=0){return false;}
                }
                break;
            case 1: // horizontal vers la gauche
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne-i][ligne]!=0){return false;}
                }
                break;
            case 2: // vertical vers le haut
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne][ligne-i]!=0){return false;}
                }
                break;
            case 3: // vertical vers la droite
                for(int i=0; i<bateau.tailleBateau;i++){
                    if (plateau[colonne+i][ligne]!=0){return false;}
                }
                break;
        }
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
    public void switchRempli(int modulo, BatoTEST bateau, int[] caseSup ) { // mise dans la matrice du bateau qui vient d'etre posé
        int colonne=caseSup[0]/90; // ici on calcule les indices en fct des coordonnées pour pouvoir les réutilisés dans la matrice
        int ligne=caseSup[1]/90;

        switch(modulo)
        {
            case 0: // vertical vers le bas
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne][ligne+i]=bateau.idBateau;
                    System.out.println(plateau[colonne][ligne+i]);
                }
                break;
            case 1: // horizontal vers la gauche
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne-i][ligne]=bateau.idBateau;
                    System.out.println(plateau[colonne-i][ligne]);
                }
                break;
            case 2: // vertical vers le haut
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne][ligne-i]=bateau.idBateau;
                    System.out.println(plateau[colonne][ligne-i]);
                }
                break;
            case 3: // vertical vers la droite
                for(int i=0; i<bateau.tailleBateau;i++){
                    plateau[colonne+i][ligne]=bateau.idBateau;
                    System.out.println(plateau[colonne+i][ligne]);
                }
                break;
        }

    }

    /* on lit la matrice a chaque fois comme ca on peut mettre le pose dans le render c'est plus facile.
     * En fct de l'idBateau trouvé dans la matrice on pose le/les bateaux (sa se refresh a chaque fois donc dés que l'on pose un bateau
     * on le verra apparaitre sur la grille).
     *
     * un peu compliqué en faite je pense
     */

    /* pour corriger je doit add un break qq part + modif mon if(k!=i) */
    public void pose(Graphics graphics) { // pour POSER LE BATEAU EN FIXE
        int idSauv=0;
        java.util.List<Integer> sauv = new ArrayList<>(); //sauvegarde les bateaux déjà mis sur le plateu
        /*System.out.println("test0"); // celui la s'affiche*/
        for(int i=0; i<plateau.length;i++){ // on parcours le plateau
            for(int j=0; j<plateau[i].length;j++) {
                if (plateau[i][j]!=0 && plateau[i][j]!=1 && testPlateau(sauv,plateau[i][j])){ // si on trouve un bateau (different des precedents)
                    idSauv=plateau[i][j]; // on sauvegarde l'id du bateau trouvé
                    sauv.add(idSauv);
                    for(int k=i; k<plateau.length;k++) { // on cherche UN AUTRE MORCEAU du bateau à partir de ou on a trouver le premier morceau
                        for (int l = j; l < plateau[k].length; l++) {
                            /*System.out.println("test5"); // s'affiche aussi*/
                            if(plateau[k][l]==idSauv && idSauv!=0 //qd on a trouvé cette autre morceau
                                    && (k!=i || l!=j)){ // et que celui-ci n'est pas le meme que le premier
                                /*System.out.println("test4"); // s'affiche*/
                                switchIdSauv(idSauv,l,i,j,graphics);
                                idSauv=0; // on remet a zero pour eviter de refaire d'autre bateaux
                            }
                        }
                    }
                }
            }
        }
    }

    /* Cette fonction parcours la list de int (qui sont les idBateaux) et regarde si le bateau
     * avec un identifiant id à été posé sur le plateau ou non
     * si deja posé retourne FALSE car deja dans la liste
     * si non posé retourne TRUE
     * */
    public boolean testPlateau(List<Integer> l, int id){
        for(int i=0;i<l.size();i++){
            if(l.get(i)==id){
                return false;
            }
        }
        return true;
    }

    public void switchIdSauv(int idSauv, int l, int i,int j, Graphics graphics) { // pour que l'on voit le bateau
        switch(idSauv){ // en fct du bateau
            case 2: //corvette
                // en fct de ou ce situais le 2e morceau du bateau on met l'image dans la position adéquate

                // POUR COMPRENDRE LA SUITE FAITE LE PLATEAU EN DESSIN
                // ici A est le premier "morceau" de bateau et B le deuxieme
                // il faut bien avoir en tête que l'on parcours le plateau de GAUCHE à DROITE
                // le plateau est en "miroir" (en java l'origine est en haut à gauche)
                // --- donc :
                // if(l==j)  si B se trouve sur une colonne d'indice different de celle de A
                //              JE CROIS que l'on met pas de different mais == pcq les y vont vers le
                //              bas (symetrie par rapport à l'axe des x du plan que l'on connait)
                //              (ou alors du au fait qu'on a le posY=900-Mouse.getY();)
                // ------------> on dessine le bateau à l'horizontal vers la droite
                // SINON c'est que B se trouve sur une ligne d'indice different de celle de A donc
                // ------------> on dessine le bateau à la vertical vers le bas
                // ici pas besoin de placement à l'horizontal vers la gauche et de placement
                // à la vertical vers le haut vue comme nous parcourons le plateau.

                if(l==j){  // bateau à l'horizontal vers la droite
                    croiseur.rotate(270);
                    graphics.drawImage(croiseur,i*90,j*90-180);
                    System.out.println("test");
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(croiseur,i*90-90,j*90-90);
                    System.out.println("test1");
                }
                break;
            case 3: // sous-marin
                if(l==j){  // bateau à l'horizontal vers la droite
                    croiseur.rotate(270);
                    graphics.drawImage(croiseur,i*90,j*90-180);
                    System.out.println("test");
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(croiseur,i*90-90,j*90-90);
                    System.out.println("test1");
                }
                break;
            case 4: // croiseur
                if(l==j){  // bateau à l'horizontal vers la droite
                    croiseur.rotate(270);
                    graphics.drawImage(croiseur,i*90,j*90-180);
                    /*System.out.println("test");*/
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(croiseur,i*90-90,j*90-90);
                    /*System.out.println("test1");*/
                }
                break;
            case 5: // cuirassé
                if(l==j){  // bateau à l'horizontal vers la droite
                    croiseur.rotate(270);
                    graphics.drawImage(croiseur,i*90,j*90-180);
                    System.out.println("test");
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(croiseur,i*90-90,j*90-90);
                    System.out.println("test1");
                }
                break;
            case 6: // porte avion
                if(l==j){  // bateau à l'horizontal vers la droite
                    croiseur.rotate(270);
                    graphics.drawImage(croiseur,i*90,j*90-180);
                    System.out.println("test");
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(croiseur,i*90-90,j*90-90);
                    System.out.println("test1");
                }
                break;
        }
    }

    public void switchView(int modulo, BatoTEST bateau,Graphics graphics,int[] caseSup) { // pour que l'on voit le bateau
        /*posX=Mouse.getX();
        posY=900-Mouse.getY();
        int[] caseSup = findIdTile(posX, posY);*/
        Image bateauIm=switchDepose(depose);
        switch(modulo)
        {
            case 0: // vertical vers le bas
                graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-90);
                break;
            case 1: // horizontal vers la gauche
                bateauIm.rotate(90);
                graphics.drawImage(bateauIm,caseSup[0]-180, caseSup[1]-180);
                break;
            case 2: // vertical vers le haut
                bateauIm.rotate(180);
                graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-270);
                break;
            case 3: // vertical vers la droite
                bateauIm.rotate(270);
                graphics.drawImage(bateauIm,caseSup[0], caseSup[1]-180);
                break;
        }
    }

    public String choixBateau(int posX, int posY){
        String depose="";
        if ((posX>918 && posX<184) && (posY>1166 && posY<258)){
            if (Mouse.isButtonDown(0)){
                depose="C";
                System.out.println("test1");
                return depose;
            }
        }
        if ((posX>918 && posX<275) && (posY>1166 && posY<343)){
            if (Mouse.isButtonDown(0)){
                depose="PA";
                System.out.println("test2");
                return depose;
            }
        }
        if ((posX>918 && posX<352) && (posY>1166 && posY<424)){
            if (Mouse.isButtonDown(0)){
                depose="Cu";
                System.out.println("test3");
                return depose;
            }
        }
        if ((posX>918 && posX<438) && (posY>1166 && posY<503)){
            if (Mouse.isButtonDown(0)){
                depose="S";
                System.out.println("test4");
                return depose;
            }
        }
        if ((posX>918 && posX<515) && (posY>1166 && posY<584)){
            if (Mouse.isButtonDown(0)){
                depose="Co";
                System.out.println("test5");
                return depose;
            }
        }
        return depose;
    }

    public Image switchDepose(String depose){ // pour le choix des bateau il change les images A REVOIR : ne pas donner en paramètre l'image mais changer l'image direct dans les cases
        Image bateauIm=croiseur;
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
        return coord; //retourne un tableau avec x et y correspondant au point en haut a gauche de la case ou on clic
    }

    public void mouseClicked(int button, int x, int y, int clickCount) { //detecte le double clic on redefini la methode de slick2D ici
        int mouseX = x;
        int mouseY = y;

        if (clickCount==2 && mouseX>0 && mouseX<900 && mouseY>0 && mouseY<900){ // si on double click dans la grille
            doubleClic = true;
        }
    }
    public void mousePressed(int button, int x, int y) { //detecte le simple clic on redefini la methode de slick2D ici aussi
        int mouseX = x;
        int mouseY = y;
        /*nbDoubleClic=0;*/
    }
    public boolean bateauPose(){
        boolean tousBateauxPoses=false;
        boolean[] tabId=new boolean[5];
        for (int i=0; i<10;i++){
            for (int j=0;j<10;j++){
                switch (plateau[i][j]){
                    case 2: tabId[0]=true;
                    break;
                    case 3: tabId[1]=true;
                    break;
                    case 4: tabId[2]=true;
                    break;
                    case 5: tabId[3]=true;
                    break;
                    case 6: tabId[4]=true;
                    break;
                }
            }
        }
        for (int i=0; i<tabId.length;i++){
            if (tabId[i]==false){
                return tousBateauxPoses;
            }
        }
        tousBateauxPoses=true;
        return tousBateauxPoses;
    }
}