import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Reseau.*;

import java.io.IOException;

public class Game extends StateBasedGame {
    public static final String gameName="Bataille Navale";
    public static final int menu=0;
    public static final int screenPlayer=1;
    public static final int screenEnemy =2;
    public static final int screenRules=3;
    public static final int screenEnd=4;
    public static final int screenShoot=5;
    public static final int screenShootEnemy=6;
//    public static String icon="res/Images/ICONE.png";

    public Game(String gameName){
        super(gameName); //définition du titre
        //ici définition de tous les différents écrans du jeux
        this.addState(new Menu(menu));
        this.addState(new ScreenPlayer(screenPlayer));
        this.addState(new ScreenEnnemy(screenEnemy));
        this.addState(new ScreenRules(screenRules));
        this.addState(new ScreenEnd(screenEnd));
        this.addState(new ScreenShoot(screenShoot));
        this.addState(new ScreenShootEnemy(screenShootEnemy));
    }

    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(screenPlayer).init(gc, this);
        this.getState(screenEnemy).init(gc, this);
        this.getState(screenRules).init(gc, this);
        this.getState(screenEnd).init(gc, this);
        this.getState(screenShoot).init(gc, this);
        this.getState(screenShootEnemy).init(gc, this);
        this.enterState(menu);
    }

    public static void main(String[] args) {
        AppGameContainer appgc; //le conteneur à qui on va dire affiche tel ou tel affichage dans la fenêtre en l'occurrence ici notre jeu
        try{
            //Test chat
            try {
                new ChatServer();
                new ClientProg();
                new ClientProg();
            } catch (IOException e) {
                e.printStackTrace();
            }

            appgc=new AppGameContainer(new Game(gameName));
            appgc.setDisplayMode(1440,900, false);
//            appgc.setIcon(icon);
            appgc.start();
        }catch (SlickException e){
            e.printStackTrace();
        }
    }
}