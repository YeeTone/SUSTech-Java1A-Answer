package Fall2021A2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class A2Q5 {
    private static final StreamTokenizer tokenizer=new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static int nextInt() throws IOException {
        tokenizer.nextToken();
        return (int)tokenizer.nval;
    }

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        int T = nextInt();

        for (int i = 0; i < T; i++) {
            int l = nextInt(), m = nextInt(), n = nextInt();

            int[] gameTimes = new int[l];
            double sumTime = 0;
            for (int j = 0; j < l; j++) {
                int paper = nextInt(), assignment = nextInt(), project = nextInt();

                int gameTime = m - n - paper*2 - assignment *3 - project*5;

                gameTimes[j] = gameTime;
                sumTime += gameTime;
            }

            double average = sumTime / l;

            int longerCount = 0;

            for (int gt: gameTimes){
                if(gt>average){
                    longerCount ++;
                }
            }

            for (int j = 0; j < gameTimes.length; j++) {
                System.out.print(gameTimes[j]);
                if(j!= gameTimes.length-1){
                    System.out.print(" ");
                }
            }

            System.out.println();
            System.out.println(longerCount);

        }

    }
}
