package Fall2021A1;

import java.util.Scanner;

public class A1Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += sc.nextInt();
        }

        System.out.println(sum - M);

        sc.close();
    }
}
