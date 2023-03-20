package Spring2023A1;

import java.util.Scanner;

public class A1Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            double v1 = sc.nextDouble(), m = sc.nextDouble(),
                    q = sc.nextDouble(), U = sc.nextDouble();

            double expectedU = 0.5*m*v1*v1 / q;
            System.out.println(U >= expectedU);
        }
        sc.close();
    }
}
