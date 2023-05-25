package labs.lab4;

import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double creditSum = 0, gpaSum = 0;
        while (true){
            int credit = sc.nextInt();
            if(credit == -1){
                break;
            }

            int grade = sc.nextInt();
            double gpa = 0;
            switch (grade / 10){
                case 10, 9 -> gpa = 4.0;
                case 8 -> gpa = 3.0;
                case 7 -> gpa = 2.0;
                case 6 -> gpa = 1.0;
                default -> gpa = 1.0;
            }

            gpaSum += gpa * credit;
            creditSum += credit;
        }

        System.out.printf("final gpa is %.1f%n", gpaSum / creditSum);
        sc.close();
    }
}
