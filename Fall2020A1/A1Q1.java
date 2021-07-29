package Fall2020A1;

import java.util.Scanner;

public class A1Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum+=sc.nextInt();
        }
        System.out.println(sum);

        sc.close();
    }
}
