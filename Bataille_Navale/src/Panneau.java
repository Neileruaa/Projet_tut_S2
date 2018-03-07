import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel{
    private int posX;
    private int posY;

    public Panneau(LayoutManager layout, boolean isDoubleBuffered, int posX, int posY) {
        super(layout, isDoubleBuffered);
        this.posX = posX;
        this.posY = posY;
    }

    public void paintComponent(Graphics g){
        //ici différentes formes par exemple
    }
}