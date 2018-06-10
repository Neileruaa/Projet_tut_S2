
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.swing.*;

public class Menu extends BasicGameState{

    private Image imgMenu; // Crée une instance de Menu de type Image
    public Music musiqueJeu; // Crée une instance de Menu de type Music
    public Sound survolBouton; // Crée une instance de Menu de type Sound
    int nbFoisSurBoutonJouer; // Crée une variable qui compte le nombre de fois que l'on est sur le bouton JOUER
    int nbFoisSurBoutonQuitter; // Crée une variable qui compte le nombre de fois que l'on est sur le bouton QUITTER

    public Menu(int state){
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        imgMenu=new Image("res/Images/imgMenu.png"); // Affiche l'image
        survolBouton=new Sound("res/Musique/survolBouton.wav"); // Affecte un nouveau son à la variable
        musiqueJeu=new Music("res/Musique/musiqueJeu.ogg"); // Affecte une nouvelle musique à la variable
        musiqueJeu.loop(); // Boucle la musique
        nbFoisSurBoutonJouer=0; // Initialise le compteur à 0
        nbFoisSurBoutonQuitter=0; // Initialise le compteur à 0
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException { //vous pouvez pour vous rappelez de ce qu'est render, en vous disant que render=afficher

        graphics.drawImage(imgMenu, 0, 0); // "dessine" l'image

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        float posX = Mouse.getX(); // Renvoi la positon X de la souris
        float posY = Mouse.getY(); // Renvoi la positon Y de la souris


        //JOUER
        if((posX>600 && posX<838) && (posY<900-232 && posY>900-332) ){ // Verifie que l'on est sur l'image JOUER
            nbFoisSurBoutonJouer++; // On incrémente le compteur
            if (nbFoisSurBoutonJouer==1) { // Si on est pour la première fois sur JOUER
                survolBouton.play(); // On joue le son

            }
            if (Mouse.isButtonDown(0)){ // Si on appuie sur JOUER
                stateBasedGame.enterState(3); // On passe à la l'état (fenêtre) suivant
            }
        }else {
            if (nbFoisSurBoutonJouer>0){ // Si on est plus sur JOUER et que notre compteur n'est pas à 0
                nbFoisSurBoutonJouer=0; // On le remet à 0
            }
        }

        //QUITTER
        if((posX>600 && posX<838) && (posY<900-600 && posY>900-700) ){ // Verifie que l'on est sur l'image QUITTER
            nbFoisSurBoutonQuitter++;
            if (nbFoisSurBoutonQuitter==1) {
                survolBouton.play();

            }
            if (Mouse.isButtonDown(0)){ // Si on appuie sur QUITTER
                System.exit(0); // On ferme la fenêtre
            }
        }else {
            if (nbFoisSurBoutonQuitter>0){
                nbFoisSurBoutonQuitter=0;
            }
        }
    }

    @Override
    public int getID() {
        return 0;
    }
}