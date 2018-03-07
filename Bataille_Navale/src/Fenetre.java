import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{
    private int width;
    private int height;
    private String title;
    private JPanel container;

    public Fenetre(int width, int height, String title, JPanel container) throws HeadlessException {
        this.container=container;
        this.width = width;
        this.height = height;
        this.title = title;
        this.setTitle(title);
        this.setSize(this.width, this.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(container);
        this.setVisible(true);
    }

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public void ajoutElement(JPanel e){
        this.container.add(e);
    }
    public void ajoutBouton(JButton b){
        this.container.add(b);
    }
}