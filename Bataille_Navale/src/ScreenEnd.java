import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ScreenEnd extends BasicGameState{
    private Image imageFin;
    public Music musiqueVictoire;
    private Menu menu;
    int var=1;

    public ScreenEnd(int state, Menu m){
        menu=m;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        imageFin=new Image("res/Images/dialogueVictoire.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        menu.getMusiqueJeu().stop();
        graphics.drawImage(imageFin,0,0);
        try {
            var=jouerSon("res/Musique/musiqueVictoire.wav", var);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
    }

    @Override
    public int getID() {
        return 4;
    }

    public int jouerSon(String path, int var) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (var==1) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-0f); // Reduce volume by 10 decibels.
            clip.start();
        }
        var=0;
        return var;
    }
}