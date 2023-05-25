package labs.lab9;

import java.util.Scanner;

public class Octagon {
    private static final int[][] DIRECTIONS = {
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1},
            {-1, -1},
            {-1, 0},
            {-1, 1}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] octagon = organizeOctagon(n);
        printOctagon(octagon);
        sc.close();
    }

    private static int[][] organizeOctagon(int n){
        int[][] octagon = new int[n][n];

        int x = 0, y = (n - 1) / 3;
        int length = (n - 1) / 3 + 1;

        for (int i = 0; i < 8; i++) {
            int[] bias = DIRECTIONS[i];
            for (int j = 0; j < length - 1; j++) {
                x += bias[0];
                y += bias[1];
                octagon[x][y] = 1;
            }
        }

        return octagon;
    }

    private static void printOctagon(int[][] octagon){
        for (int[] ints : octagon) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }
}
