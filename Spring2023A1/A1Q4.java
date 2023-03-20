package Spring2023A1;

import java.util.Scanner;

public class A1Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int m = 1000, n = 1000;
        for (int i = 0; i < T - 1; i++) {
            int b = sc.nextInt();
            if(b == 1){
                m -= 1;
            }else {
                n -= 1;
            }
        }

        System.out.printf("%.2f", n * 1.0 / (m + n));
        sc.close();
    }
}
