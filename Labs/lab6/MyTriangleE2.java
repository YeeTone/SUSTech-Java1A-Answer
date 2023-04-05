import java.util.Scanner;

public class MyTriangleE2 {

    public static double area(double bottom, double height) {
        return bottom * height / 2;
    }

    public static double area(double a, double b, int angleOfAandB) {
        return a * b * Math.sin(Math.toRadians(angleOfAandB)) / 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please input two numbers for bottom and height:");
        double bottom = sc.nextDouble(), height = sc.nextDouble();
        System.out.printf("The area is %.3f%n", area(bottom, height));

        System.out.println("Please input two numbers for a and b:");
        double a = sc.nextDouble(), b = sc.nextDouble();
        System.out.println("Please input a number in (0,180) for angle (angle is a float variable):");
        int angle = sc.nextInt();
        System.out.printf("The area is %.3f", area(a, b, angle));
    }
}
