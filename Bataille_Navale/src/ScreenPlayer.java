import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;


public class ScreenPlayer extends BasicGameState{
    private boolean finPartie;
    private Image grille;
    private Image ctrl;

    public ScreenPlayer(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        grille=new Image("res/Images/900.jpg");
        ctrl=new Image("res/Images/540.jpg");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        graphics.drawImage(grille, 0,0);
        graphics.drawImage(ctrl,900,0);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //dans une condition il y aura un test pour savoir si la partie est finie, si c'est le cas alors la variable  finPartie sera mis à true
        if (finPartie){
            //besoin de créer une méthode pour savoir si le joueur à gagner ou non (pour l'écran de fin
            stateBasedGame.enterState(4);
        }
    }

    @Override
    public int getID() {
        return 1;
    }
//    public boolean verifGagne(){
//        if (/** ici : si il un des joueurs n'a plus de bateaux alors true**/){
//            return true;
//        }
//        else return false;
//    }
}