package labs.lab2;

import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of seconds: ");
        int seconds = sc.nextInt();

        int h = seconds / 3600;
        int m = seconds % 3600 / 60;
        int s = seconds % 60;

        System.out.printf("The equivalent time is %d hours %d minutes and %d seconds.%n", h, m, s);
        sc.close();
    }
}
