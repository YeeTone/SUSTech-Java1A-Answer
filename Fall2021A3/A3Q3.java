package Fall2021A3;

import java.util.Scanner;

public class A3Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] yangHui = buildYangHui(n);
        reversePrint(yangHui);
        sc.close();
    }

    private static int[][] buildYangHui(int n) {
        int[][] yangHuiTriangle = new int[n][];

        for (int i = 0; i < n; i++) {
            yangHuiTriangle[i] = new int[i + 1];
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    yangHuiTriangle[i][j] = 1;
                } else {
                    yangHuiTriangle[i][j] = yangHuiTriangle[i - 1][j - 1] + yangHuiTriangle[i - 1][j];
                }
            }
        }

        return yangHuiTriangle;
    }

    private static void reversePrint(int[][] yanghui) {
        for (int i = yanghui.length - 1; i >= 0; i--) {
            for (int j = 0; j < yanghui[i].length; j++) {
                System.out.print(yanghui[i][j]);
                if (j != yanghui[i].length - 1) {
                    System.out.print(" ");
                }
            }
            if (i != 0) {
                System.out.println();
            }

        }
    }
}