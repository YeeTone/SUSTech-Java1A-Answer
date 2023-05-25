package labs.lab5;

import java.util.Scanner;

public class Exercise6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter how many numbers: ");
        int n = sc.nextInt();
        int[] array = new int[n];
        int sum = 0;
        System.out.printf("Enter %d numbers: ", n);
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
            sum += array[i];
        }

        double average = sum * 1.0 / n;
        System.out.printf("average=%.1f%n", average);

        long current1 = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((array[i] + array[j]) * 1.0 / 2 > average) {
                    count++;
                }
            }
        }
        long current2 = System.currentTimeMillis();

        System.out.printf("The number of pairs of integer is %d%n", count);
        System.out.printf("The running time is %.3f second%n", (current2 - current1) * 1.0 / 1000);
        sc.close();
    }
}
