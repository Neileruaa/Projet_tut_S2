import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;

public class TestSlickInstallation extends BasicGame
{
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public TestSlickInstallation()
    {
        super("Game");
    }

    public static void main(String[] arguments) {
        try     {
            AppGameContainer app = new AppGameContainer(new TestSlickInstallation());
             //app.setDisplayMode(screenSize.width, screenSize.height, true); //=> Full screen
            app.setDisplayMode(1600 , 900, false);
            app.setShowFPS(true); // true for display the numbers of FPS
            app.setVSync(true); // false for disable the FPS synchronize
            app.start();

        }   catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

    }
}