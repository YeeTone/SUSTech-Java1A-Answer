package Spring2022A2;

import java.util.Scanner;

public class A2Q1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        double[] ais = new double[n];
        for (int i = 0; i < n; i++) {
            ais[i] = sc.nextDouble();
        }

        double l = sc.nextDouble(), r = sc.nextDouble();

        double leftValue = 0, rightValue = 0;

        for (int i = 0; i < n; i++) {
            leftValue += ais[i] * Math.pow(l, i + 1) / (i + 1);
            rightValue += ais[i] * Math.pow(r, i + 1) / (i + 1);
        }

        System.out.print(rightValue - leftValue);

        sc.close();
    }
}
