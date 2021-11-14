package Fall2021A3;

import java.util.Scanner;

public class A3Q2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Matrix[] matrices = new Matrix[N];

        for (int i = 0; i < N; i++) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            matrices[i] = new Matrix(m, n);
        }

        for (int i = 0; i < N; i++) {
            matrices[i].inputMatrix(sc);
        }

        Matrix result = matrices[0];
        for (int i = 1; i < matrices.length; i++) {
            result = result.multiply(matrices[i]);
        }
        System.out.println(result);
        sc.close();
    }


    private static class Matrix {
        private static final long MOD = 514329;
        private final int a;
        private final int b;
        private final long[][] values;

        public Matrix(int a, int b) {
            this.a = a;
            this.b = b;
            this.values = new long[a][b];
        }

        public void inputMatrix(Scanner sc) {
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    values[i][j] = sc.nextLong() % MOD;
                }
            }
        }

        public Matrix multiply(Matrix m) {
            Matrix result = new Matrix(this.a, m.b);
            for (int i = 0; i < this.a; i++) {
                for (int j = 0; j < this.b; j++) {
                    for (int k = 0; k < m.b; k++) {
                        this.values[i][j] %= MOD;
                        m.values[j][k] %= MOD;
                        result.values[i][k] %= MOD;

                        result.values[i][k] += (this.values[i][j] * m.values[j][k]) % MOD;

                        result.values[i][k] %= MOD;
                    }
                }
            }

            return result;
        }

        public String toString() {
            StringBuilder b = new StringBuilder();
            for (int i = 0; i < this.a; i++) {
                for (int j = 0; j < this.b; j++) {
                    b.append(this.values[i][j]);
                    if (j != this.b - 1) {
                        b.append(" ");
                    }
                }
                if (i != this.a - 1) {
                    b.append(System.lineSeparator());
                }

            }

            return b.toString();
        }
    }
}
