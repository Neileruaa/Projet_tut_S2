package Map;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TestTiledMap extends BasicGame {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private TiledMap map;
    private ArrayList<Tile> tiles;

    private Image croiseur;

    private LETTERS[] lines;
    private int[] columns;

    private enum LETTERS{
        A,B,C,D,E,F,G,H,I,J
    }

    private int[] space = {90,180,270,360,450,540,630,720,810,900};
    private int[][] mapTab = {{90,180,270,360,450,540,630,720,810,900},{90,180,270,360,450,540,630,720,810,900}};

    public TestTiledMap()
    {
        super("Test de map");
    }

    public static void main(String[] arguments) {
        try     {
            AppGameContainer app = new AppGameContainer(new TestTiledMap());
            //app.setDisplayMode(screenSize.width, screenSize.height, true); //=> Full screen

            app.setDisplayMode(1440 , 900, false);
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
        croiseur = new Image("res/Images/croiseur.png");

        int posX = Mouse.getX();
        int posY = 900 - Mouse.getY();
        map.render(0,0,0,0,900,900);

        int[] caseSup = findIdTile(posX, posY);
        if((posX>caseSup[0]-90 && posX<caseSup[0])
                && (posY>caseSup[1]-90 && posY<caseSup[1]) ){
            graphics.setColor(new Color(255,0,255,0.5f));
            graphics.fillRect(caseSup[0]-90, caseSup[1]-90, 90, 90);

        }
        graphics.drawImage(croiseur,caseSup[0]-90, caseSup[1]-90);

        croiseur.destroy();
        Image croiseurRotated =croiseur;
        croiseurRotated.rotate(90);

        if (Mouse.isButtonDown(0)){


            graphics.drawImage(croiseurRotated,caseSup[0]-180, caseSup[1]-180);
        }
    }

    public int[] findIdTile(int posX, int posY){
        int[] coord= new int[2];
        for (int i = 0; i<space.length;i++){
            if (posX < space[i]){
                for (int j =0; j<space.length;j++){
                    if (posY < space[j]){
                        coord[0]=space[i];
                        coord[1]=space[j];
                        return coord;
                    }
                }
            }
        }
        return coord;
    }
}
