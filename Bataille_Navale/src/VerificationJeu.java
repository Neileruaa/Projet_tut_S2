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

    public boolean checkForShips(int[][] plateauEcranTir) {
        int sommeDe8 = 0;
        for(int i = 0; i<plateauEcranTir.length; i++){
            for (int j = 0 ; j<plateauEcranTir.length; j++){
                if (plateauEcranTir[i][j] ==8 ) {
                    sommeDe8++;
                }
            }
        }
        if (sommeDe8  == 17 ){
            return true;
        }
        return false;
    }
}
