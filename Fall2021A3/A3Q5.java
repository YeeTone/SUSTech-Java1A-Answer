package Fall2021A3;

import java.util.Arrays;
import java.util.Scanner;

public class A3Q5 {
    private static int color;
    private static int[][] chessboard;
    private static final boolean[] validDirs = new boolean[8];
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        color = sc.nextInt();
        chessboard = inputChessboard(sc);

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (!generateNewBoard(x, y)) {
                break;
            }
            swap();
        }
        printChessboard();
        sc.close();
    }

    private static int[][] inputChessboard(Scanner sc) {
        int[][] chessboard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard[i][j] = sc.nextInt();
            }
        }

        return chessboard;
    }

    private static boolean generateNewBoard(int x, int y) {
        if (!isValid(x, y)) {
            return false;
        }

        for (int i = 0; i < directions.length; i++) {
            if (!validDirs[i]) {
                continue;
            }

            int count = 1;
            while (true) {
                int curX = x + count * directions[i][0];
                int curY = y + count * directions[i][1];
                count++;

                if (chessboard[curX][curY] == color * -1) {
                    chessboard[curX][curY] = color;
                } else {
                    break;
                }
            }
        }
        chessboard[x][y] = color;

        return true;
    }

    private static boolean isValid(int x, int y) {
        if (!(0 <= x && x < 8 && 0 <= y && y < 8)) {
            return false;
        }

        if (chessboard[x][y] != 0) {
            return false;
        }

        Arrays.fill(validDirs, false);

        for (int i = 0; i < directions.length; i++) {
            if (isValidIn1Direction(directions[i], x, y)) {
                validDirs[i] = true;
            }
        }

        boolean result = false;
        for (boolean v : validDirs) {
            result |= v;
        }

        return result;
    }

    private static boolean isValidIn1Direction(int[] direction, int i, int j) {
        if (chessboard[i][j] != 0) {
            return false;
        }

        int opponent = 0;

        int count = 1;
        while (true) {
            int currentI = i + count * direction[0];
            int currentJ = j + count * direction[1];

            if (!(0 <= currentI && currentI < 8 && 0 <= currentJ && currentJ < 8)) {
                return false;
            }

            if (chessboard[currentI][currentJ] == 0) {
                return false;
            }

            if (chessboard[currentI][currentJ] == color) {
                return opponent > 0;
            }

            if (chessboard[currentI][currentJ] == color * -1) {
                opponent++;
            }
            count++;
        }
    }

    private static void swap() {
        color *= -1;
    }

    private static void printChessboard() {
        for (int[] row : chessboard) {
            for (int pos : row) {
                System.out.printf("%3d", pos);
            }
            System.out.println();
        }
    }
}
