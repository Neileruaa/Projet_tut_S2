import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
//        map.render(0,0,0,0,900,900);
        afficherMap(graphics);
        afficherAnimation();
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
    }
}
