package Spring2023A2;

import java.util.Scanner;

public class A2Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] poly1 = new int[n];
            for (int j = 0; j < n; j++) {
                poly1[j] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] poly2 = new int[m];
            for (int j = 0; j < m; j++) {
                poly2[j] = sc.nextInt();
            }

            int[] result = multiply(poly1, poly2, m, n);

            for (int j = 0; j < m + n - 1; j++) {
                System.out.print(result[j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }

    private static int[] multiply(int[] poly1, int[] poly2, int m, int n) {
        int[] result = new int[m + n - 1];

        for (int k = 0; k < n; k++) {
            for (int j = 0; j < m; j++) {
                result[k + j] += poly1[k] * poly2[j];
            }
        }

        return result;
    }

}
