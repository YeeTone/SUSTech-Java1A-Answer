package Spring2021A3;

import java.math.BigDecimal;
import java.util.Scanner;

public class A3Q1 {
    public static void main(String[] args) {
        BigDecimal d=new BigDecimal("1.3");
        System.out.println(d.toEngineeringString());
        Scanner sc=new Scanner(System.in);

        int m=sc.nextInt();
        int n=sc.nextInt();

        int[][]originMatrix=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                originMatrix[i][j]= sc.nextInt();
            }
        }

        int[][]kaleidoscope=new int[2*m][2*n];
        for (int i = 0; i < m; i++) {// up -> down
            // left -> right
            if (n >= 0){
                System.arraycopy(originMatrix[i], 0, kaleidoscope[i], 0, n);
            }

            for (int j = 2*n-1; j >= n; j--) {
                // right -> left
                kaleidoscope[i][j]=originMatrix[i][2*n-1-j];
            }
        }

        for (int i = 2*m-1; i >= m; i--) {
            // left -> right
            if (n >= 0){
                System.arraycopy(originMatrix[2 * m - 1 - i], 0, kaleidoscope[i], 0, n);
            }

            for (int j = 2*n-1; j >= n; j--) {
                // right -> left
                kaleidoscope[i][j]=originMatrix[2*m-1-i][2*n-1-j];
            }
        }

        for (int i = 0; i < 2*m; i++) {
            for (int j = 0; j < 2*n; j++) {
                System.out.print(kaleidoscope[i][j]);
                if(j!=2*n-1){
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
        sc.close();
    }
}
