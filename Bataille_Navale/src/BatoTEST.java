package Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class BatoTEST {
    private int x;
    private int y;
    private Image img;

    public int tailleBateau=3; // pour le test
    public int idBateau=4; // pour le test

    public BatoTEST(Image img) {
        this.img = img;
    }

    public BatoTEST(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void drawImage(Graphics g){
        g.drawImage(img, x, y);
    }

    @Override
    public String toString() {
        return "BatoTEST{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
