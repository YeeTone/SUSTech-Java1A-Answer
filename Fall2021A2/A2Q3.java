package Fall2021A2;

import java.io.FileInputStream;
import java.util.Scanner;

public class A2Q3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int[] ais = new int[N];

            for (int j = 0; j < N; j++) {
                ais[j] = sc.nextInt();
            }

            int sum0Pairs = 0;

            for (int j = 0; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (ais[j] + ais[k] == 0) {
                        sum0Pairs++;
                    }
                }
            }
            System.out.println(sum0Pairs);
        }
        sc.close();
    }
}
