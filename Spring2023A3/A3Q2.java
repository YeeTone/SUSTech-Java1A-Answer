package Spring2023A3;

import java.util.Scanner;

public class A3Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        boolean[][] attackable = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int xi = sc.nextInt(), yi = sc.nextInt();
            deal(attackable, xi, yi, DESOLATOR);
        }

        for (int i = 0; i < k; i++) {
            int oi = sc.nextInt(), pi = sc.nextInt();
            deal(attackable, oi, pi, GRAND_CANNON);
        }

        System.out.println(answer(attackable));
        sc.close();
    }

    private static final int[][] DESOLATOR = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
    };

    private static final int[][] GRAND_CANNON = {
            {-2, -2},
            {-2, -1},
            {-2, 0},
            {-2, 1},
            {-2, 2},
            {-1, -2},
            {-1, 2},
            {0, -2},
            {0, 2},
            {1, -2},
            {1, 2},
            {2, -2},
            {2, -1},
            {2, 0},
            {2, 1},
            {2, 2}
    };

    public static boolean checkBound(boolean[][] attackable, int x, int y) {
        return 0 <= x && x < attackable.length && 0 <= y && y < attackable[0].length;
    }

    public static void deal(boolean[][] attackable, int xi, int yi, int[][] dimensionArray) {
        if (checkBound(attackable, xi, yi)) {
            attackable[xi][yi] = true;
        }
        for (int[] da : dimensionArray) {
            if (checkBound(attackable, xi + da[0], yi + da[1])) {
                attackable[xi + da[0]][yi + da[1]] = true;
            }
        }
    }

    public static int answer(boolean[][] attackable) {
        int ans = 0;
        for (boolean[] ba : attackable) {
            for (boolean b : ba) {
                if (!b) {
                    ans += 1;
                }
            }
        }
        return ans;

    }
}
