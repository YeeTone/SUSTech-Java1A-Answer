package labs.lab4;

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the precision: ");
        double precision = sc.nextDouble();

        double pi = 0.0;
        int times = 0;
        while (true) {
            double bias = 4.0 / (2 * times + 1);
            if (times % 2 == 0) {
                pi += bias;
            } else {
                pi -= bias;
            }

            times++;
            if (bias <= precision) {
                break;
            }
        }

        System.out.printf("The estimation of Pi is %f%n", pi);
        System.out.printf("It computed %d times%n", times);
        sc.close();
    }
}
