import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the width of a rectangle: ");
        double width = sc.nextDouble();
        System.out.print("Enter the height of a rectangle: ");
        double height = sc.nextDouble();

        double area = width * height;
        double perimeter = (width + height) * 2;

        System.out.printf("The area is %.2f%n", area);
        System.out.printf("The perimeter is %.2f%n", perimeter);
        sc.close();
    }
}
