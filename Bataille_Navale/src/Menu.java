import javax.swing.*;
import java.awt.*;

public class Menu extends Fenetre {
    private JButton playNow;
    private JButton exitGame;

    public Menu(int width, int height, String title, JPanel container, JButton playNow, JButton exitGame, JLabel img) throws HeadlessException {
        super(width, height, title, container);
        //container.add(img);
        this.playNow = playNow;
        this.exitGame = exitGame;
        container.add(this.playNow);
        container.add(this.exitGame);
        container.add(img);
    }
}
