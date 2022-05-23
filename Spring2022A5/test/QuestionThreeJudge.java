import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static utils.LocalJudgeUtils.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class QuestionThreeJudge {
    @Test
    public void test01_ChessGameClass() {
        assertNotNull(findChessGameClass());
    }

    @Test
    public void test02_ConcreteChessGameClass() {
        assertNotNull(findConcreteChessGameClass());
    }

    @Test
    public void test03_ConcreteChessGameClassFields() {
        assertNotNull(findChessComponentsFieldInConcreteChessGameClass());
        assertNotNull(findCurrentPlayerFieldInConcreteChessGameClass());
    }

    @Test
    public void test04_ConcreteChessGameClassMethods() {
        assertNotNull(findLoadChessGameMethodInConcreteChessGameClass());
        assertNotNull(findGetCurrentPlayerMethodInConcreteChessGameClass());
        assertNotNull(findGetChessMethodInConcreteChessGameClass());
        assertNotNull(findGetChessboardGraphMethodInConcreteChessGameClass());
        assertNotNull(findGetCapturedChessMethodInConcreteChessGameClass());
        assertNotNull(findGetCanMovePointsMethodInConcreteChessGameClass());
        assertNotNull(findMoveChessMethodInConcreteChessGameClass());
    }

    @Test
    public void test05_ConcreteChessGameClassLoadChessGame() {
        try {
            Object concreteChessGame = newConcreteChessGame();

            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/load/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);
            assertEquals(
                    chessboard.stream().limit(chessboard.size() - 1).collect(Collectors.joining("\n")),
                    ((String) callGetChessboardGraphMethodInConcreteChessGameClass(concreteChessGame)).trim(),
                    "Cannot loadChessGame right, please check loadChessGame() or getChessboardGraph() Method"
            );

            chessboard = Files.readAllLines(Paths.get("./testcases/load/game2.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);
            assertEquals(
                    chessboard.stream().limit(chessboard.size() - 1).collect(Collectors.joining("\n")),
                    ((String) callGetChessboardGraphMethodInConcreteChessGameClass(concreteChessGame)).trim(),
                    "Cannot loadChessGame right, please check loadChessGame() or getChessboardGraph() Method"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test06_ConcreteChessGameClassGetCapturedChess() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/captured/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);
            Object[] enumConstants = Objects.requireNonNull(findChessColorClass()).getEnumConstants();
            assertEquals(
                    "R 1" + "\n" +
                            "B 1" + "\n" +
                            "P 3",
                    ((String) callGetCapturedChessMethodInConcreteChessGameClass(concreteChessGame, enumConstants[0])).trim(),
                    "getCapturedChess(BLACK) fail, please check"
            );
            assertEquals(
                    "q 1" + "\n" +
                            "r 1" + "\n" +
                            "b 1" + "\n" +
                            "n 2" + "\n" +
                            "p 1",
                    ((String) callGetCapturedChessMethodInConcreteChessGameClass(concreteChessGame, enumConstants[1])).trim(),
                    "getCapturedChess(WHITE) fail, please check"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
