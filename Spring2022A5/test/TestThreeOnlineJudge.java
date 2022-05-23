import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.OnlineDataUtilQ3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static utils.OnlineJudgeUtils.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestThreeOnlineJudge {
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

    static final int FileLengths = 5; 
    static final String FilePath = "./testcases/chessboards/game%d.txt";
    OnlineDataUtilQ3 dataUtil = new OnlineDataUtilQ3();

    String[][] capturedList= dataUtil.getCapturedList();

    int[][][] testChessPoint=dataUtil.getTestChessPoint();
    String[][][] ChessAnswerList=dataUtil.getChessAnswerList();
    boolean[][] testType = dataUtil.getTestType();

    String[] msg={
            "Cannot loadChessGame right, please check loadChessGame() or getChessboardGraph() Method",
            "getCurrentPlayer() fail, current player should be %s",
            "getCapturedChess(%s) fail, please check",
            "%s Chess on (%d,%d) should not be null, getChess(source) method fail, please check",
            "%s chess on (%d,%d) toString() should be '%s'"
    };

    static Object[] enumConstants = findChessColorClass().getEnumConstants();
    static Object[] currentPlayers = new Object[FileLengths];

    static List<String>[] games = new List[FileLengths];

    static List<Arguments> readFiles() throws IOException {
        for(int i=0;i<FileLengths;i++){
            games[i] = Files.readAllLines(Paths.get(String.format(FilePath,i+1)));
            currentPlayers[i] = games[i].get(games[i].size()-1).equals("w")?enumConstants[1]:enumConstants[0];      
        }
        return Arrays.asList(Arguments.of(games,1));

    }



    @ParameterizedTest
    @MethodSource("readFiles")  
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test05_ConcreteChessGameClassLoadChessGame(List<String>[] games) {
        for(int i=0;i<FileLengths;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);
            String currentGame = games[i].stream().limit(games[i].size() - 1).collect(Collectors.joining("\n"));
            assertTrue(
                    ((String) callGetChessboardGraphMethodInConcreteChessGameClass(concreteChessGame)).trim().equals(currentGame),
                    msg[0]
            );

            assertTrue(
                    callGetCurrentPlayerMethodInConcreteChessGameClass(concreteChessGame)==currentPlayers[i],
                    String.format(msg[1],currentPlayers[i].toString())
            );
        }
    }

    @ParameterizedTest
    @MethodSource("readFiles")       
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)
    public void test06_ConcreteChessGameClassGetCapturedChess(List<String>[] games) {
        for(int i=0;i<FileLengths;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]); 
            assertTrue(
                    ((String) callGetCapturedChessMethodInConcreteChessGameClass(concreteChessGame, enumConstants[0])).trim().equals(capturedList[i][0]),
                    String.format(msg[2], enumConstants[0])
            );
            assertTrue(
                    ((String) callGetCapturedChessMethodInConcreteChessGameClass(concreteChessGame, enumConstants[1])).trim().equals(capturedList[i][1]),
                    String.format(msg[2], enumConstants[1])
            );
        }
    }

    @ParameterizedTest
    @MethodSource("readFiles")
    @Timeout(value = 3000,unit = TimeUnit.MILLISECONDS)    
    public void test07_ConcreteChessGameClassAdditional(List<String>[] games) {
        for(int i=0;i<FileLengths;i++){
            Object concreteChessGame = newConcreteChessGame();
            callLoadChessGameMethodInConcreteChessGameClass(concreteChessGame, games[i]);
            for(int j=0;j<testType[i].length;j++){
                int x=testChessPoint[i][j][0],y=testChessPoint[i][j][1];
                Object chess = callGetChessMethodInConcreteChessGameClass(concreteChessGame, x,y);
                if(testType[i][j])
                    assertTrue(
                            callToStringMethodInChessComponentClass(chess).equals(ChessAnswerList[i][j][1]),
                            String.format(msg[4],ChessAnswerList[i][j][0],x,y,ChessAnswerList[i][j][1])
                    );
                else
                    assertNotNull(
                            chess,
                            String.format(msg[3],ChessAnswerList[i][j][0],x,y)
                    );
            }
        }
    }

}
