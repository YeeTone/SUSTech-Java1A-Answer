package labs.lab6;

import java.util.Scanner;

public class MyTriangleE1 {
    public static double area(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static double perimeter(double a, double b, double c) {
        return a + b + c;
    }

    public static boolean isValid(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0
                && a + b > c && a + c > b && b + c > a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input three numbers for a, b, c:");
        int a, b, c;
        while (true) {
            a = sc.nextInt();
            if (a == -1) {
                break;
            }
            b = sc.nextInt();
            c = sc.nextInt();
            if (isValid(a, b, c)) {
                System.out.printf("The area is %.3f%n", area(a, b, c));
                System.out.printf("The perimeter is %.3f%n", perimeter(a, b, c));
            } else {
                System.out.println("The input is invalid.");
            }
        }
        System.out.println("Bye~");
        sc.close();
    }
}
