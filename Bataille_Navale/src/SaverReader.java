import java.io.*;

public class SaverReader {

    File plateauJoueur1;
    File plateauJoueur2;

    File ecranTirJoueur1;
    File ecranTirJoueur2;

    private final int TAILLEPLATEAU = 12;

    public SaverReader() {
        plateauJoueur1 = new File("plateauJ1.txt");
        plateauJoueur2 = new File("plateauJ2.txt");
        ecranTirJoueur1 = new File("ecranTirJ1.txt");
        ecranTirJoueur2 = new File("ecranTirJ2.txt");
    }

    public void savePlateau(int idJoueur, int[][] plateau){
        //Si idJoueur == 1 -> plateauJoueur1 sinon plateauJoueur2
        File file = (idJoueur==1) ? plateauJoueur1 : plateauJoueur2;

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            addArrayToFile(plateau, writer);

            //on ferme le writer
        } catch (IOException e) {
            System.out.println("ERREUR le fichier ne peut être lu");
            e.printStackTrace();
        }
    }

    private void addArrayToFile(int[][] plateau, BufferedWriter writer) throws IOException {
        //on ajoute la tableau ligne par ligne
        for(int i = 0 ; i<plateau.length; i++){
            for(int j=0; j<plateau.length; j++){
                writer.append(""+plateau[j][i]);
            }
            writer.newLine();
        }
        writer.close();
    }

    public int[][] readPlateau(int idJoueur){
        int[][] plateauRead = new int[TAILLEPLATEAU][TAILLEPLATEAU];
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

    /**
     * On crée un fichier de tir qui stockera le tableau à afficher à chaque tour.
     * On le comparera à plateauJ1.txt pour choisir l'action à faire.
     * Il faut préciser l'idJoueur, pour lui associer son fichier
     * @param idJoueur
     */
    public void initEcranTir(int idJoueur){
        //Si idJoueur == 1 -> plateauJoueur1 sinon plateauJoueur2
        File file = (idJoueur==1) ? ecranTirJoueur1 : ecranTirJoueur2;

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for(int i = 0; i<(TAILLEPLATEAU-2); i++){
                for(int j = 0; j<(TAILLEPLATEAU-2); j++){
                    writer.append(""+9);
                }
                writer.newLine();
            }
            //on ferme le writer
            writer.close();
        } catch (IOException e) {
            System.out.println("ERREUR le fichier ne peut être lu");
            e.printStackTrace();
        }
    }

    public int[][] readEcranTir(int idJoueur){
        int[][] plateauRead = new int[TAILLEPLATEAU][TAILLEPLATEAU];
        File file = (idJoueur==1) ? ecranTirJoueur1 : ecranTirJoueur2;

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

    public void saveEcranTir(int idJoueur, int[][] ecranTir){
        //Si idJoueur == 1 -> plateauJoueur1 sinon plateauJoueur2
        File file = (idJoueur==1) ? ecranTirJoueur1 : ecranTirJoueur2;

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            //on ajoute la tableau ligne par ligne
            for(int i = 0 ; i<ecranTir.length; i++){
                for(int j=0; j<ecranTir.length; j++){
                    writer.append(""+ecranTir[j][i]);
                }
                writer.newLine();
            }
            writer.close();

            //on ferme le writer
        } catch (IOException e) {
            System.out.println("ERREUR le fichier ne peut être lu");
            e.printStackTrace();
        }
    }
}
