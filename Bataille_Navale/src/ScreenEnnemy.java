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
        if(isBateauxPoses() && (posX>900 && posX<1440) && (posY>0 && posY<100) ){
            if (Mouse.isButtonDown(0)){
                stateBasedGame.enterState(1);

            }
        }
    }
    @Override
    public int getID() {
        return 2;
    }
}