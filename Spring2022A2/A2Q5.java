package Spring2022A2;

import java.util.Scanner;

public class A2Q5 {
    private static long gcd(long a, long b) {
        while (true) {
            if (a < b) {
                long t = a;
                a = b;
                b = t;
            }
            if (b == 0) {
                return a;
            }
            long x = a % b;
            a = b;
            b = x;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long r = sc.nextLong();

        if (r == 0) {
            System.out.println(1);
            sc.close();
            return;
        }

        long answer = 0;

        long sqrt = (long) Math.sqrt(2 * r);

        for (long d = 1; d <= sqrt; d++) {
            if (2 * r % d != 0) {
                continue;
            }

            for (long a = 1, sqrt2R2D = (long) Math.sqrt((2 * r) * 1.0 / (2 * d)); a <= sqrt2R2D; a++) {
                long b = (long) Math.sqrt(2L * r * 1.0 / d - a * a);
                if (a * a + b * b != 2 * r / d) {
                    continue;
                }

                long A = a * a, B = b * b;
                if (A == B) {
                    continue;
                }

                if (gcd(A, B) != 1) {
                    continue;
                }

                answer++;

            }

            if (d * d == 2 * r) {
                continue;
            }

            for (long a = 1, sqrtD2 = (long) Math.sqrt(d * 1.0 / 2); a <= sqrtD2; a++) {
                long b = (long) Math.sqrt(d - a * a);
                if (a * a + b * b != d) {
                    continue;
                }

                long A = a * a, B = b * b;
                if (A == B) {
                    continue;
                }

                if (gcd(A, B) != 1) {
                    continue;
                }
                answer++;
            }
        }

        System.out.println(answer * 4 + 4);
        sc.close();
    }
}
