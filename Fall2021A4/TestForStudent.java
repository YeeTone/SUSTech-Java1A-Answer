package Fall2021A4;

import org.junit.jupiter.api.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestForStudent {

    private static Class<?> playerClass;
    private static Class<?> stepClass;
    private static Class<?> gameClass;
    private static Class<?> gameSystemClass;

    @BeforeAll
    static void beforeAll() {
        try {
            playerClass = Class.forName("Fall2021A4.Player");
            stepClass = Class.forName("Fall2021A4.Step");
            gameClass = Class.forName("Fall2021A4.Game");
            gameSystemClass = Class.forName("Fall2021A4.GameSystem");
        } catch (ClassNotFoundException e) {
            fail(e.toString());
        }
    }

    @Order(1)
    @Test
    void test1() throws Exception {
        String[] fieldsExpected = {"private java.lang.String Fall2021A4.Player.name"};
        String[] methodsExpected = {
                "public java.lang.String Fall2021A4.Player.getName()",
                "public void Fall2021A4.Player.setName(java.lang.String)"
        };
        Utils.checkClass(playerClass, fieldsExpected, methodsExpected);
    }

    @Order(2)
    @Test
    void test2() throws Exception {
        String[] fieldsExpected = {
                "private java.lang.String Fall2021A4.Player.name",
                "private int Fall2021A4.Player.pid",
                "private static int Fall2021A4.Player.playerCnt"
        };
        String[] methodsExpected = {
                "public java.lang.String Fall2021A4.Player.getName()",
                "public java.lang.String Fall2021A4.Player.toString()",
                "public void Fall2021A4.Player.setName(java.lang.String)",
                "public static int Fall2021A4.Player.getPlayerCnt()",
                "public int Fall2021A4.Player.getPid()"
        };
        Utils.checkClass(playerClass, fieldsExpected, methodsExpected);

        try {
            Object p1 = playerClass.getConstructor(String.class).newInstance("Zhangsan");
            Field fieldName = playerClass.getDeclaredField("name");
            Field fieldPid = playerClass.getDeclaredField("pid");


            fieldName.setAccessible(true);
            fieldPid.setAccessible(true);

            assertEquals("Zhangsan", fieldName.get(p1), "Player name is incorrect!");
            assertEquals(1, fieldPid.get(p1), "Player ID is incorrect!");
            assertEquals("Player: Zhangsan, pid: 1", p1.toString(), "toString() is incorrect!");
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            fail("Instantiation failed");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException");
        }
    }


    @Order(3)
    @Test
    void test3() throws Exception {
        String[] fieldsExpected = {"private int Fall2021A4.Step.sid"};
        String[] methodsExpected = {"public int Fall2021A4.Step.getSid()"};
        String[] methodsUnexpected = {"public void Fall2021A4.Step.setSid(int)"};
        Utils.checkClass(stepClass, fieldsExpected, methodsExpected, methodsUnexpected);
    }

    @Order(4)
    @Test
    void test4() throws Exception {
        String[] fieldsExpected = {
                "private int Fall2021A4.Step.sid",
                "private static int Fall2021A4.Step.stepCnt",
                "private int Fall2021A4.Step.rowIndex",
                "private int Fall2021A4.Step.columnIndex",
                "private int Fall2021A4.Step.color"
        };
        String[] methodsExpected = {
                "public int Fall2021A4.Step.getSid()",
                "public static int Fall2021A4.Step.getStepCnt()",
                "public int Fall2021A4.Step.getRowIndex()",
                "public void Fall2021A4.Step.setRowIndex(int)",
                "public int Fall2021A4.Step.getColumnIndex()",
                "public void Fall2021A4.Step.setColumnIndex(int)",
                "public int Fall2021A4.Step.getColor()",
                "public void Fall2021A4.Step.setColor(int)",
                "public java.lang.String Fall2021A4.Step.toString()"
        };
        Utils.checkClass(stepClass, fieldsExpected, methodsExpected);

        try {
            Object s1 = stepClass.getConstructor(int.class, int.class, int.class).newInstance(1, 2, -1);
            Field fieldSid = stepClass.getDeclaredField("sid");
            Field fieldRowIndex = stepClass.getDeclaredField("rowIndex");
            Field fieldColumnIndex = stepClass.getDeclaredField("columnIndex");
            Field fieldColor = stepClass.getDeclaredField("color");

            fieldSid.setAccessible(true);
            fieldRowIndex.setAccessible(true);
            fieldColumnIndex.setAccessible(true);
            fieldColor.setAccessible(true);

            assertEquals(1, fieldRowIndex.get(s1), "Step rowIndex is incorrect!");
            assertEquals(2, fieldColumnIndex.get(s1), "Step columnIndex is incorrect!");
            assertEquals(1, fieldSid.get(s1), "Step ID is incorrect!");
            assertEquals(-1, fieldColor.get(s1), "Step color is incorrect!");
            assertEquals("sid: 1, rowIndex: 1, columnIndex: 2, color: -1", s1.toString(), "toString() is incorrect!");
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            fail("Instantiation failed");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException");
        }
    }

    @Order(5)
    @Test
    void test5() throws Exception {
        String[] fieldsExpected = {"private java.lang.String Fall2021A4.Game.name"};
        String[] methodsExpected = {
                "public java.lang.String Fall2021A4.Game.getName()",
                "public void Fall2021A4.Game.setName(java.lang.String)"
        };
        Utils.checkClass(gameClass, fieldsExpected, methodsExpected);
    }

    @Order(6)
    @Test
    void test6() throws Exception {
        String[] fieldsExpected = {
                "private java.lang.String Fall2021A4.Game.name",
                "private int Fall2021A4.Game.gid",
                "private Fall2021A4.Player Fall2021A4.Game.whitePlayer",
                "private Fall2021A4.Player Fall2021A4.Game.blackPlayer",
                "private java.util.ArrayList Fall2021A4.Game.stepList",
                "private int[][] Fall2021A4.Game.board",
                "private static int Fall2021A4.Game.gameCnt"
        };
        String[] methodsExpected = {
                "public java.lang.String Fall2021A4.Game.getName()",
                "public void Fall2021A4.Game.setName(java.lang.String)",
                "public int Fall2021A4.Game.getGid()",
                "public Fall2021A4.Player Fall2021A4.Game.getWhitePlayer()",
                "public Fall2021A4.Player Fall2021A4.Game.getBlackPlayer()",
                "public java.util.ArrayList Fall2021A4.Game.getStepList()",
                "public boolean Fall2021A4.Game.checkStep(int)",
                "public boolean Fall2021A4.Game.addStep(Fall2021A4.Step)",
                "public int[][] Fall2021A4.Game.getBoard()",
                "public void Fall2021A4.Game.setBoard(int[][])",
                "public static int Fall2021A4.Game.getGameCnt()"
        };
        Utils.checkClass(gameClass, fieldsExpected, methodsExpected);

        try {
            Class<?> playerClass = Class.forName("Fall2021A4.Player");
            Class<?> stepClass = Class.forName("Fall2021A4.Step");

            Field fieldName = gameClass.getDeclaredField("name");
            Field fieldGid = gameClass.getDeclaredField("gid");
            Field fieldWhitePlayer = gameClass.getDeclaredField("whitePlayer");
            Field fieldBlackPlayer = gameClass.getDeclaredField("blackPlayer");
            Field fieldStepList = gameClass.getDeclaredField("stepList");
            Field fieldBoard = gameClass.getDeclaredField("board");
            Method methodGetName = gameClass.getMethod("getName");
            Method methodSetName = gameClass.getMethod("setName", String.class);
            Method methodGetGid = gameClass.getMethod("getGid");
            Method methodGetWhitePlayer = gameClass.getMethod("getWhitePlayer");
            Method methodGetBlackPlayer = gameClass.getMethod("getBlackPlayer");
            Method methodGetStepList = gameClass.getMethod("getStepList");
            Method methodCheckStep = gameClass.getMethod("checkStep", int.class);
            Method methodAddStep = gameClass.getMethod("addStep", Step.class);
            Method methodGetBoard = gameClass.getMethod("getBoard");
            Method methodSetBoard = gameClass.getMethod("setBoard", int[][].class);
            Method methodGetGameCnt = gameClass.getMethod("getGameCnt");

            Object p1 = playerClass.getConstructor(String.class).newInstance("Zhangsan");
            Object p2 = playerClass.getConstructor(String.class).newInstance("Lisi");
            int[][] initBoard = new int[8][8];
            initBoard[3][3] = initBoard[4][4] = 1;
            initBoard[3][4] = initBoard[4][3] = -1;
            Object g1 = gameClass.getConstructor(String.class, playerClass, playerClass).newInstance("Game1", p1, p2);
            Object g2 = gameClass.getConstructor(String.class, playerClass, playerClass).newInstance("Game2", p1, p2);

            fieldName.setAccessible(true);
            fieldGid.setAccessible(true);
            fieldWhitePlayer.setAccessible(true);
            fieldBlackPlayer.setAccessible(true);
            fieldStepList.setAccessible(true);
            fieldBoard.setAccessible(true);

            assertEquals("Game1", fieldName.get(g1), "Name is incorrect!");
            assertEquals("Game1", methodGetName.invoke(g1), "Name is incorrect!");
            assertEquals(1, fieldGid.get(g1), "Gid is incorrect!");
            assertEquals(1, methodGetGid.invoke(g1), "Gid is incorrect!");
            assertEquals(0, ((ArrayList<?>) fieldStepList.get(g1)).size(), "The size of stepList is incorrect!");
            assertEquals(0, ((ArrayList<?>) methodGetStepList.invoke(g1)).size(), "The size of stepList is incorrect!");
            assertArrayEquals(initBoard, (Object[]) fieldBoard.get(g1), "Board is incorrect!");
            assertArrayEquals(initBoard, (Object[]) methodGetBoard.invoke(g1), "Board is incorrect!");

            assertEquals("Game2", fieldName.get(g2), "Name is incorrect!");
            assertEquals("Game2", methodGetName.invoke(g2), "Name is incorrect!");
            assertEquals(2, fieldGid.get(g2), "Gid is incorrect!");
            assertEquals(2, methodGetGid.invoke(g2), "Gid is incorrect!");
            assertEquals(0, ((ArrayList<?>) fieldStepList.get(g2)).size(), "The size of stepList is incorrect!");
            assertEquals(0, ((ArrayList<?>) methodGetStepList.invoke(g2)).size(), "The size of stepList is incorrect!");
            assertArrayEquals(initBoard, (Object[]) fieldBoard.get(g2), "Board is incorrect!");
            assertArrayEquals(initBoard, (Object[]) methodGetBoard.invoke(g2), "Board is incorrect!");

            assertSame(p1, fieldWhitePlayer.get(g1), "WhitePlayer is incorrect!");
            assertSame(p1, methodGetWhitePlayer.invoke(g1), "WhitePlayer is incorrect!");
            assertSame(p2, fieldBlackPlayer.get(g1), "BlackPlayer is incorrect!");
            assertSame(p2, methodGetBlackPlayer.invoke(g1), "BlackPlayer is incorrect!");

            assertSame(p1, fieldWhitePlayer.get(g2), "WhitePlayer is incorrect!");
            assertSame(p1, methodGetWhitePlayer.invoke(g2), "WhitePlayer is incorrect!");
            assertSame(p2, fieldBlackPlayer.get(g2), "BlackPlayer is incorrect!");
            assertSame(p2, methodGetBlackPlayer.invoke(g2), "BlackPlayer is incorrect!");

            methodSetName.invoke(g2, "Game11");
            assertEquals("Game11", fieldName.get(g2), "Name is incorrect!");
            assertEquals("Game11", methodGetName.invoke(g2), "Name is incorrect!");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException");
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            fail("Fail to invoke your code");
        }

    }

    @Order(7)
    @Test
    void test7() throws Exception {
        String[] fieldsExpected = {"private java.util.ArrayList Fall2021A4.GameSystem.playerList",};
        String[] methodsExpected = {"public java.util.ArrayList Fall2021A4.GameSystem.getPlayerList()"};
        Utils.checkClass(gameSystemClass, fieldsExpected, methodsExpected);
    }

    @Order(8)
    @Test
    void test8() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {
                "public boolean Fall2021A4.GameSystem.checkPlayer(int)",
                "public boolean Fall2021A4.GameSystem.addPlayer(Fall2021A4.Player)"
        };
        Utils.checkClass(gameSystemClass, fieldsExpected, methodsExpected);

        try {
            Class<?> playerClass = Class.forName("Fall2021A4.Player");
            Constructor<?> playerConstructor = playerClass.getConstructor(String.class);
            Field fieldGameList = gameSystemClass.getDeclaredField("gameList");
            Field fieldPlayerList = gameSystemClass.getDeclaredField("playerList");
            Method methodGetGameList = gameSystemClass.getMethod("getGameList");
            Method methodGetPlayerList = gameSystemClass.getMethod("getPlayerList");
            Method methodCheckPlayer = gameSystemClass.getMethod("checkPlayer", int.class);
            Method methodAddPlayer = gameSystemClass.getMethod("addPlayer", playerClass);
            Method methodGetPid = playerClass.getMethod("getPid");

            fieldGameList.setAccessible(true);
            fieldPlayerList.setAccessible(true);

            Object g = gameSystemClass.newInstance();
            Object p1 = playerConstructor.newInstance("Zhangyi");
            Object p2 = playerConstructor.newInstance("Zhanger");
            Object p3 = playerConstructor.newInstance("Zhangsan");

            assertFalse((boolean) methodCheckPlayer.invoke(g, methodGetPid.invoke(p1)), "CheckPlayer is incorrect!");
            assertTrue((boolean) methodAddPlayer.invoke(g, p1), "AddPlayer is incorrect!");
            assertTrue((boolean) methodCheckPlayer.invoke(g, methodGetPid.invoke(p1)), "CheckPlayer is incorrect!");
            assertFalse((boolean) methodCheckPlayer.invoke(g, methodGetPid.invoke(p2)), "CheckPlayer is incorrect!");
            assertTrue((boolean) methodAddPlayer.invoke(g, p2), "AddPlayer is incorrect!");
            assertTrue((boolean) methodCheckPlayer.invoke(g, methodGetPid.invoke(p2)), "CheckPlayer is incorrect!");
            assertFalse((boolean) methodCheckPlayer.invoke(g, methodGetPid.invoke(p3)), "CheckPlayer is incorrect!");
            assertTrue((boolean) methodAddPlayer.invoke(g, p3), "AddPlayer is incorrect!");
            assertTrue((boolean) methodCheckPlayer.invoke(g, methodGetPid.invoke(p3)), "CheckPlayer is incorrect!");


            assertEquals(3, ((ArrayList<?>) fieldPlayerList.get(g)).size(), "The size of playerList is incorrect!");
            assertEquals(3, ((ArrayList<?>) methodGetPlayerList.invoke(g)).size(), "The size of playerList is incorrect!");
            ArrayList<?> players = (ArrayList<?>) methodGetPlayerList.invoke(g);
            assertTrue(players.contains(p1) && players.contains(p2) && players.contains(p3), "Missing player(s)!");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute!");
        } catch (ClassNotFoundException e) {
            fail("Missing class! Please check whether you have submitted all required files.");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check the existence, signature, and accessibility!");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception!");
        } catch (ClassCastException e) {
            fail("ClassCastException! Please check the fields' types and methods' return types.");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code!");
        }
    }
}
