package Fall2021A1;

import java.util.Scanner;

public class A1Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();

            int maxNumber = N;

            while (N != 1) {
                if (N % 2 == 1) {
                    N = 3 * N + 1;
                } else {
                    N = N / 2;
                }

                maxNumber = Math.max(maxNumber, N);
            }

            System.out.println(maxNumber);
        }

        sc.close();
    }
}
