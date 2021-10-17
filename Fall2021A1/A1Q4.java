package Fall2021A1;

import java.util.Scanner;

public class A1Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int max = -1;
        int min = 10001;

        for (int i = 0; i < N; i++) {
            int number = sc.nextInt();
            max = Math.max(number, max);
            min = Math.min(number, min);
        }

        boolean isMaxPrime = true;
        boolean isMinPrime = true;

        if ((max != 2 && max % 2 == 0) || (max == 1)) {
            isMaxPrime = false;
        } else {
            for (int i = 3; i < max; i += 2) {
                if (max % i == 0) {
                    isMaxPrime = false;
                    break;
                }
            }
        }

        if ((min != 2 && min % 2 == 0) || (min == 1)) {
            isMinPrime = false;
        } else {
            for (int i = 3; i < min; i += 2) {
                if (min % i == 0) {
                    isMinPrime = false;
                    break;
                }
            }
        }

        int count = 0;

        if (isMaxPrime) {
            count += 1;
        }
        if (isMinPrime) {
            count += 1;
        }

        System.out.println(min);
        System.out.println(max);
        System.out.println(count);

        sc.close();
    }
}
