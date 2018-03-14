import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //methode pour image de fond sur le Menu
        ImageIcon icon = new ImageIcon("test.png");
        JPanel pan=new JPanel();
        JLabel img = new JLabel(icon);

        JButton b=new JButton("mon bouton test");
        JButton b1=new JButton("test");
        Menu m=new Menu(1500,1000,"test", pan, b, b1, img);
    }
}
