package Fall2021Project.util;

import Fall2021Project.components.ChessGridComponent;
import Fall2021Project.model.ChessPiece;
import Fall2021Project.model.Step;
import Fall2021Project.view.GameFrame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameReader extends Util {
    private static Color currentPlayer;
    private static int gameId;
    private static List<Step> steps;
    private static ChessGridComponent[][] chessboard;
    private static BufferedReader gameReader;

    private GameReader() {
        super();
    }

    public static List<Step> getSteps() {
        return steps;
    }

    public static int getGameId() {
        return gameId;
    }

    public static ChessGridComponent[][] getChessboard() {
        return chessboard;
    }

    public static void readGame(String s) {
        try {
            initiate(s);
            readGId();
            readStep();
            readChessboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initiate(String s) throws IOException {
        if (steps != null) {
            steps.clear();
        }
        GameSaver.initiate();
        steps = new ArrayList<>();
        chessboard = new ChessGridComponent[SIZE][SIZE];
        gameReader = new BufferedReader(new FileReader(s));
    }

    private static void readGId() throws IOException {
        String[] line = gameReader.readLine().split(" ");
        gameId = Integer.parseInt(line[1]);
    }

    private static void readStep() throws IOException {
        gameReader.readLine();
        String s;

        int maxCnt = 0;

        int nextColor = -1;

        while ((s = gameReader.readLine()) != null && !s.startsWith("===")) {
            String[] split = s.split(" ");
            int sid = Integer.parseInt(split[0]);
            boolean isCheat = Boolean.parseBoolean(split[1]);
            int x = Integer.parseInt(split[2]);
            int y = Integer.parseInt(split[3]);
            Color color = parseColor(Integer.parseInt(split[4]));
            Step sx = new Step(sid, isCheat, x, y, color);
            steps.add(sx);
            GameSaver.recordStep(sx);
            maxCnt = Math.max(sid, maxCnt);

            nextColor *= -1;
        }

        Step.setStepCnt(maxCnt);
        currentPlayer = parseColor(nextColor);
        GameFrame.controller.setCurrentPlayer(currentPlayer == Color.BLACK ? ChessPiece.BLACK : ChessPiece.WHITE);

    }

    public static Color getCurrentPlayer() {
        return currentPlayer;
    }

    private static void readChessboard() throws IOException {
        for (int i = 0; i < SIZE; i++) {
            String[] split = gameReader.readLine().split(" ");
            for (int j = 0, k = 0; j < SIZE; j++, k++) {
                if (split[k].isEmpty()) {
                    j--;
                    continue;
                }
                chessboard[i][j] = new ChessGridComponent(i, j);
                ChessPiece cp = parseChessPiece(Integer.parseInt(split[k]));
                chessboard[i][j].setChessPiece(cp);
            }
        }


    }

    private static Color parseColor(int color) {
        if (color == 1) {
            return Color.WHITE;
        } else if (color == -1) {
            return Color.BLACK;
        } else {
            return null;
        }
    }

    private static ChessPiece parseChessPiece(int cp) {
        if (cp == 1) {
            return ChessPiece.WHITE;
        } else if (cp == -1) {
            return ChessPiece.BLACK;
        } else {
            return null;
        }
    }
}
