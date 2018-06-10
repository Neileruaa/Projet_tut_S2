import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

class GameState extends BasicGameState {

    private SpriteSheet spritesheet;
    private Animation waterAnimation;

    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        spritesheet = new SpriteSheet("res/Images/spritesheet.png", 90, 90);
        waterAnimation = new Animation(spritesheet, 100);
    }
    public void update(GameContainer container, StateBasedGame sbg, int delta) {

    }
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) {
        waterAnimation.draw(100, 100);
    }


    public int getID(){
        return 0;
    }

}