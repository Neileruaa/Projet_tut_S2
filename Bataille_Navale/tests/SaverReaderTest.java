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


        Assert.assertArrayEquals(plateau,saverReader.readPlateau(1));
    }
}
