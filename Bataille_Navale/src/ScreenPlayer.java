import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;


public class ScreenPlayer extends BasicGameState{

    private Image grille;
    private Image ctrl;

    public ScreenPlayer(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        grille=new Image("res/Images/900.jpg");
        ctrl=new Image("res/Images/540.jpg");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(grille, 0,0);
        graphics.drawImage(ctrl,900,0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public int getID() {
        return 1;
    }
}