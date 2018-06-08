import java.io.*;

public class SaverReader {

    File plateauJoueur1;
    File plateauJoueur2;

    private final int TAILLEPLATEAU = 12;

    public SaverReader() {
        plateauJoueur1 = new File("plateauJ1.txt");
        plateauJoueur2 = new File("plateauJ2.txt");
    }

    public void savePlateau(int idJoueur, int[][] plateau){
        //Si idJoueur == 1 -> plateauJoueur1 sinon plateauJoueur2
        File file = (idJoueur==1) ? plateauJoueur1 : plateauJoueur2;

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            addArrayToFile(plateau, writer);

            //on ferme le writer
            writer.close();
        } catch (IOException e) {
            System.out.println("ERREUR le fichier ne peut être lu");
            e.printStackTrace();
        }
    }

    private void addArrayToFile(int[][] plateau, BufferedWriter writer) throws IOException {
        //on ajoute la tableau ligne par ligne
        for(int i = 0 ; i<plateau.length; i++){
            for(int j=0; j<plateau.length; j++){
                writer.append(""+plateau[i][j]);
            }
            writer.newLine();
        }
    }

    public int[][] readPlateau(int idJoueur){
        int[][] plateauRead = new int[12][12];
        File file = (idJoueur==1) ? plateauJoueur1 : plateauJoueur2;

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            readIntByInt(plateauRead, reader);
            //on ferme le reader
            reader.close();
        } catch (IOException e) {
            System.out.println("ERREUR le fichier ne peut être lu");
            e.printStackTrace();
        }
        return plateauRead;
    }

    private void readIntByInt(int[][] plateauRead, BufferedReader reader) throws IOException {
        String ligne;
        int i = 0;
        while ((ligne=reader.readLine()) != null) {
            for (int j=0; j<ligne.length(); j++){
                plateauRead[i][j]= Character.getNumericValue((char)(ligne.charAt(j)));
            }
            i++;
        }
    }
}
