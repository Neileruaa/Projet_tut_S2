import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenShoot extends BasicGameState {
//    private TiledMap map;

    SaverReader  saverReader = new SaverReader();

    //tableau 2D avec le placement des bateaux
    int[][] plateauPlacement;

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

        //Affichage du curseur en mire
        gameContainer.setMouseCursor("res/Images/mire.png", 0, 0);

        //Animation de l'explosion
        explosionSheet = new SpriteSheet("res/Images/explosion.png", 90,90);
        explosionAnimation = new Animation(explosionSheet, 42);
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
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                //Inversion de i et de j afin d'avoir la meme reprensation que dans le tableau
                // ecranTirJ(1 ou 2), sans l'effet de miroir.
                if (saverReader.readEcranTir(1)[j][i] == 8){
                    graphics.drawImage(new Image("res/Images/fire.jpg"),i*90,j*90);
                }
                if (saverReader.readEcranTir(1)[j][i] == 9){
                    graphics.drawImage(new Image("res/Images/guess.jpeg"),i*90,j*90);
                }
                if (saverReader.readEcranTir(1)[j][i] == 7){
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

    private boolean comparePlateauAndShoot(int x, int y){
        //On enleve les 1 du plateauPlacement
        for(int i = 0; i< plateauPlacement.length; i++){
            removeElement(plateauPlacement[i],1);
        }

//        for (int i = 0; i < ; i++) {
//
//        }


        return false;
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
            waterAnimation.update(100);
    }
}
