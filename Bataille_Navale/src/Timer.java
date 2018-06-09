import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.swing.*;

public class Timer extends BasicGameState{

    public int temps;
    private Color vert= new Color(0,255,0);

    public Timer(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        temps=0;

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException { //vous pouvez pour vous rappelez de ce qu'est render, en vous disant que render=afficher

        //graphics.drawImage(imgMenu, 0, 0); // "dessine" l'image
        graphics.drawString("Time : " + temps/1000, 100, 100);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        temps+= i;
    }

    @Override
    public int getID() {
        return 6;
    }
}
