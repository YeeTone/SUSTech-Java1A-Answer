package Spring2023A2;

import java.util.Scanner;

public class A2Q2 {
    private static final int[][] DIRECTION_ARRAY = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    private static boolean checkBoundary(int x, int y, int n, int m){
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.next().charAt(0);
            }
        }

        while (k > 0) {
            char[][] newBoard = new char[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(board[i], 0, newBoard[i], 0, m);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'B') {
                        for (int[] direction: DIRECTION_ARRAY){
                            int x = i + direction[0], y = j + direction[1];
                            if(checkBoundary(x, y, n, m) && board[x][y] == 'L'){
                                newBoard[x][y] = 'B';
                            }
                        }
                    }
                }
            }

            board = newBoard;
            k--;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
