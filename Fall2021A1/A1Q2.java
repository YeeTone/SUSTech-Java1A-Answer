package Fall2021A1;

import java.util.Scanner;

public class A1Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        double sum = 0;
        int valid = 0;
        for (int i = 0; i < N; i++) {
            int number = sc.nextInt();
            if (number != 0) {
                valid += 1;
                sum += number;
            }
        }

        System.out.printf("%.2f%n", sum / valid);

        sc.close();
    }
}
