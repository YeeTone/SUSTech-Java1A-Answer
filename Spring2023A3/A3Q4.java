package Spring2023A3;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class A3Q4 {

    private static final int[][] I = {
            {0, 0},
            {0, 1},
            {0, 2},
            {0, 3}
    };

    private static final int[][] L = {
            {0, 0},
            {0, 1},
            {-1, 0},
            {-2, 0}
    };

    private static final int[][] O = {
            {0, 0},
            {0, 1},
            {-1, 0},
            {-1, 1}
    };

    private static final int[][] J = {
            {0, 0},
            {0, 1},
            {-1, 1},
            {-2, 1}
    };

    private static final int[][] S = {
            {0, 0},
            {0, 1},
            {-1, 1},
            {-1, 2}
    };

    private static final int[][] T = {
            {0, 0},
            {0, 1},
            {0, 2},
            {1, 1}
    };

    private static final int[][] Z = {
            {0, 0},
            {0, 1},
            {1, 1},
            {1, 2}
    };

    private static final Map<Character, int[][]> TYPE_MAPPING = Map.of(
            'I', I,
            'L', L,
            'O', O,
            'J', J,
            'S', S,
            'T', T,
            'Z', Z
    );

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt(), h = sc.nextInt();
        int n = sc.nextInt();
        int[][] gameBoard = new int[h + 3][w];

        for (int i = 0; i < n; i++) {
            char type = sc.next().charAt(0);
            int leftmost = sc.nextInt();
            handle(gameBoard, type, leftmost, w, h);
            eliminateLines(gameBoard, w, h);
            if(exceed(gameBoard, w, h)){
                break;
            }
        }

        printMat(gameBoard);
        sc.close();
    }

    private static boolean checkBoundary(int x, int y, int w, int h) {
        return 0 <= x && x < h + 3 && 0 <= y && y < w;
    }

    private static void handle(int[][] gameBoard, char type, int leftmost, int w, int h) {
        int mostDownward = 1;
        int[][] biases = TYPE_MAPPING.get(type);
        for (int i = 2; i < h + 3; i++) {
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                int x = i + biases[j][0], y = leftmost + biases[j][1];
                if (checkBoundary(x, y, w, h)) {
                    sum += gameBoard[x][y];
                }
            }

            if (sum != 0) {
                mostDownward = i - 1;
                break;
            }

            if(i == h + 2){
                mostDownward = h + 2;
                break;
            }
        }

        for (int j = 0; j < 4; j++) {
            int x = mostDownward + biases[j][0], y = leftmost + biases[j][1];
            if (checkBoundary(x, y, w, h)) {
                gameBoard[x][y] = 1;
            }
        }

    }

    private static void eliminateLines(int[][] gameBoard, int w, int h){
        while (true){
            int eliminatedIndex = -1;

            for (int i = 3; i < h + 3; i++) {
                boolean allOnes = true;
                for (int j = 0; j < w; j++) {
                    if(gameBoard[i][j] != 1){
                        allOnes = false;
                        break;
                    }
                }

                if(allOnes){
                    eliminatedIndex = i;
                    break;
                }
            }
            
            
            if(eliminatedIndex == -1){
                break;
            }else {
                for (int i = eliminatedIndex; i >= 1; i--) {
                    System.arraycopy(gameBoard[i - 1], 0, gameBoard[i], 0, w);
                }

                Arrays.fill(gameBoard[0], 0);
            }
        }
    }

    private static boolean exceed(int[][] gameBoard, int w, int h){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < w; j++) {
                if(gameBoard[i][j] != 0){
                    return true;
                }
            }
        }

        return false;
    }

    private static void printMat(int[][] gameBoard) {
        for (int i = 3; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                System.out.print(gameBoard[i][j]);
            }
            System.out.println();
        }
    }
}
