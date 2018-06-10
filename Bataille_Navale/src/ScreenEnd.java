import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenEnd extends BasicGameState{
    private Image imageFin;

    public ScreenEnd(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
//        if (joueur.verifGagne()){
//            imageFin=new Image("win.png");
//        } else imageFin=new Image("lose.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
//        graphics.drawImage(imageFin);
        System.out.println("fail");
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    @Override
    public int getID() {
        return 4;
    }
}