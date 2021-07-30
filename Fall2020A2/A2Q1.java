package Fall2020A2;

import java.util.Scanner;

public class A2Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextInt();
        double b = sc.nextInt();
        double delta = 0.0001;

        int degree = sc.nextInt();
        double[] coefficients = new double[degree + 1];
        for (int i = 0; i <= degree; i++) {
            coefficients[i] = sc.nextDouble();
        }

        double sum = 0;

        for (double x = a; x < b; x += delta) {
            double f = 0;

            double xn = 1;
            for (int n = degree; n >= 0; n--, xn *= x) {
                f += coefficients[n] * xn;
            }

            sum += f;
        }

        sum *= delta;

        System.out.printf("%.1f",sum);
        System.out.println();

        sc.close();
    }
}
