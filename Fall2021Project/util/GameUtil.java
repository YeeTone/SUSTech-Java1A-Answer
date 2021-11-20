package Fall2021Project.util;

import Fall2021Project.components.ChessGridComponent;
import Fall2021Project.model.ChessPiece;

import java.awt.*;

public class GameUtil extends Util{
    private static final int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {-1, -1}, {1, -1}, {-1, 1}
    };
    private static final boolean[] flippable = new boolean[8];

    private GameUtil(){
        super();
    }

    public static boolean canFlip(ChessGridComponent[][] chessboard, int x, int y, Color color) {
        if (chessboard[x][y].getChessPiece() != null) {
            return false;
        }

        for (int i = 0; i < flippable.length; i++) {
            flippable[i] = canFlipIn1Direction(chessboard, x, y, color, i);
        }

        for (boolean b : flippable) {
            if (b) {
                return true;
            }
        }
        return false;
    }

    public static void flip(ChessGridComponent[][] chessboard, int x, int y, Color color) {

        for (int i = 0; i < flippable.length; i++) {
            flipIn1Direction(chessboard, x, y, color, i);
        }
    }

    public static boolean isPlayable(ChessGridComponent[][] chessboard, Color player) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (canFlip(chessboard, i, j, player)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isTerminate(ChessGridComponent[][] chessboard) {
        return !isPlayable(chessboard, Color.BLACK) && !isPlayable(chessboard, Color.WHITE);
    }


    private static boolean canFlipIn1Direction(ChessGridComponent[][] chessboard, int x, int y, Color color, int dirIndex) {
        int[] direction = directions[dirIndex];

        int opponent = 0;
        int move = 1;
        while (true) {
            int curX = x + move * direction[0];
            int curY = y + move * direction[1];
            if (rangeInvalid(curX) || rangeInvalid(curY)) {
                return false;
            }

            if (chessboard[curX][curY].getChessPiece() == null) {
                return false;
            }

            if (chessboard[curX][curY].getChessPiece().getColor().equals(color)) {
                return opponent > 0;
            } else {
                opponent++;
                move++;
            }
        }
    }

    private static void flipIn1Direction(ChessGridComponent[][] chessboard, int x, int y, Color color, int dirIndex) {
        if (!flippable[dirIndex]) {
            return;
        }

        int[] direction = directions[dirIndex];
        int move = 1;
        while (true) {
            int curX = x + move * direction[0];
            int curY = y + move * direction[1];
            if (rangeInvalid(curX) || rangeInvalid(curY)) {
                return;
            }

            if (chessboard[curX][curY].getChessPiece() == null) {
                return;
            }

            if (chessboard[curX][curY].getChessPiece().getColor().equals(color)) {
                return;
            } else {
                if (color == Color.BLACK) {
                    chessboard[curX][curY].setChessPiece(ChessPiece.BLACK);
                } else {
                    chessboard[curX][curY].setChessPiece(ChessPiece.WHITE);
                }
                chessboard[curX][curY].repaint();
                move++;
            }
        }
    }

    private static boolean rangeInvalid(int a) {
        return 0 > a || a >= SIZE;
    }
}
