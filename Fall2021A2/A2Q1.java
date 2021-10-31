package Fall2021A2;

import java.util.Scanner;

public class A2Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double[] ais = new double[n];
        for (int i = 0; i < n; i++) {
            ais[i] = sc.nextDouble();
        }

        double x =sc.nextDouble();

        double f_x_ = 0;

        for (int i = 0; i < n; i++) {
            f_x_ += ais[i] * i * Math.pow(x, i - 1);
        }

        System.out.printf("%.2f%n",f_x_);

        sc.close();
    }
}
