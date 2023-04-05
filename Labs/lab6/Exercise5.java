import java.util.Arrays;
import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int[][] square = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    square[i][j] = sc.nextInt();
                }
            }

            System.out.println(check(square) ? "Yes": "No");
        }
        sc.close();
    }

    private static boolean check(int[][] square){
        return checkRows(square) && checkColumns(square) && checkSubSquares(square);
    }

    private static boolean checkRows(int[][] square) {
        boolean[] appear = new boolean[9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(appear, false);
            for (int j = 0; j < 9; j++) {
                int num = square[i][j];
                appear[num - 1] = true;
            }

            for (int j = 0; j < 9; j++) {
                if (!appear[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkColumns(int[][] square) {
        boolean[] appear = new boolean[9];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(appear, false);
            for (int j = 0; j < 9; j++) {
                int num = square[j][i];
                appear[num - 1] = true;
            }

            for (int j = 0; j < 9; j++) {
                if (!appear[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean checkSubSquares(int[][] square) {
        boolean[] appear = new boolean[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Arrays.fill(appear, false);

                int startX = i * 3, startY = j * 3;
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        int num = square[startX + k][startY + l];
                        appear[num - 1] = true;
                    }
                }

                for (int k = 0; k < 9; k++) {
                    if (!appear[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
