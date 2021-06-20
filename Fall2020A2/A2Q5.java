import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.*;
public class A2Q5 {

    public static void main(String[] args) {
        Reader in = new Reader(System.in);
        int n = in.nextInt(), m = in.nextInt(), q = in.nextInt();
        int[] stdSet = new int[m];
        long luck = 0;
        for (int i = 0; i < m; i++) {
            stdSet[i] = in.nextInt();
            luck += stdSet[i];
        }
        luck %= q;

        for (int i = 0; i < n; i++) {
            long sum = 0;
            int res = 0;
            for (int j = 0; j < m; j++) {
                int num = in.nextInt();
                res += getScore(stdSet[j], num);
                sum += num;
            }
            res += getScore(sum % q, luck);
            System.out.println(res);
        }
    }

    private static int getScore(long a, long b) {
        if (a == b) {
            return 2;
        } else if (Math.abs(a - b) < 3) {
            return 1;
        }
        return 0;
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

