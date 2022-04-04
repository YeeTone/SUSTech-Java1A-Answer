package Spring2022A2;

import java.util.Scanner;

public class A2Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(3 * n + 3);

        for (int i = 0; i <= n; i++) {
            System.out.println(i + " " + (i + 1));
            System.out.println((i + 1) + " " + i);
            System.out.println((i + 1) + " " + (i + 1));
        }

        sc.close();
    }
}
