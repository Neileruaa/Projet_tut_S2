import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenEnnemy extends ScreenPlayer{
    private int timer=30000;

    public ScreenEnnemy(int state) {
        super(state);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);
    }

    @Override
    public void passerTour(StateBasedGame stateBasedGame, int i){
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        timer-=i;
        if(isBateauxPoses() && (posX>1170 && posX<1440) && (posY>0 && posY<100) || timer<= 0){
            if (Mouse.isButtonDown(0) && timer > 0){
                saverReader.savePlateau(1, plateau);
                saverReader.initEcranTir(1);
                stateBasedGame.enterState(2);
            }else if(timer<=0){
                stateBasedGame.enterState(4);
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        super.update(gameContainer, stateBasedGame, i);
    }

    @Override
    public int getID() {
        return 2;
    }
}