import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.LocalJudgeUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static utils.LocalJudgeUtils.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class QuestionFourJudge {


    @Test
    public void test01_ConcreteChessGameClassMoveKnight() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            Class<?> chessColorClass = findChessColorClass();
            Object[] enumConstants = chessColorClass.getEnumConstants();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/move/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(3, 3));
            assertNotNull(canMovePoints);
            String answer = "(1,4),(2,1),(2,5),(4,5),(5,2),(5,4)";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((3,3)) fail, please check"
            );

            assertEquals(
                    enumConstants[1],
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame),
                    "getCurrentPlayer() fail, current player should be WHITE"
            );

            assertFalse(
                    (boolean) callMoveChessMethodInConcreteChessGameClass(concreteChessGame, 3, 3, 1, 4),
                    "Cannot moveChess (3,3) to (1,4), moveChess method fail please check"
            );

            assertEquals(
                    enumConstants[1],
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame),
                    "getCurrentPlayer() fail, current player should be WHITE"
            );

            chessboard = Files.readAllLines(Paths.get("./testcases/move/game2.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            assertEquals(
                    enumConstants[1],
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame),
                    "getCurrentPlayer() fail, current player should be WHITE"
            );

            canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(2, 6));
            assertNotNull(canMovePoints);
            answer = "(0,5),(0,7),(1,4),(4,5),(4,7)";
            collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((2,6)) fail, please check"
            );


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02_ConcreteChessGameClassMoveRook() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            Class<?> chessColorClass = findChessColorClass();
            Object[] enumConstants = chessColorClass.getEnumConstants();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/move/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(7, 0));
            assertNotNull(canMovePoints);
            String answer = "";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((7,0)) fail, please check"
            );

            chessboard = Files.readAllLines(Paths.get("./testcases/move/game2.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            assertEquals(
                    enumConstants[1],
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame),
                    "getCurrentPlayer() fail, current player should be WHITE"
            );

            canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(7, 7));
            assertNotNull(canMovePoints);
            answer = "(0,7),(1,7),(2,7),(3,7),(4,7),(5,7),(6,7),(7,5),(7,6)";
            collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((7,7)) fail, please check"
            );

            assertEquals(
                    enumConstants[1],
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame),
                    "getCurrentPlayer() fail, current player should be WHITE"
            );

            assertFalse(
                    (boolean) callMoveChessMethodInConcreteChessGameClass(concreteChessGame, 7, 7, 100, 100),
                    "moveChess (7,7) to (100,100) fail, please check"
            );

            assertEquals(
                    enumConstants[1],
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame),
                    "getCurrentPlayer() fail, current player should be WHITE"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03_ConcreteChessGameClassMoveKing() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/move/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(7, 4));
            assertNotNull(canMovePoints);
            String answer = "(6,3),(6,4),(7,3)";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((7,4)) fail, please check"
            );

            Object chess = callGetChessMethodInConcreteChessGameClass(concreteChessGame, 7, 4);
            assertNotNull(
                    chess,
                    "Chess on (7,4) should not be null, getChess(source) method fail, please check"
            );

            chessboard = Files.readAllLines(Paths.get("./testcases/move/game2.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(0, 4));
            assertNotNull(canMovePoints);
            answer = "(0,5),(1,3),(1,4)";
            collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((0,4)) fail, please check"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test04_ConcreteChessGameClassMoveQueen() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/move/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(5, 3));
            assertNotNull(canMovePoints);
            String answer = "(1,7),(2,0),(2,6),(3,1),(3,5),(4,2),(4,4),(5,0),(5,1),(5,2),(5,4),(6,3),(6,4),(7,3)";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((5,3)) fail, please check"
            );

            chessboard = Files.readAllLines(Paths.get("./testcases/move/game2.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(0, 3));
            assertNotNull(canMovePoints);
            answer = "(1,3),(1,4),(2,5),(3,6),(4,7)";
            collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((0,3)) fail, please check"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test05_ConcreteChessGameClassMovePawn() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/move/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(3, 4));
            assertNotNull(canMovePoints);
            String answer = "";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((3,4)) fail, please check"
            );

            chessboard = Files.readAllLines(Paths.get("./testcases/move/game2.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(1, 5));
            assertNotNull(canMovePoints);
            answer = "(2,5),(2,6),(3,5)";
            collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((1,5)) fail, please check"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test06_ConcreteChessGameClassMoveBishop() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/move/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(4, 1));
            assertNotNull(canMovePoints);
            String answer = "(0,5),(1,4),(2,3),(3,0),(3,2),(5,0),(5,2),(6,3),(7,4)";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((4,1)) fail, please check"
            );

            chessboard = Files.readAllLines(Paths.get("./testcases/move/game2.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(7, 2));
            assertNotNull(canMovePoints);
            answer = "(2,7),(3,6),(4,5),(5,4),(6,3)";
            collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((7,2)) fail, please check"
            );

            Object chess = callGetChessMethodInConcreteChessGameClass(concreteChessGame, 7, 2);
            assertNotNull(
                    chess,
                    "Chess on (7,2) should not be null, getChess(source) method fail, please check"
            );
            List<Object> canMoveToList = (List<Object>) callCanMoveToMethodInChessComponentClass(chess);
            String canMoveToListStr = canMoveToList
                    .stream()
                    .map(point -> point.toString())
                    .sorted()
                    .collect(Collectors.joining(","));
            String canMoveToListAns = "(2,7),(3,6),(4,5),(5,4),(6,3)";
            assertEquals(
                    canMoveToListAns,
                    canMoveToListStr,
                    "chess on (7,2) call canMoveTo() (return a List) method fail, please check"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test07_ConcreteChessGamePolymorphism() {
        Object concreteChessGame = newConcreteChessGame();
        Field chessboardField = findChessComponentsFieldInConcreteChessGameClass();
        chessboardField.setAccessible(true);
        ChessComponent[][] chessComponents = new ChessComponent[1][1];
        chessComponents[0][0] = new TestChessComponent();
        try {
            chessboardField.set(concreteChessGame,chessComponents);
            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(0, 0));
            assertNotNull(canMovePoints,"Return List is null. Please check whether use Polymorphism");
            String answer = "(1,2),(2,1),(2,2),(3,1)";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "Please check whether using Polymorphism"
            );
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test08_ConcreteChessGameClassAdditional() {
        try {
            Object concreteChessGame = newConcreteChessGame();
            List<String> chessboard = Files.readAllLines(Paths.get("./testcases/move/game1.txt"));
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, chessboard);

            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(2, 0));
            assertNotNull(canMovePoints);
            String answer = "";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertEquals(
                    answer,
                    collect,
                    "getCanMoves((2,0)) fail, please check"
            );
            Object chess = callGetChessMethodInConcreteChessGameClass(concreteChessGame, 2, 0);
            assertNotNull(
                    chess,
                    "Empty Chess on (2,0) should not be null, getChess(source) method fail, please check"
            );
            assertEquals(
                    "_",
                    callToStringMethodInChessComponentClass(chess),
                    "Empty chess on (2,0) toString() should be '_'"
            );

            chess = callGetChessMethodInConcreteChessGameClass(concreteChessGame, 7, 5);
            assertNotNull(
                    chess,
                    "Bitshop Chess on (7,5) should not be null, getChess(source) method fail, please check"
            );
            assertEquals(
                    "b",
                    callToStringMethodInChessComponentClass(chess),
                    "Bitshop chess on (7,5) toString() should be 'b'"
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TestChessComponent extends ChessComponent {
    //"(1,2),(2,1),(2,2),(3,1)"
    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> points = new ArrayList<>();
        points.add(new ChessboardPoint(1, 2));
        points.add(new ChessboardPoint(2, 1));
        points.add(new ChessboardPoint(2, 2));
        points.add(new ChessboardPoint(3, 1));
        return points;
    }
}