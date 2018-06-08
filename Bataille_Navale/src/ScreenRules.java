import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;


public class ScreenRules extends BasicGameState{

    private Image imgRegle;

    public ScreenRules(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        imgRegle=new Image("res/Images/Regles.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(imgRegle, 0,0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input= gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_SPACE)){
            stateBasedGame.enterState(1);
        }
    }

    @Override
    public int getID() {
        return 3;
    }
}