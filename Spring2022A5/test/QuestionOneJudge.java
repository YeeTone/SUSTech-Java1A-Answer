import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static utils.LocalJudgeUtils.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class QuestionOneJudge {
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
        Object chessboardPoint = newChessboardPoint(1, 2);
        assertEquals(
                1,
                callGetXMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint (1,2), getX() should be 1"
        );
        assertEquals(
                2,
                callGetYMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint (1,2), getY() should be 2"
        );
        chessboardPoint = newChessboardPoint(2, 1);
        assertEquals(
                2,
                callGetXMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint (2,1), getX() should be 2"
        );
        assertEquals(
                1,
                callGetYMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint (2,1), getY() should be 1"
        );
    }

    @Test
    public void test05_ChessboardPointClassToString() {
        Object chessboardPoint = newChessboardPoint(6, 10);
        assertEquals(
                "(6,10)",
                callToStringMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint (1,2), toString() should be (1,2)"
        );
        chessboardPoint = newChessboardPoint(100, 66);
        assertEquals(
                "(100,66)",
                callToStringMethodInChessboardPointClass(chessboardPoint),
                "ChessboardPoint (100,66), toString() should be (100,66)"
        );
    }

    @Test
    public void test06_ChessboardPointClassOffset() {
        Object chessboardPoint = newChessboardPoint(1, 1);
        Object newChessboardPoint = callOffsetMethodInChessboardPointClass(chessboardPoint, 2, 4);
        assertEquals(
                "(3,5)",
                callToStringMethodInChessboardPointClass(newChessboardPoint),
                "(1,1) -> offset(2,4) should return (3,5)"
        );
        assertNotSame(
                chessboardPoint,
                newChessboardPoint,
                "offset() method should return a new instance"
        );
        newChessboardPoint = callOffsetMethodInChessboardPointClass(chessboardPoint, -2, 2);
        assertNull(
                newChessboardPoint,
                "(1,1) -> offset(-2,2) should return null value"
        );
        newChessboardPoint = callOffsetMethodInChessboardPointClass(chessboardPoint, 5, 10);
        assertNull(
                newChessboardPoint,
                "(1,1) -> offset(5,10) should return null value"
        );
    }

}
