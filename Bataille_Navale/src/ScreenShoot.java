import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.input.Mouse;

public class ScreenShoot extends BasicGameState {
//    private TiledMap map;

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

    SpriteSheet waterSheet;
    Animation waterAnimation;

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


    public ScreenShoot(int state){
    }
    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
//           map = new TiledMap("res/Map/Map900x900.tmx");
        plateauPlacement = saverReader.readPlateau(1);

        plateauEcranTir = saverReader.readEcranTir(1);

        //Affichage du curseur en mire
        gameContainer.setMouseCursor("res/Images/mire.png", 0, 0);

        //Animation de l'explosion
        explosionSheet = new SpriteSheet("res/Images/explosion.png", 90,90);
        explosionAnimation = new Animation(explosionSheet,42);
        waterSheet = new SpriteSheet("res/Images/water.png", 90,90);
        waterAnimation = new Animation(waterSheet, 100);

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
//        map.render(0,0,0,0,900,900);
        afficherMap(graphics);

        graphics.drawImage(tire,900,400);
        graphics.drawImage(passe,900,810);

        Input input = gameContainer.getInput(); // pour le clic
        int posX= Mouse.getX();
        int posY=900- Mouse.getY();
        int[] caseSup = findIdTile(posX, posY);
        if((posX>caseSup[0]-90 && posX<caseSup[0])
                && (posY>caseSup[1]-90 && posY<caseSup[1]) ){
            graphics.setColor(new Color(255,0,255,0.5f));
            graphics.fillRect(caseSup[0]-90, caseSup[1]-90, 90, 90);
        }
        choixDeTire(Mouse.getX(),900-Mouse.getY());

        if(Mouse.isButtonDown(0) && !Mouse.isButtonDown(1)) { // clic gauche
            if ((posX>0 && posX<900) && (posY>0 && posY<900) && !dejaTire){ // si il clic dans la grille
                // et qu'il n'a pas deja tire
                System.out.println("clic dans grille");
                System.out.println(choix);
                if(choix.equals("tire normal")){
                    // tire matrice avec en parametre posX et posY
                    int colonne =caseSup[0]/90-1;
                    int ligne = caseSup[1]/90-1;
                    System.out.println("vous effectuez un tire normal à la position X : "+colonne+", Y : "+ligne);
                    dejaTire=true;
                }
            }
            System.out.println("X : "+posX+", Y : "+posY);
        }

        afficherAnimation();
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


    private void afficherAnimation() {
        //Animation de l'explosion
        explosionAnimation.draw(10,10);

        //Animation de l'eau
        waterAnimation.draw(110,110);
    }

    private void afficherMap(Graphics graphics) throws SlickException {
        for (int i = 0; i<plateauEcranTir.length; i++){
            for (int j = 0; j<plateauEcranTir.length; j++){
                //Inversion de i et de j afin d'avoir la meme reprensation que dans le tableau
                // ecranTirJ(1 ou 2), sans l'effet de miroir.
                if (plateauEcranTir[j][i] == 8){
                    graphics.drawImage(new Image("res/Images/fire.jpg"),i*90,j*90);
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

    private void removeElement(int[] A, int elem) {
        int length=A.length;
        int i=0;
        for(int j=0; j<length; j++) {
            if(A[j]!= elem){
                A[i]=A[j];
                i++;
            }
        }
        if(i<length) A[i]='\0';
    }

    //Ajout de 2 variable pour les tests le temps que mattéo finisse
    public boolean comparePlateauAndShoot(int x, int y, int[][] plateauPlacementTEST, int[][] plateauEcranTirTEST){
        //On enleve les 1 du plateauPlacement
        //TEST : plateauPlacement -> plateauPlacementTEST
        // plateauEcranTir -> plateauEcranTirTEST
        for(int i = 0; i< plateauPlacementTEST.length; i++){
            removeElement(plateauPlacementTEST[i],1);
        }

        for (int i = 0; i < plateauPlacementTEST.length; i++) {
            for (int j = 0; j < plateauPlacementTEST.length; j++) {
                if (x==i && y==j) {
                    if (plateauPlacementTEST[j][i] > 1 && plateauPlacementTEST[j][i] < 7 && plateauEcranTirTEST[j][i] != COULE){
                        System.out.println("Il y a un bateau en : " + j + ";" + i + " -> coulé");
                        changerEtatCaseEcranTir(x,y,COULE, plateauEcranTirTEST);
                        // METTRE ANIMATION EXPLOSION
                        // METTRE SON EXPLOSION
                        return true;
                    }
                    if (plateauPlacementTEST[j][i] > 1 && plateauPlacementTEST[j][i] < 7 && plateauEcranTirTEST[j][i] == COULE){
                        System.out.println("Il y a un bateau en : " + j + ";" + i
                                + " -> mais déjà coulé donc impossible");
                        changerEtatCaseEcranTir(x,y,COULE, plateauEcranTirTEST);
                        return false;
                    }
                    if (plateauPlacementTEST[j][i]  == 0 && plateauEcranTirTEST[j][i] != RATE  ){
                        System.out.println("Il n'y a rien en : " + j + ";" + i
                                + " -> donc coup raté ");
                        changerEtatCaseEcranTir(x,y,RATE, plateauEcranTirTEST);
                        return true;
                    }
                    if (plateauPlacementTEST[j][i]  == 0 && plateauEcranTirTEST[j][i] == RATE  ){
                        System.out.println("Vous avez déjà essayé et il n'y a toujours rien en : " + j + ";" + i
                                + " -> donc impossible");
                        changerEtatCaseEcranTir(x,y,RATE, plateauEcranTirTEST);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    private void changerEtatCaseEcranTir(int x, int y, int nouvelEtat, int[][] plateauEcranTirTEST){
        for (int i = 0; i < plateauEcranTirTEST.length; i++) {
            for (int j = 0; j < plateauEcranTirTEST.length; j++) {
                if (j==x && i==y){
                    plateauEcranTirTEST[j][i] = nouvelEtat;
                }
            }
        }
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
            waterAnimation.update(100);
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
