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
    public int timer=30000;



    public boolean bateauxPoses=false;
    public boolean finPartie;
    public Image ctrl;
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public TiledMap map;
//    public ArrayList<Tile> tiles;

    public Bateau bateau;
    public Image croiseur;
    public Image sousMarin;
    public Image porteAvion;
    public Image cuirasse;
    public Image corvette;
    public Image passeT;

    SaverReader saverReader;

    public int posX;
    public int posY;


    public int[] space = {90,180,270,360,450,540,630,720,810,900};

    public String depose;
    /* variable depose (pour les changement de bateaux : PA = porte avion ; Cu = cuirassé ;
        SM = sous-marin ; Co = corvette
        changera en fonction du bouton sélectionné
    */

    public Image imgDefaut;

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

    // test
    public Image croiseurView;
    public Image sousMarinView;
    public Image porteAvionView;
    public Image cuirasseView;
    public Image corvetteView;

    // popup
    public Image gene;
    public Image dejaPose;
    public boolean affiche;
    public Image infoPlace;


    public boolean modif=false;

    public ScreenPlayer(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        gameContainer.setDefaultMouseCursor();

//        grille=new Image("res/Images/900.jpg");
        ctrl=new Image("res/Images/captain.png");
        map = new TiledMap("res/Map/Map900x900.tmx");

        croiseurView = new Image("res/Images/croiseur.png");
        sousMarinView=new Image("res/Images/sous_marin.png");
        porteAvionView=new Image("res/Images/porteAvion.png");
        cuirasseView=new Image("res/Images/cuirasse.png");
        corvetteView=new Image("res/Images/corvette.png");

        passeT=new Image("res/Images/Suivant.png");

        // popup
        gene=new Image("res/Images/Bulle1.png");
        dejaPose=new Image("res/Images/Bulle1.png");
        infoPlace=new Image("res/Images/infoPlace.png");

        croiseurView.rotate(90);
        sousMarinView.rotate(90);
        porteAvionView.rotate(90);
        cuirasseView.rotate(90);
        corvetteView.rotate(90);

        /* important pour eviter les Nullpointerexception quand on lance le jeu */
        modif=false;
        depose="";

        saverReader = new SaverReader();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
//        graphics.drawImage(grille, 0,0);
        graphics.drawImage(ctrl,900,0);

        if(timer/1000>=0) {
            graphics.drawImage(new Image("res/Images/" + String.valueOf(timer / 1000) + ".png"), 1220, 50);
        }
        graphics.drawImage(passeT, 900,795);
        graphics.drawImage(infoPlace,900,38);

        Input input = gameContainer.getInput(); // pour le clic

        int posX= Mouse.getX();
        int posY=900-Mouse.getY();


        if(depose.equals("") && modif==false){ //si on a pas cliquer sur un bouton pour changer les bateaux on prend le croiseur par defaut
            bateau=new Bateau(4,3,0,0,croiseur);
        }

        depose=choixBateau(Mouse.getX(),900-Mouse.getY()); // on regarde le choix de l'utilisateur


        croiseur = new Image("res/Images/croiseur.png");
        sousMarin = new Image("res/Images/sous_marin.png");
        porteAvion=new Image("res/Images/porteAvion.png");
        cuirasse=new Image("res/Images/cuirasse.png");
        corvette=new Image("res/Images/corvette.png");

        imgDefaut = new Image("res/Images/icon.ico"); // une image par defaut pour init la variable (rend compilable)

        map.render(0,0,0,0,900,900);
//        img.rotate(90);

        visiblePlacementBateau(graphics);

        int[] caseSup = findIdTile(posX, posY);
        if((posX>caseSup[0]-90 && posX<caseSup[0])
                && (posY>caseSup[1]-90 && posY<caseSup[1]) ){
            graphics.setColor(new Color(255,0,255,0.5f));
            graphics.fillRect(caseSup[0]-90, caseSup[1]-90, 90, 90);
        }


        if(Mouse.isButtonDown(1) && !Mouse.isButtonDown(0) && doubleClic==false){ // empeche l'apparition de 2 bateaux lorsque l'on clique sur le bouton droit et gauche
            posX = Mouse.getX();
            posY = 900 - Mouse.getY();

            Bateau renderBateau;
            renderBateau = switchDepose(depose);
            drawImageViewHorizGauche(renderBateau,graphics,caseSup);
            affiche=false;


        }

        if(Mouse.isButtonDown(0) && !Mouse.isButtonDown(1) && doubleClic==false){
            posX = Mouse.getX();
            posY = 900 - Mouse.getY();
            switchDepose(depose);
            affiche=false;

        }

        if (doubleClic) { // si on fait un double clic
            if(input.isMousePressed(input.MOUSE_RIGHT_BUTTON) && posX>0 && posX<900 && posY>0 && posY<900) { // si on fait un click DROIT dans la grille
                nbClicDroit++;
                modulo=nbClicDroit%4;
            }
            if (switchLook(modulo,bateau,caseSup) && look(bateau)){ //si pas 2 fois le mm bateau et que rien ne gene
                caseSup = findIdTile(posX, posY);
                switchView(modulo,bateau,graphics,caseSup);
                if(input.isMousePressed(input.MOUSE_LEFT_BUTTON) && posX>0 && posX<900 && posY>0 && posY<900){ // si on fait un click GAUCHE dans la grille
                    switchRempli(modulo, bateau,caseSup); // on rempli la matrice

                    nbClicDroit=0; // on remet tt à zero
                    modulo=0;
                    doubleClic=false;
                }
            } else {
                if (!look(bateau)){
                    //System.out.println("Se bateau est deja posé");
                    affiche=true; // pour la popup
                    doubleClic=false;
                }
                if (!switchLook(modulo,bateau,caseSup)){
                    //System.out.println("quelque chose gene le placement de bateau (un bord ou un autre bateau)");
                    graphics.drawImage(gene, 945, 650);
                }
            }
        }

        if(affiche){
            graphics.drawImage(dejaPose, 945, 650);
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
        timer-=i;
        passerTour(stateBasedGame,i);
    }

    @Override
    public int getID() {
        return 1;
    }

    /* regarde dans la matrice si le bateau est déjà dans la matrice ou non si il est DEJA DEDANS renvoie FALSE*/
    public boolean look(Bateau bateau) {
        for(int i=0; i<plateau.length;i++){
            for(int j=0; j<plateau[i].length;j++) {
                if (plateau[i][j]==bateau.getType()){return false;}
            }
        }
        return true; // true car pret a etre placé (il n'est pas dans la matrice)
    }

    /* si il n'y a rien qui gene pour le placer (bord ou autres bateaux) retourne true */
    public boolean switchLook(int modulo, Bateau bateau,int[] caseSup) {
        int colonne=caseSup[0]/90; // ici on calcule les indices en fct des coordonnées pour pouvoir les réutilisés dans la matrice
        int ligne=caseSup[1]/90;
        /*System.out.println(colonne);
        System.out.println(ligne);*/

        switch(modulo)
        {
            case 0: // vertical vers le bas
                for(int i=0; i<bateau.getTaille();i++){
                    if (plateau[colonne][ligne+i]!=0){return false;}
                }
                break;
            case 1: // horizontal vers la gauche
                for(int i=0; i<bateau.getTaille();i++){
                    if (plateau[colonne-i][ligne]!=0){return false;}
                }
                break;
            case 2: // vertical vers le haut
                for(int i=0; i<bateau.getTaille();i++){
                    if (plateau[colonne][ligne-i]!=0){return false;}
                }
                break;
            case 3: // vertical vers la droite
                for(int i=0; i<bateau.getTaille();i++){
                    if (plateau[colonne+i][ligne]!=0){return false;}
                }
                break;
        }
        return true;
    }

    /* on remplis la matrice avec l'id du bateau contenu dans les classes de léa
       on a :
       id tir deja touche = 0+[idBateau]
       id tir raté = 7
       id porte avion = 6
       id Cuirasse = 5
       id croiseur = 4
       id sous marin = 3
       id Corvette = 2
    */
   
    public void switchRempli(int modulo, Bateau bateau, int[] caseSup ) { // mise dans la matrice du bateau qui vient d'etre posé
        int colonne=caseSup[0]/90; // ici on calcule les indices en fct des coordonnées pour pouvoir les réutilisés dans la matrice
        int ligne=caseSup[1]/90;

        switch(modulo)
        {
            case 0: // vertical vers le bas
                for(int i=0; i<bateau.getTaille();i++){
                    plateau[colonne][ligne+i]=bateau.getType();
//                    System.out.println(plateauPlacement[colonne][ligne+i]);
                }
                break;
            case 1: // horizontal vers la gauche
                for(int i=0; i<bateau.getTaille();i++){
                    plateau[colonne-i][ligne]=bateau.getType();
//                    System.out.println(plateauPlacement[colonne-i][ligne]);
                }
                break;
            case 2: // vertical vers le haut
                for(int i=0; i<bateau.getTaille();i++){
                    plateau[colonne][ligne-i]=bateau.getType();
//                    System.out.println(plateauPlacement[colonne][ligne-i]);
                }
                break;
            case 3: // vertical vers la droite
                for(int i=0; i<bateau.getTaille();i++){
                    plateau[colonne+i][ligne]=bateau.getType();
//                    System.out.println(plateauPlacement[colonne+i][ligne]);
                }
                break;
        }

    }

    /* on lit la matrice a chaque fois comme ca on peut mettre le pose dans le render c'est plus facile.
     * En fct de l'getType( trouvé dans la matrice on pose le/les bateaux (sa se refresh a chaque fois donc dés que l'on pose un bateau
     * on le verra apparaitre sur la grille).
     *
     */
    public void pose(Graphics graphics) { // pour POSER LE BATEAU EN FIXE
        int idSauv=0;
        java.util.List<Integer> sauv = new ArrayList<>(); //sauvegarde les bateaux déjà mis sur le plateu
        for(int i=0; i<plateau.length;i++){ // on parcours le plateauPlacement
            for(int j=0; j<plateau[i].length;j++) {
                if (plateau[i][j]!=0 && plateau[i][j]!=1 && testPlateau(sauv,plateau[i][j])){ // si on trouve un bateau (different des precedents)
                    idSauv=plateau[i][j]; // on sauvegarde l'id du bateau trouvé
                    sauv.add(idSauv);
                    for(int k=i; k<plateau.length;k++) { // on cherche UN AUTRE MORCEAU du bateau à partir de ou on a trouver le premier morceau
                        for (int l = j; l < plateau[k].length; l++) {
                            if(plateau[k][l]==idSauv && idSauv!=0 //qd on a trouvé cette autre morceau
                                    && (k!=i || l!=j)){ // et que celui-ci n'est pas le meme que le premier
                                switchIdSauv(idSauv,l,i,j,graphics);
                                idSauv=0; // on remet a zero pour eviter de refaire d'autre bateaux
                            }
                        }
                    }
                }
            }
        }
    }

    /* Cette fonction parcours la list de int (qui sont les getType(x) et regarde si le bateau
     * avec un identifiant id à été posé sur le plateauPlacement ou non
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


                if(l==j){  // bateau à l'horizontal vers la droite
                    corvette.rotate(270);
                    graphics.drawImage(corvette,i*90-45,j*90-180+45);
                    corvette.rotate(-270);
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(corvette,i*90-90,j*90-90);
                }
                break;
            case 3: // sous-marin
                if(l==j){  // bateau à l'horizontal vers la droite
                    sousMarin.rotate(270);
                    graphics.drawImage(sousMarin,i*90,j*90-180);

                    sousMarin.rotate(-270);
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(sousMarin,i*90-90,j*90-90);

                }
                break;
            case 4: // croiseur
                if(l==j){  // bateau à l'horizontal vers la droite
                    croiseur.rotate(270);
                    graphics.drawImage(croiseur,i*90,j*90-180);

                    croiseur.rotate(-270);
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(croiseur,i*90-90,j*90-90);

                }
                break;
            case 5: // cuirassé
                if(l==j){  // bateau à l'horizontal vers la droite
                    cuirasse.rotate(270);
                    graphics.drawImage(cuirasse,i*90+45,j*90-180-45);
                    cuirasse.rotate(-270);
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(cuirasse,i*90-90,j*90-90);
                }
                break;
            case 6: // porte avion
                if(l==j){  // bateau à l'horizontal vers la droite
                    porteAvion.rotate(270);
                    graphics.drawImage(porteAvion,i*90+90,j*90-180-90);
                    porteAvion.rotate(-270);
                }else{     // bateau à la vertical vers le bas
                    graphics.drawImage(porteAvion,i*90-90,j*90-90);
                }
                break;
        }
    }

    // Corriger relou mais OK
    public void switchView(int modulo, Bateau bateau,Graphics graphics,int[] caseSup) { // pour que l'on voit le bateau
        Image bateauIm=bateau.getImgAvant();
        switch(modulo)
        {
            case 0: // vertical vers le bas
                graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-90);
                break;
            case 1: // horizontal vers la gauche
                drawImageViewHorizGauche(bateau,graphics,caseSup);
                break;
            case 2: // vertical vers le haut
                drawImageViewVertiHaut(bateau,graphics,caseSup);
                break;
            case 3: // horizontal vers la droite
                drawImageViewHorizDroite(bateau,graphics,caseSup);
                break;
        }
    }

    public void drawImageViewHorizGauche(Bateau bateau,Graphics graphics,int[] caseSup){
        Image bateauIm=bateau.getImgAvant();
        switch(bateau.getTaille())
        {
            case 2: // corvette
                bateauIm.rotate(90);
                graphics.drawImage(bateauIm,caseSup[0]-180+45, caseSup[1]-180+45);
                bateauIm.rotate(-90);
                break;
            case 3: // croiseur et sous-marin
                bateauIm.rotate(90);
                graphics.drawImage(bateauIm,caseSup[0]-180, caseSup[1]-180);
                bateauIm.rotate(-90);
                break;
            case 4: // cuirasse
                bateauIm.rotate(90);
                graphics.drawImage(bateauIm,caseSup[0]-180-45, caseSup[1]-180-45);
                bateauIm.rotate(-90);
                break;
            case 5: // porte avion
                bateauIm.rotate(90);
                graphics.drawImage(bateauIm,caseSup[0]-180-90, caseSup[1]-180-90);
                bateauIm.rotate(-90);
                break;
        }
    }

    public void drawImageViewHorizDroite(Bateau bateau,Graphics graphics,int[] caseSup){
        Image bateauIm=bateau.getImgAvant();
        switch(bateau.getTaille())
        {
            case 2: // corvette --> correcte
                bateauIm.rotate(270);
                graphics.drawImage(bateauIm,caseSup[0]-45, caseSup[1]-180+45);
                bateauIm.rotate(-270);
                break;
            case 3: // croiseur et sous-marin
                bateauIm.rotate(270);
                graphics.drawImage(bateauIm,caseSup[0], caseSup[1]-180);
                bateauIm.rotate(-270);
                break;
            case 4: // cuirasse  --> correcte
                bateauIm.rotate(270);
                graphics.drawImage(bateauIm,caseSup[0]+45, caseSup[1]-180-45);
                bateauIm.rotate(-270);
                break;
            case 5: // porte avion
                bateauIm.rotate(270);
                graphics.drawImage(bateauIm,caseSup[0]+90, caseSup[1]-180-90);
                bateauIm.rotate(-270);
                break;
        }
    }

    public void drawImageViewVertiHaut(Bateau bateau,Graphics graphics,int[] caseSup){
        Image bateauIm=bateau.getImgAvant();
        switch(bateau.getTaille())
        {
            case 2: // corvette --> correcte
                bateauIm.rotate(180);
                graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-270+90);
                bateauIm.rotate(-180);
                break;
            case 3: // croiseur et sous-marin
                bateauIm.rotate(180);
                graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-270);
                bateauIm.rotate(-180);
                break;
            case 4: // cuirasse  --> correcte
                bateauIm.rotate(180);
                graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-270-90);
                bateauIm.rotate(-180);
                break;
            case 5: // porte avion
                bateauIm.rotate(180);
                graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-270-180);
                bateauIm.rotate(-180);
                break;
        }
    }



    public String choixBateau(int posX, int posY){ // permet de choisir un bateau pour le placer

        if ((posX>925 && posX<1164) && (posY>196 && posY<258)){
            if (Mouse.isButtonDown(0)){
                depose="C";
                modif=true;
                return depose;
            }
        }
        if ((posX>910 && posX<1328) && (posY>275 && posY<352)){
            if (Mouse.isButtonDown(0)){
                depose="PA";
                modif=true;
                return depose;
            }
        }
        if ((posX>918 && posX<1259) && (posY>365 && posY<445)){
            if (Mouse.isButtonDown(0)){
                depose="Cu";
                modif=true;
                return depose;
            }
        }
        if ((posX>901 && posX<1171) && (posY>454 && posY<536)){
            if (Mouse.isButtonDown(0)){
                depose="S";
                modif=true;
                return depose;
            }
        }
        if ((posX>910 && posX<1080) && (posY>548 && posY<626)){
            if (Mouse.isButtonDown(0)){
                depose="Co";
                modif=true;
                return depose;
            }
        }
        return depose;
    }

    //CHANGEMENT ON RETOURNE LE TYPE DU BATEAU
    public Bateau switchDepose(String depose){
        switch (depose)
        {
            case "PA":
                bateau = new Bateau(6,5,0,0,porteAvion);
                //System.out.println("PA");
                break;
            case "Cu":
                bateau = new Bateau(5,4,0,0,cuirasse);
                //System.out.println("Cu");
                break;
            case "C":
                bateau = new Bateau(4,3,0,0,croiseur);
                //System.out.println("C");
                break;
            case "S":
                bateau = new Bateau(3,3,0,0,sousMarin);
                //System.out.println("S");
                break;
            case "Co":
                bateau = new Bateau(2,2,0,0,corvette);
                //System.out.println("Co");
                break;
        }
        return bateau; // retourne le bateau
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
    }
    public boolean bateauPose(){ // retourne true si tous les bateaux sont posés sinon false
        boolean tousBateauxPoses=false;
        boolean[] tabId=new boolean[5];
        for (int i=0; i<plateau.length;i++){
            for (int j=0;j<plateau[i].length;j++){
                switch (plateau[i][j]){ //si dans le plateauPlacement il y a la présence d'un bateau alors on passe à true la case de tabId
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
        for (int i=0; i<tabId.length;i++){ //on s'assure que tabId est à true ou false puis on retourne si oui ou non tous les bateaux sont posés
            if (tabId[i]==false){
                return tousBateauxPoses;
            }
        }
        tousBateauxPoses=true;
        return tousBateauxPoses;
    }
    public void passerTour(StateBasedGame stateBasedGame, int i){
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        timer-=i;
        if(bateauxPoses && ((posX>900 && posX<1170) && (posY>0 && posY<100) || timer<=0) ){
            if (Mouse.isButtonDown(0) && timer > 0){
                saverReader.savePlateau(1, plateau);
                saverReader.initEcranTir(1);
                stateBasedGame.enterState(2);
            }else if(timer<=0){
                stateBasedGame.enterState(4);
            }
        }
    }

    public void visiblePlacementBateau(Graphics graphics){
        if (!bateauPose()) { //fait apparaître ou non les bateaux à gauche pour le placement
            // images des bateaux pour leur placement (à gauche de la fenêtre)
            graphics.drawImage(croiseurView, 990, 90); // croiseur
            graphics.drawImage(porteAvionView, 1080, 90); // porte avion
            graphics.drawImage(cuirasseView, 1035, 225); // cuirasse
            graphics.drawImage(sousMarinView, 990, 360); //sous marin
            graphics.drawImage(corvetteView, 945, 495); // corvette
        }
        else{
            bateauxPoses=true;
        }
    }

    public boolean isBateauxPoses() {
        return bateauxPoses;
    }
}
