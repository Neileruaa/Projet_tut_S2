import javax.swing.*;
import java.awt.*;

public class Appli {
    JFrame app;


    public Appli(String title) {
        app = new JFrame(title);
        app.setLocationRelativeTo(null);
        app.setSize(800,600);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}