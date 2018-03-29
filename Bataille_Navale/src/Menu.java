
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;

import javax.swing.*;

public class Menu extends BasicGameState{

    private Image imgMenu;

    public Menu(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        imgMenu=new Image("res/Images/imgMenu.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException { //vous pouvez pour vous rappelez de ce qu'est render, en vous disant que render=afficher

        graphics.drawImage(imgMenu, 0, 0);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();


        //JOUER
        if((posX>600 && posX<838) && (posY>232 && posY<332) ){
            if (Mouse.isButtonDown(0)){
                System.exit(0);
            }
        }

        //QUITTER
        if((posX>600 && posX<838) && (posY>600 && posY<700) ){
            if (Mouse.isButtonDown(0)){
                stateBasedGame.enterState(3);

            }
        }
    }

    @Override
    public int getID() {
        return 0;
    }
}