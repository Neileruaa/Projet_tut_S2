import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {
    public static final String gameName="Bataille Navale";
    public static final int menu=0;
    public static final int screenPlayer=1;
    public static final int screenEnemy =2;
    public static final int screenRules=3;
    public static final int screenEnd=4;
    public static String icon="res/Images/ICONE.png";

    public Game(String gameName){
        super(gameName); //définition du titre
        //ici définition de tous les différents écrans du jeux
        this.addState(new Menu(menu));
        this.addState(new ScreenPlayer(screenPlayer));
        this.addState(new ScreenEnnemy(screenEnemy));
        this.addState(new ScreenRules(screenRules));
        this.addState(new ScreenEnd(screenEnd));
    }

    public void initStatesList(GameContainer gc) throws SlickException{
        this.getState(menu).init(gc, this);
        this.getState(screenPlayer).init(gc, this);
        this.getState(screenEnemy).init(gc, this);
        this.getState(screenRules).init(gc, this);
        this.getState(screenEnd).init(gc, this);
        this.enterState(menu);
    }

    public static void main(String[] args) {
        AppGameContainer appgc; //le conteneur à qui on va dire affiche tel ou tel affichage dans la fenêtre en l'occurrence ici notre jeu
        try{
            appgc=new AppGameContainer(new Game(gameName));
            appgc.setDisplayMode(1440,900, false);
            appgc.setIcon(icon);
            appgc.start();
        }catch (SlickException e){
            e.printStackTrace();
        }
    }
}