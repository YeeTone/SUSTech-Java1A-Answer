package Spring2023A3;

import java.util.Scanner;

public class A3Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            String x = sc.next();
            for (int j = 0; j < n; j++) {
                board[i][j] = x.charAt(j);
            }
        }

        int x = sc.nextInt(), y = sc.nextInt();

        int[][] directionArray =
                {{-1, -1}, {-1, 0}, {-1, 1},
                        {0, -1}, {0, 1},
                        {1, -1}, {1, 0}, {1, 1}};

        if (board[x][y] == 'x') {
            System.out.println(-1);
            sc.close();
            System.exit(0);
        }

        int answer = 0;
        for (int[] da : directionArray) {
            int newX = x + da[0], newY = y + da[1];
            if (0 <= newX && newX < n && 0 <= newY && newY < n) {
                if (board[newX][newY] == 'x') {
                    answer += 1;
                }
            }
        }

        System.out.println(answer);
        sc.close();
    }
}
