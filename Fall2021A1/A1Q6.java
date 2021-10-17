package Fall2021A1;

import java.math.BigInteger;
import java.util.Scanner;

public class A1Q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();

            /*  Solution 1:
            System.out.println(Integer.toBinaryString(n));
            */
            /*  Solution 2:
            System.out.println(Integer.toString(n,2));
            */

            /*  Solution 3:
            System.out.println(BigInteger.valueOf(n).toString(2));
            */

            //  Solution 3:
            StringBuilder b = new StringBuilder();

            while (n != 0) {
                b.append(n % 2);
                n /= 2;
            }

            b.reverse();

            System.out.println(b);
        }

        sc.close();
    }
}
