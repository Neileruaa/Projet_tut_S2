package Map;

public class Tile {
    private int x;
    private int y;
    private int x1;
    private int x2;
    private int x3;
    private int y1;
    private int y2;
    private int y3;

    private static final int SQUARE_SIZE = 90;


    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        x1= x + SQUARE_SIZE;
        x2 = x+SQUARE_SIZE;
        x3= x;
        y1= y;
        y2 = y+SQUARE_SIZE;
        y3= y+SQUARE_SIZE;
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

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }
}
