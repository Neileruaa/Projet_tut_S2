import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel{
    private int posX;
    private int posY;

    public Panneau(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void paintComponent(Graphics g){
        //ici différentes formes par exemple
    }
}