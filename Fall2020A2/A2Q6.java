package Fall2020A2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A2Q6 {
    public static void main(String[] args) {
        Reader reader = new Reader(System.in);

        int n = reader.nextInt();
        int k = reader.nextInt();
        long mod = 1000000007;

        long[][] result = null;

        for (int i = 0; i < n; i++) {
            int mi = reader.nextInt();
            int ni = reader.nextInt();

            long[][] matrix = new long[mi][ni];
            for (int j = 0; j < mi; j++) {
                for (int r = 0; r < ni; r++) {
                    matrix[j][r] = reader.nextInt();
                }
            }

            if(result == null){
                result = matrix;
            }else {
                result = multiply(result,matrix,mod);
            }
        }
        if(result==null){
            return;
        }

        result=fastPower(result,k,mod);

        for (long[] column : result) {
            for (long value : column) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
    private static long[][] multiply(long[][] result, long[][] matrix,long mod){
        long[][] newResult = new long[result.length][matrix[0].length];

        for (int j = 0; j < result.length; j++) {
            for (int l = 0; l < result[0].length; l++) {
                for (int m = 0; m < matrix[0].length; m++) {
                    long number = ((result[j][l]%mod) * (matrix[l][m]%mod))%mod;
                    newResult[j][m] %= mod;
                    newResult[j][m] += number;
                    newResult[j][m] %= mod;
                }
            }
        }

        return newResult;
    }

    private static long[][] unit(int s){
        long[][] unitMatrix = new long[s][s];
        for (int i = 0; i < s; i++) {
            unitMatrix[i][i]=1;
        }

        return unitMatrix;
    }

    private static long[][] fastPower(long[][] result, long pow,long mod){
        long[][] temp = result;
        long[][] answer = unit(result.length);

        while (pow!=0){
            if((pow&1)!=0){
                answer = multiply(answer,temp,mod);
            }

            temp = multiply(temp,temp,mod);

            pow >>= 1;
        }

        return answer;
    }

    private static class Reader{
        BufferedReader in;
        StringTokenizer tokenizer;

        public Reader(InputStream inputStream){
            in = new BufferedReader(new InputStreamReader(inputStream));
        }

        private String next(){
            while (tokenizer == null || !tokenizer.hasMoreTokens()){
                try{
                    tokenizer = new StringTokenizer(in.readLine());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            return tokenizer.nextToken();
        }

        public int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
