import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static utils.LocalJudgeUtils.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class QuestionTwoJudge {
    @Test
    public void test01_ChessComponentClass() {
        assertNotNull(findChessComponentClass());
    }

    @Test
    public void test02_ChessComponentClassFields() {
        assertNotNull(findSourceInChessComponentClass());
        assertNotNull(findChessColorFieldInChessComponentClass());
        assertNotNull(findNameFieldInChessComponentClass());
    }

    @Test
    public void test03_ChessComponentClassMethods() {
        assertNotNull(findChessComponentConstructor());
        assertNotNull(findCanMoveToMethodInChessComponentClass());
        assertNotNull(findToStringMethodInChessComponentClass());
    }

}
