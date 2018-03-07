import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JPanel pan=new JPanel();
        JButton b=new JButton("mon bouton test");
        Fenetre f=new Fenetre(300,300,"Test", pan);
        f.ajoutBouton(b);
    }
}
