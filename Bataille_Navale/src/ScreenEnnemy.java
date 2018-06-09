import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenEnnemy extends ScreenPlayer{

    public ScreenEnnemy(int state) {
        super(state);
    }
    @Override
    public void passerTour(StateBasedGame stateBasedGame){
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if(isBateauxPoses() && (posX>1170 && posX<1440) && (posY>0 && posY<100) ){
            if (Mouse.isButtonDown(0)){
                saverReader.savePlateau(2, plateau);
                saverReader.initEcranTir(2);
                stateBasedGame.enterState(5);
            }
        }
    }
    @Override
    public int getID() {
        return 2;
    }
}