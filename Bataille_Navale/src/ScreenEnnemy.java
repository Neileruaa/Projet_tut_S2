import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScreenEnnemy extends ScreenPlayer{
    private int timer=30000;

    public ScreenEnnemy(int state) {
        super(state);
    }


    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
//        graphics.drawImage(grille, 0,0);
        graphics.drawImage(ctrl,900,0);

        if(timer/1000>=0) {
            graphics.drawImage(new Image("res/Images/" + String.valueOf(timer / 1000) + ".png"), 1220, 50);
        }
        graphics.drawImage(passeT, 1200,795);
        graphics.drawImage(infoPlace,900,38);

        Input input = gameContainer.getInput(); // pour le clic

        int posX= Mouse.getX();
        int posY=900-Mouse.getY();



        /* ------------------------------- IMPORTANT ---------------------------- */
        // je met une valeur par default a bateau qd on a pas choisit un bateau (evite les nullpointerexception)
        // les coord nous servent A RIEN dans les CLASSES (du moins pour le placement)
        // ils nous serviront PEUT ETRE pour le touché coulé (qu'on pourra init en regardant dans la matrice)
        // on initera d'ailleurs tout le bateau BEAUCOUP plus simple

        if(depose.equals("") && modif==false){ //si on a pas cliquer sur un bouton pour changer les bateaux on prend le croiseur par defaut
            bateau=new Bateau(4,3,0,0,croiseur);
        }

        depose=choixBateau(Mouse.getX(),900-Mouse.getY()); // on regarde le choix de l'utilisateur


        croiseur = new Image("res/Images/croiseur.png");
        sousMarin = new Image("res/Images/sous_marin.png");
        porteAvion=new Image("res/Images/porteAvion.png");
        cuirasse=new Image("res/Images/cuirasse.png");
        corvette=new Image("res/Images/corvette.png");

        imgDefaut = new Image("res/Images/icon.ico"); // une image par defaut pour init la variable (rend compilable)

        map.render(0,0,0,0,900,900);
//        img.rotate(90);

        visiblePlacementBateau(graphics);

        int[] caseSup = findIdTile(posX, posY);
        if((posX>caseSup[0]-90 && posX<caseSup[0])
                && (posY>caseSup[1]-90 && posY<caseSup[1]) ){
            graphics.setColor(new Color(255,0,255,0.5f));
            graphics.fillRect(caseSup[0]-90, caseSup[1]-90, 90, 90);
        }


        if(Mouse.isButtonDown(1) && !Mouse.isButtonDown(0) && doubleClic==false){ // empeche l'apparition de 2 bateaux lorsque l'on clique sur le bouton droit et gauche
            posX = Mouse.getX();
            posY = 900 - Mouse.getY();

            Bateau renderBateau;
            renderBateau = switchDepose(depose);
            drawImageViewHorizGauche(renderBateau,graphics,caseSup);
            affiche=false;
            /* acienne methode avt chgt bateau */
//            Image bateauIm;
//            bateauIm=switchDepose(depose).getImgAvant();
//            bateauIm.rotate(90);
//            graphics.drawImage(bateauIm,caseSup[0]-180, caseSup[1]-180);
//            bateauIm.rotate(-90); // retour à la normal comme ca CORRIGE le BOGUE

        }

        if(Mouse.isButtonDown(0) && !Mouse.isButtonDown(1) && doubleClic==false){
            posX = Mouse.getX();
            posY = 900 - Mouse.getY();
            switchDepose(depose);
            affiche=false;
            //Image bateauIm=imgDefaut;
            //bateauIm=switchDepose(depose).getImgAvant(); // pb affiche que le croiseur

            //bateau = new Bateau(4,3,caseSup[0]-90,caseSup[1]-90,croiseur);
            //graphics.drawImage(bateauIm,caseSup[0]-90, caseSup[1]-90);   // bateau fixed
//            System.out.println(bateau.toString());
            //System.out.println("X : "+posX+", Y : "+posY);
        }

        if (doubleClic) { // si on fait un double clic
            if(input.isMousePressed(input.MOUSE_RIGHT_BUTTON) && posX>0 && posX<900 && posY>0 && posY<900) { // si on fait un click DROIT dans la grille
                nbClicDroit++;
                modulo=nbClicDroit%4;
            }
            if (switchLook(modulo,bateau,caseSup) && look(bateau)){ //si pas 2 fois le mm bateau et que rien ne gene
                caseSup = findIdTile(posX, posY);
                switchView(modulo,bateau,graphics,caseSup);
                if(input.isMousePressed(input.MOUSE_LEFT_BUTTON) && posX>0 && posX<900 && posY>0 && posY<900){ // si on fait un click GAUCHE dans la grille
                    switchRempli(modulo, bateau,caseSup); // on rempli la matrice

                    nbClicDroit=0; // on remet tt à zero
                    modulo=0;
                    doubleClic=false;
                }
            } else {
                if (!look(bateau)){
                    //System.out.println("Se bateau est deja posé");
                    affiche=true; // pour la popup
                    doubleClic=false;
                }
                if (!switchLook(modulo,bateau,caseSup)){
                    //System.out.println("quelque chose gene le placement de bateau (un bord ou un autre bateau)");
                    graphics.drawImage(gene, 945, 650);
                }
            }
        }

        if(affiche){
            graphics.drawImage(dejaPose, 945, 650);
        }


        pose(graphics); // pose les bateaux


        /* Pour apres il faudrait cliquer (gauche) ensuite l'image reste a la case que l'on veut puis avec le clique droit changer
         * de direction. Ou alors on garde le glissement et lors du premier clique Gauche on "valide" la case de depart.
         *
         * DANS TT LES CAS :
         * -------> utilisation des modulo avec une variable qui compte le nombre de clique droit :
         * par ex: au depart on a :
         *             int nbClicD=0;
         *             si on a un nb impaire (modulo 2 different de 0) on aura forcement une position horizontal
         *             on suppose que la pos de depart c'est la vertical vers le bas (celle faite par aurel)
         *             et que le bateau tourne dans le sens des aiguilles d'une montre
         *
         *             on part de ce principe donc :
         *             ++
         *             nbClicD=1 --> position horizontal : 1[2] != 0
         *             en revanche avec modulo 2 on a que 2 valeur possible : 0 (vertical) et 1 (horizontal)
         *             ds le mm principe : x modulo 3 --> modulo 3 = 3 valeur possible 0 , 1 , 2
         *             or nous a 4 position possible donc :
         *             x[4] --> 4 valeur possibles --> 0, 1, 2, 3
         *             si 0 = position de base (vertical vers le bas)
         *             si 1 = position horizontal vers la gauche
         *             si 2 = position vertical vers le haut
         *             si 3 = position horizontal vers la droite
         *             apres retour à 0 donc position de base et ainsi de suite
         *
         *             -----------------------------------------------------------------------
         *             il reste à savoir la modification des coordonnées (en terme de grille) :
         *
         *             pos de base = (baseX , baseY) (ici c'est uniquement le "deuxieme" points qui nous interesse
         *                                          car le premier point (ou case) c'est celle ou clique l'utilisateur
         *                                          "l'origine" )
         *             getTaille() = c'est la taille du bateau en nb de cases
         *             tCase = 90 ici, UTILISE UNIQUEMENT POUR LES COORD PAS DANS LA MATRICE CAR INUTILE
         *
         *             -> pour la matrice c'est le mm principe sans les *tCase)
         *             -> pour les coord pas besoin de for donc on remplace le i par le getTaille() mais on garde le *tCase
         *                     comme ca on obtient uniquement le "deuxieme" point
         *
         *             switch {
         *                 case 0 : (for i allant de 0 à getTaille()) : baseX=baseX         ; baseY=baseY+i*tCase
         *                 case 1 : (for i allant de 0 à getTaille()) : baseX=baseX-i*tCase ; baseY=baseY
         *                 case 2 : (for i allant de 0 à getTaille()) : baseX=baseX         ; baseY=baseY-i*tCase
         *                 case 3 : (for i allant de 0 à getTaille()) : baseX=baseX+i*tCase ; baseY=baseY
         *             }
         */

    }

    @Override
    public void passerTour(StateBasedGame stateBasedGame, int i){
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        timer-=i;
        if(isBateauxPoses() && (posX>1200 && posX<1440) && (posY>0 && posY<100) || timer<= 0){
            if (Mouse.isButtonDown(0) && timer > 0){
                saverReader.savePlateau(1, plateau);
                saverReader.initEcranTir(2);//1
                stateBasedGame.enterState(5);//2
            }else if(timer<=0){
                stateBasedGame.enterState(4);
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        super.update(gameContainer, stateBasedGame, i);
    }

    @Override
    public int getID() {
        return 2;
    }
}