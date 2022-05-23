import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import utils.OnlineDataUtilQ4;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static utils.OnlineJudgeUtils.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestFourOnlineJudge {        
    OnlineDataUtilQ4 dataUtil = new OnlineDataUtilQ4();

    String[][][] answerList = dataUtil.getAnswerList();
    int[][][][] testPointList = dataUtil.getTestPointList();

    int[][][][] movePointList = dataUtil.getMovePointList();
    String[][][] moveAfterGameList = dataUtil.getMoveAfterGameList();
    boolean[][][] moveFlagList = dataUtil.getMoveFlagList();

    static int FileList = 5;
    static final String FilePath = "./testcases/chessboards/game%d.txt";
    static int K=0,Q=1,B=2,N=3,R=4,P=5;

    static List<String>[] games = new List[FileList];
    static Class<?> chessColor = findChessColorClass();
    static Object[] enumConstants = chessColor.getEnumConstants();
    static Object[][] currentPlayers = new Object[FileList][2];
    String[] msg = {
            "getCanMoves((%d,%d)) fail, please check",
            "getCurrentPlayer() fail, current player should be %s",
            "Cannot moveChess (%d,%d) to (%d,%d), moveChess method fail please check",
            "getCapturedChess(%s) fail, please check",
            "getChess(source) method fail, Chess on (%d,%d) should not be null, please check",
            "Return List is null. Please check whether use Polymorphism",
            "Please check whether using Polymorphism"
    };


    static List<Arguments> readFiles() throws IOException {
        for(int i=0;i<FileList;i++){
            games[i] = Files.readAllLines(Paths.get(String.format(FilePath,i+1)));
            currentPlayers[i][0] = games[i].get(games[i].size()-1).equals("w")?enumConstants[1]:enumConstants[0];      
            currentPlayers[i][1] = games[i].get(games[i].size()-1).equals("w")?enumConstants[0]:enumConstants[1];
        }
        return Arrays.asList(Arguments.of(games,1));

    }


    @ParameterizedTest
    @MethodSource("readFiles")  
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test01_ConcreteChessGameClassMoveKing(List<String>[] games) {
        for(int i=0;i<FileList;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);

            test08_testGetCanMovePoints(concreteChessGame,testPointList[i][K],answerList[i][K]);

            testMoveChess(concreteChessGame,currentPlayers[i],movePointList[i][K],moveAfterGameList[i][K],moveFlagList[i][K]);
        }

    }

    @ParameterizedTest
    @MethodSource("readFiles")  
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test02_ConcreteChessGameClassMoveQueen(List<String>[] games) {
        for(int i=0;i<FileList;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);

            test08_testGetCanMovePoints(concreteChessGame,testPointList[i][Q],answerList[i][Q]);

            testMoveChess(concreteChessGame,currentPlayers[i],movePointList[i][Q],moveAfterGameList[i][Q],moveFlagList[i][Q]);
        }
    }

    @ParameterizedTest
    @MethodSource("readFiles")  
    @Timeout(value = 4000,unit = TimeUnit.MILLISECONDS)
    public void test03_ConcreteChessGameClassMoveBishop(List<String>[] games) {
        for(int i=0;i<FileList;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);

            test08_testGetCanMovePoints(concreteChessGame,testPointList[i][B],answerList[i][B]);

            testMoveChess(concreteChessGame,currentPlayers[i],movePointList[i][B],moveAfterGameList[i][B],moveFlagList[i][B]);
        }
    }

    @ParameterizedTest
    @MethodSource("readFiles") 
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test04_ConcreteChessGameClassMoveKnight(List<String>[] games) {
        for(int i=0;i<FileList;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);

            test08_testGetCanMovePoints(concreteChessGame,testPointList[i][N],answerList[i][N]);

            testMoveChess(concreteChessGame,currentPlayers[i],movePointList[i][N],moveAfterGameList[i][N],moveFlagList[i][N]);
        }
    }

    @ParameterizedTest
    @MethodSource("readFiles") 
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test05_ConcreteChessGameClassMoveRook(List<String>[] games) {
        for(int i=0;i<FileList;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);

            test08_testGetCanMovePoints(concreteChessGame,testPointList[i][R],answerList[i][R]);

            testMoveChess(concreteChessGame,currentPlayers[i],movePointList[i][R],moveAfterGameList[i][R],moveFlagList[i][R]);
        }

    }


    @ParameterizedTest
    @MethodSource("readFiles")  
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test06_ConcreteChessGameClassMovePawn(List<String>[] games) {
        for(int i=0;i<FileList;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);

            test08_testGetCanMovePoints(concreteChessGame,testPointList[i][P],answerList[i][P]);

            testMoveChess(concreteChessGame,currentPlayers[i],movePointList[i][P],moveAfterGameList[i][P],moveFlagList[i][P]);
        }

    }

    @Test     
    public void test07_ConcreteChessGamePolymorphism() {
        Object concreteChessGame = newConcreteChessGame();
        Field chessboardField = findChessComponentsFieldInConcreteChessGameClass();
        chessboardField.setAccessible(true);
        ChessComponent[][] chessComponents = new ChessComponent[1][1];
        chessComponents[0][0] = new OnlineTestChessComponent();
        try {
            chessboardField.set(concreteChessGame,chessComponents);
            List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(0, 0));
            assertNotNull(canMovePoints,"Return List is null. Please check whether use Polymorphism");
            String answer = "(1,1),(2,0),(3,3),(3,4),(4,0),(6,6),(7,5),(7,6)";
            String collect = canMovePoints
                    .stream()
                    .map(point -> point.toString())
                    .collect(Collectors.joining(","));
            assertTrue(answer.equals(collect), "Please check whether using Polymorphism");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void test08_testGetCanMovePoints(Object concreteChessGame,int[][] testPoint, String[] answer){

            for (int i = 0; i < testPoint.length; i++) {
                List<Object> canMovePoints = (List<Object>) callGetCanMovePointsMethodInConcreteChessGameClass(concreteChessGame, newChessboardPoint(testPoint[i][0], testPoint[i][1]));
                assertNotNull(canMovePoints, String.format(msg[0], testPoint[i][0], testPoint[i][1]));
                String collect = canMovePoints
                        .stream()
                        .map(point -> point.toString())
                        .collect(Collectors.joining(","));
                assertTrue(
                        collect.equals(answer[i]),
                        String.format(msg[0], testPoint[i][0], testPoint[i][1])
                );
                Object chess = callGetChessMethodInConcreteChessGameClass(concreteChessGame, testPoint[i][0], testPoint[i][1]);
                assertNotNull(
                        chess,
                        String.format(msg[4], testPoint[i][0], testPoint[i][1])
                );
            }
    }

    public void testMoveChess(Object concreteChessGame,Object[] currentPlayer,int[][] movePoint,String[] moveAfterGames,boolean[] moveFlag){
        Object current = currentPlayer[0];
        for(int i=0;i<moveFlag.length;i++){
            if(moveFlag[i]){
                testMoveTrue(concreteChessGame,movePoint[i][0],movePoint[i][1],movePoint[i][2],movePoint[i][3]);
                if(current==currentPlayer[0])
                    current=currentPlayer[1];
                else
                    current=currentPlayer[0];
            }
            else
                testMoveFalse(concreteChessGame,movePoint[i][0],movePoint[i][1],movePoint[i][2],movePoint[i][3]);

            assertTrue(
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame)==current,
                    String.format(msg[1],current.toString())
            );
            testAfterMoveGame(concreteChessGame,moveAfterGames[i*2],moveAfterGames[i*2+1]);
        }
    }
    public void testMoveFalse(Object concreteChessGame, int sourceX, int sourceY, int targetX, int targetY){
        assertFalse(
                (boolean) callMoveChessMethodInConcreteChessGameClass(concreteChessGame, sourceX,sourceY,targetX,targetY),
                String.format(msg[2],sourceX,sourceY,targetX,targetY)
        );
    }

    public void testMoveTrue(Object concreteChessGame, int sourceX, int sourceY, int targetX, int targetY){
        assertTrue(
                (boolean) callMoveChessMethodInConcreteChessGameClass(concreteChessGame, sourceX,sourceY,targetX,targetY),
                String.format(msg[2],sourceX,sourceY,targetX,targetY)
        );
    }

    public void testAfterMoveGame(Object concreteChessGame, String blackCaptured,String writeCaptured){
        assertTrue(
                ((String) callGetCapturedChessMethodInConcreteChessGameClass(concreteChessGame, enumConstants[0])).trim().equals(blackCaptured),
                String.format(msg[3],enumConstants[1].toString())
        );
        assertTrue(
                ((String) callGetCapturedChessMethodInConcreteChessGameClass(concreteChessGame, enumConstants[1])).trim().equals(writeCaptured),
                String.format(msg[3],enumConstants[1].toString())
        );
    }


}

class OnlineTestChessComponent extends ChessComponent {
    int[][] testPoints = {{1, 1},{2, 0},{3, 3},{3, 4},{4, 0},{6, 6},{7, 5},{7, 6}};
    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> points = new ArrayList<>();
        for(int i=0;i<8;i++){
            points.add(new ChessboardPoint(testPoints[i][0], testPoints[i][1]));
        }
        return points;
    }
}