package labs.lab6;

import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the number of matrices: ");
        int n = sc.nextInt();
        int[][] resultMatrix = null;
        for (int i = 1; i <= n; i++) {
            System.out.printf("Enter the number of row and column of matrix%d:", i);
            int row = sc.nextInt(), column = sc.nextInt();

            System.out.println("Enter the elements of the matrix:");
            int[][] inputMatrix = new int[row][column];
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    inputMatrix[j][k] = sc.nextInt();
                }
            }

            if (i == 1) {
                resultMatrix = inputMatrix;
            } else {
                resultMatrix = multiply(resultMatrix, inputMatrix);
            }
        }

        System.out.println("The results:");
        printMatrix(resultMatrix);
        sc.close();
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = 0; k < b[0].length; k++) {
                    c[i][k] += a[i][j] * b[j][k];
                }
            }
        }

        return c;
    }

    private static void printMatrix(int[][] a){
        if(a == null){
            System.out.println("null");
            return;
        }
        for (int[] ints : a) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
