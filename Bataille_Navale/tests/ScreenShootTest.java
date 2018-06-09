import org.junit.Assert;
import org.junit.Test;

public class ScreenShootTest {
    @Test
    public void comparePlateauAndShootTest(){
        ScreenShoot screenShoot  = new ScreenShoot(7);
        int plateauPlacement[][] = {
                {1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,3,3,3,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,5,5,5,5,5,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,4,4,4,4,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,2,2,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int ecranTir[][] = {
                {9,9,9,9,9,7,7,7,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9},
                {9,9,9,9,8,8,9,9,9,9},
                {9,9,9,9,9,9,9,9,9,9}
        };
        int x;
        int y;

        //Test sur premier IF
        x=2;
        y=6;
        Assert.assertTrue(screenShoot.comparePlateauAndShoot(x,y,plateauPlacement,ecranTir));
        screenShoot.comparePlateauAndShoot(x,y,plateauPlacement,ecranTir);
        for (int i = 0; i < plateauPlacement.length; i++) {
            for (int j = 0; j < plateauPlacement.length; j++) {
                System.out.print(plateauPlacement[i][j]);
            }
            System.out.println();
        }
    }

    @Test
    public void removeElementTest(){
        ScreenShoot screenShoot  = new ScreenShoot(7);

        int plateauPlacement[][] = {
                {1,1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,3,3,3,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,5,5,5,5,5,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,4,4,4,4,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,2,2,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int plateauPlacementJ[][] = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,3,3,3,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,5,5,5,5,5,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,4,4,4,4,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,2,2,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0}
        };

    }
}
