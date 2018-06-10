import org.lwjgl.input.Mouse;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenShootEnemy extends ScreenShoot {
    public ScreenShootEnemy(int state) {
        super(state);
    }
    @Override
    public void passerTour(StateBasedGame stateBasedGame) {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        if (isDejaTire() && (posX > 900 && posX < 1170) && (posY > 0 && posY < 100)) {
            if (Mouse.isButtonDown(0)) { // on sauvegarde les changements et on passe à l'autre joueur
                System.out.println("on sauvegarde les changements et on passe à l'autre joueur");
                stateBasedGame.enterState(5);
            }
        }
    }
    @Override
    public int getID() {
        return 6;
    }
}
