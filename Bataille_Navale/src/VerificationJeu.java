import java.util.ArrayList;

public class VerificationJeu {
    int[][] plateauJ1;
    int[][] plateauJ2;

    SaverReader saverReader;

    public VerificationJeu() {
        saverReader = new SaverReader();
        plateauJ1 = saverReader.readPlateau(1);
        plateauJ2 = saverReader.readPlateau(2);
    }

    public boolean verifJ1Perdu(){
        return !checkForShips(plateauJ1);
    }

    public boolean verifJ2Perdu(){
        return !checkForShips(plateauJ2);
    }

    public boolean checkForShips(int[][] plateau) {
        for(int i = 0; i<plateau.length; i++){
            ArrayList<Integer> tabTest = new ArrayList<Integer>();
            for (int j = 0 ; j<plateau.length; j++){
                tabTest.add(plateau[i][j]);
            }
            if(tabTest.contains(2) ||
                    tabTest.contains(3) ||
                    tabTest.contains(4) ||
                    tabTest.contains(5) ||
                    tabTest.contains(6) ){
                return true;
            }
        }
        return false;
    }
}
