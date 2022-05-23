import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static utils.OnlineJudgeUtils.*;



@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestOneOnlineJudge {
    private static int OFFSETLength=50;

    Random random = new Random();
    private int x = random.nextInt(8);
    private int y = random.nextInt(8);
    Object chessboardPoint = newChessboardPoint(x, y);

    @Test
    public void test_ChessColor(){
        assertNotNull(findChessColorClass());
        assertTrue(findChessColorField());
    }

    @Test
    public void test01_ChessboardPointClass() {
        assertNotNull(findChessboardPointClass());
    }


    @Test
    public void test02_ChessboardPointClassFields() {
        assertNotNull(findXFieldInChessboardPointClass());
        assertNotNull(findYFieldInChessboardPointClass());
    }


    @Test       
    public void test03_ChessboardPointClassMethods() {
        assertNotNull(findChessboardPointConstructor());
        assertNotNull(findGetXMethodInChessboardPointClass());
        assertNotNull(findGetYMethodInChessboardPointClass());
        assertNotNull(findToStringMethodInChessboardPointClass());
        assertNotNull(findOffsetMethodInChessboardPointClass());
    }


    @Test       
    public void test04_ChessboardPointClassGetXY() {

        assertEquals(
                x,
                callGetXMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint ("+x+","+y+"), getX() should be "+x
        );
        assertEquals(
                y,
                callGetYMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint ("+x+","+y+"), getY() should be "+y
        );

    }

    @Test       
    public void test05_ChessboardPointClassToString() {
        assertEquals(
                "("+x+","+y+")",
                callToStringMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint ("+x+","+y+"), toString() should be "+"("+x+","+y+")"
        );
    }

    @Test      
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test06_ChessboardPointClassOffset() {

       
        for(int i=0; i<OFFSETLength; i++){
            int offsetX=x + random.nextInt(8);
            int offsetY=y + random.nextInt(8);

            Object newChessboardPoint = callOffsetMethodInChessboardPointClass(chessboardPoint, offsetX-x, offsetY-y);
            if (offsetX < 0 || offsetX > 7 || offsetY < 0 || offsetY > 7) {

                assertNull(
                        newChessboardPoint,
                        "("+x+","+y+") -> offset("+(offsetX-x)+","+(offsetY-x)+") should return null value"
                );
            }else{
                assertEquals(
                        "(" + offsetX + "," + offsetY + ")",
                        callToStringMethodInChessboardPointClass(newChessboardPoint),
                        "(" + x + "," + y + ") -> offset(" + (offsetX - x) + "," + (offsetY - x) + ") should return (" + offsetX + "," + offsetY + ")"
                );
                assertNotSame(
                        chessboardPoint,
                        newChessboardPoint,
                        "offset() method should return a new instance"
                );
            }

        }

    }

}
