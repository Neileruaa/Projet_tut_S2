import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ScreenShoot extends BasicGameState {

    final int CASE_NON_CHECK = 9;
    final int COULE = 8;
    final int RATE = 7;


    SaverReader  saverReader = new SaverReader();

    //tableau 2D avec le placement des bateaux
    int[][] plateauPlacement;

    //tableau 2D avec le placement des bateaux
    int[][] plateauEcranTir;


    //Animation de l'explosion
    SpriteSheet explosionSheet;
    Animation explosionAnimation;

    //Animation de la fumee
    SpriteSheet fumeeSheet;
    Animation fumeeAnimation;

    // image
    private Image tire;
    private Image passe;

            // popup

    private Image dialogueTouche;
    private Image couleCorvette;
    private Image couleCroiseur;
    private Image couleSousMarin;
    private Image couleCuirasse;
    private Image coulePorteAvion;
    private Image dialogueVictoire;
    private Image rate;

    private int[] space = {90,180,270,360,450,540,630,720,810,900};
    private boolean dejaTire=false;
    private String choix="";

    boolean EcranTirDejaCharge = false;



    public ScreenShoot(int state){
    }
    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        //Affichage du curseur en mire
        gameContainer.setMouseCursor("res/Images/mire.png", 0, 0);

        //Animation de l'explosion
        explosionSheet = new SpriteSheet("res/Images/explosion.png", 90,90);
        explosionAnimation = new Animation(explosionSheet,42);
        //Animation de la fumée
        fumeeSheet = new SpriteSheet("res/Images/fumee.png", 90,90);
        fumeeAnimation = new Animation(fumeeSheet, 160);


        // image bouton
        tire=new Image("res/Images/tire.png");
        passe=new Image("res/Images/passe.png");

        // popup de dialogue du commandant
        couleCorvette=new Image("res/Images/couleCorvette.png");
        couleCroiseur=new Image("res/Images/couleCroiseur.png");
        couleSousMarin=new Image("res/Images/couleSousmarin.png");
        couleCuirasse=new Image("res/Images/couleCuirasse.png");
        coulePorteAvion=new Image("res/Images/coulePorteAvion.png");
        dialogueVictoire=new Image("res/Images/dialogueVictoire.png");
        dialogueTouche=new Image("res/Images/dialogueTouche.png");
        rate=new Image("res/Images/rate.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        if(!EcranTirDejaCharge){
            plateauEcranTir = saverReader.readEcranTir(1);
            EcranTirDejaCharge = true;
        }

        afficherMap(graphics);

        graphics.drawImage(tire,900,400);
        graphics.drawImage(passe,900,810);

        Point tirChoisi = tirer(gameContainer, graphics);

        comparePlateauAndShoot(tirChoisi);

        //save ecranTir
        saverReader.saveEcranTir(1, plateauEcranTir);

    }

    private Point tirer(GameContainer gameContainer, Graphics graphics) {
        int colonne = 0;
        int ligne = 0;

        int posX= Mouse.getX();
        int posY=900- Mouse.getY();
        int[] caseSup = findIdTile(posX, posY);
        if((posX>caseSup[0]-90 && posX<caseSup[0])
                && (posY>caseSup[1]-90 && posY<caseSup[1]) ){
            graphics.setColor(new Color(255,0,255,0.5f));
            graphics.fillRect(caseSup[0]-90, caseSup[1]-90, 90, 90);
        }

        choixDeTire(Mouse.getX(),900-Mouse.getY());

        Point p = null;

        if(Mouse.isButtonDown(0) && !Mouse.isButtonDown(1)) { // clic gauche
            if ((posX>0 && posX<900) && (posY>0 && posY<900) && !dejaTire){ // si il clic dans la grille
                // et qu'il n'a pas deja tire
                System.out.println("clic dans grille");
                System.out.println(choix);
                if(choix.equals("tire normal")){
                    // tire matrice avec en parametre posX et posY
                    colonne =caseSup[0]/90-1;
                    ligne = caseSup[1]/90-1;
                    System.out.println("vous effectuez un tire normal à la position X : "+colonne+", Y : "+ligne);
                    p = new Point(ligne, colonne);
                    //CHANGER POUR LA PHASE DE TESTS
                    dejaTire=false;
                }
            }
        }
        return p;
    }

    public void choixDeTire(int posX, int posY){
        if ((posX>890 && posX<1041) && (posY>390 && posY<492)){
            if (Mouse.isButtonDown(0)){
                System.out.println("vous avez choisit le tire normal");
                choix="tire normal";
            }
        }
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

    private void afficherAnimationExplosion(int x, int y) {
        //Animation de l'explosion
        explosionAnimation.setSpeed(27);
        explosionAnimation.draw(x,y);

//        //Animation de l'eau
//        waterAnimation.draw(110,110);
    }

    private void afficherAnimationfumee(int x, int y) {
        //Animation de l'explosion
        fumeeAnimation.draw(x,y);

//        //Animation de l'eau
//        waterAnimation.draw(110,110);
    }

    private void afficherMap(Graphics graphics) throws SlickException {
        for (int i = 0; i<plateauEcranTir.length; i++){
            for (int j = 0; j<plateauEcranTir.length; j++){
                //Inversion de i et de j afin d'avoir la meme reprensation que dans le tableau
                // ecranTirJ(1 ou 2), sans l'effet de miroir.
                if (plateauEcranTir[j][i] == 8){
                    graphics.drawImage(new Image("res/Images/tile#1.png"),i*90,j*90);
                    afficherAnimationfumee(i*90, j*90);
                }
                if (plateauEcranTir[j][i] == 9){
                    graphics.drawImage(new Image("res/Images/guess.jpeg"),i*90,j*90);
                }
                if (plateauEcranTir[j][i] == 7){
                    graphics.drawImage(new Image("res/Images/tile#1.png"),i*90,j*90);
                }
            }
        }
    }

    public int[][] removeElement(int[][] A, int elem) {
        int[][] tabSansElem = new int[12][12];
        int[][] finalTab = new int[10][10];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i][j] != elem){
                    tabSansElem[i][j] = A[i][j];
                }
            }
        }

        System.out.println("Valeur de plateau placement : "+tabSansElem.length);
        for (int i = 0; i < tabSansElem.length; i++) {
            for (int j = 0; j < tabSansElem.length; j++) {
                System.out.print(tabSansElem[j][i]);
            }
            System.out.println();
        }
        System.out.println(" ============================================ ");

        for (int i = 0; i < finalTab.length; i++) {
            for (int j = 0; j < finalTab.length; j++) {
               finalTab[i][j] = tabSansElem[i][j];
            }
        }
        return finalTab;
    }

    //Ajout de 2 variable pour les tests le temps que mattéo finisse
    public boolean comparePlateauAndShoot(Point point){
        int x = 0;
        int y= 0;
        if (point != null) {
            x = (int)point.getX();
            y = (int) point.getY();
        } else {
            System.out.println("POINT INVALIDE");
        }

        //On enleve les 1 du plateauPlacement
        plateauPlacement = removeElement(plateauPlacement, 1);


        /*System.out.println("Valeur de plateau placement : "+plateauPlacement.length);
        for (int i = 0; i < plateauPlacement.length; i++) {
            for (int j = 0; j < plateauPlacement.length; j++) {
                System.out.print(plateauPlacement[j][i]);
            }
            System.out.println();
        }
        System.out.println(" ============================================ ");*/


        if (plateauPlacement[y][x] > 1 && plateauPlacement[y][x] < 7 && plateauEcranTir[y][x] != COULE){
            System.out.println("Il y a un bateau en y: " + y + ";i " + x + " -> coulé");
            changerEtatCaseEcranTir(x,y,COULE);

            afficherAnimationExplosion(y * 90, x * 90);

            // METTRE SON EXPLOSION
            return true;
        }
        if (plateauPlacement[y][x] > 1 && plateauPlacement[y][x] < 7 && plateauEcranTir[y][x] == COULE){
            System.out.println("Il y a un bateau en y : " + y + "; i " + x
                    + " -> mais déjà coulé donc impossible");
            changerEtatCaseEcranTir(x,y,COULE);
            return false;
        }
        if (plateauPlacement[y][x]  == 0 && plateauEcranTir[y][x] != RATE  ){
            System.out.println("Il n'y a rien en y : " + y + "; x " + x
                    + " -> donc coup raté ");
            changerEtatCaseEcranTir(x,y,RATE);
            return true;
        }
        if (plateauPlacement[y][x]  == 0 && plateauEcranTir[y][x] == RATE  ){
            System.out.println("Vous avez déjà essayé et il n'y a toujours rien en y : " + y + ";" + x
                    + " -> donc impossible");
            changerEtatCaseEcranTir(x,y,RATE);
            return false;
        }

        return false;
    }

    private void changerEtatCaseEcranTir(int x, int y, int nouvelEtat){
        plateauEcranTir[x][y] = nouvelEtat;

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        plateauPlacement = saverReader.readPlateau(1);
        passerTour(stateBasedGame);
    }

    public void passerTour(StateBasedGame stateBasedGame) {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if (dejaTire && (posX > 900 && posX < 1170) && (posY > 0 && posY < 100)) {
            if (Mouse.isButtonDown(0)) { // on sauvegarde les changements et on passe à l'autre joueur
                System.out.println("on sauvegarde les changements et on passe à l'autre joueur");
                //saverReader.saveEcranTir(1, plateau);
                //saverReader.initEcranTir(1);
                //stateBasedGame.enterState(2);
            }
        }
    }

}
