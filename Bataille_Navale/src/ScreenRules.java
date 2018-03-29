import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenRules extends BasicGameState{

    private Image imgRegle;

    public ScreenRules(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        imgRegle=new Image("Img/900.jpg");
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