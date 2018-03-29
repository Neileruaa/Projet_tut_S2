package Map;

import org.lwjgl.util.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;

public class TestTiledMap extends BasicGame {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private TiledMap map;



    public TestTiledMap()
    {
        super("Test de map");
    }

    public static void main(String[] arguments) {
        try     {
            AppGameContainer app = new AppGameContainer(new TestTiledMap());
            //app.setDisplayMode(screenSize.width, screenSize.height, true); //=> Full screen

            app.setDisplayMode(1600 , 900, false);
            app.setShowFPS(true); // true for display the numbers of FPS
            app.setVSync(true); // false for disable the FPS synchronize
            app.setShowFPS(true); // true for display the numbers of FPS
            app.start();

        }   catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException{

        map = new TiledMap("res/Map/Map900x900.tmx");

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.render(0,0,0,0,1000,1000);
    }
}
