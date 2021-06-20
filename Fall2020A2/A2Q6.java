import java.io.*;
import java.util.StringTokenizer;

public class A2Q6 {

    public static void main(String[] args) {
        Reader in = new Reader(System.in);
        int N = in.nextInt(), k = in.nextInt();
        int[][][] matrix = new int[N][][];
        int minRow = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < N; i++) {
            int n = in.nextInt(), m = in.nextInt();
            if (minRow > n) {
                minRow = n;
                minIdx = i;
            }
            matrix[i] = new int[n][m];
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    matrix[i][row][col] = in.nextInt();
                }
            }
        }
        int[][] mid = matrix[minIdx];
        for (int i = minIdx + 1; i < minIdx + N; i++) {
            mid = multiplyMatrix(mid, matrix[i % N]);
        }

        mid = pow(mid, k - 1);

        int[][] res = identity(matrix[0].length);
        for (int i = 0; i < minIdx; i++) {
            res = multiplyMatrix(res, matrix[i]);
        }
        res = multiplyMatrix(res, mid);
        for (int i = minIdx; i < N; i++) {
            res = multiplyMatrix(res, matrix[i]);
        }

        int n = res.length, m = res[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] pow(int[][] a, int n) {
        int[][] x = identity(a.length);
        while (n != 0) {
            if ((n & 1) == 1) {
                x = multiplyMatrix(x, a);
            }
            a = multiplyMatrix(a, a);
            n >>= 1;
        }
        return x;
    }

    private static int[][] identity(int n) {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }
        return res;
    }

    private static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int m = a.length, n = a[0].length, p = b[0].length;
        int[][] res = new int[m][p];
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < p; col++) {
                long tmp = 0;
                for (int i = 0; i < n; i++) {
                    tmp = (tmp + (long) a[row][i] * b[i][col] % 1000000007) % 1000000007;
                }
                res[row][col] = (int) tmp;
            }
        }
        return res;
    }

    private static class Reader {
        BufferedReader in;
        StringTokenizer tokenizer;

        public Reader(InputStream inputStream) {
            in = new BufferedReader(new InputStreamReader(inputStream));
        }

        private String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
