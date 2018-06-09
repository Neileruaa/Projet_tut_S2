import org.junit.Assert;
import org.junit.Test;

public class SaverReaderTest {

    @Test
    public void savePlateauTest(){
        SaverReader saverReader = new SaverReader();

        //tab test
        int plateau[][] = {
                {1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,2,2,2,2,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1}
        };

        saverReader.savePlateau(1,plateau);
        saverReader.savePlateau(2,plateau);


        Assert.assertArrayEquals(plateau,saverReader.readPlateau(1));
    }

    @Test
    public void readEcranTir(int idJoueur){
        SaverReader saverReader = new SaverReader();

        //tab test
        int plateau[][] = {
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,10,10,10,10,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,8,9,9,9,9,9,9},
                {9,9,9,8,9,9,9,9,9,9},
                {9,9,9,8,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9}
        };



    }
}
