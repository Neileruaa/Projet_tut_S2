package test;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickExeption;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.BasicGameState;
import org.newdawn.slick.StateBasedGame;

public class GameState extends BasicGameState {

    private SpriteSheet spritesheet;
    private Animation waterAnimation;

    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickExeption {
        spritesheet = new SpriteSheet("res/Images/spritesheet.png", 90, 90);
        waterAnimation = new Animation(spritesheet, 100);
    }
    public void update(GameContainer container, StateBasedGames sbg, int delta) throws SlickExeption {

    }
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickExeption {
        waterAnimation.draw(100, 100);
    }
    public int getID(){
        return 0;
    }
}