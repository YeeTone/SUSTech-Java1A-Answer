package labs.lab4;

import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Please input a number to print the Multiplication Table [0 to terminate]:");
            int num = sc.nextInt();
            if(num == 0){
                break;
            }

            if(num < 0 || num > 9){
                System.out.println("Please input a number between [1,9]");
                continue;
            }

            for (int i = 1; i <= num; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.printf("%d * %d = %d ", j, i, i*j);
                }
                System.out.println();
            }
        }
        sc.close();
    }
}
