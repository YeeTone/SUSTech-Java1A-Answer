package Spring2023A1;

import java.util.Scanner;

public class A1Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        double weightedSum = 0;
        int credits = 0;
        for (int i = 0; i < T; i++) {
            int grade = sc.nextInt();
            int credit = sc.nextInt();
            double gpa;
            if(grade >= 90){
                gpa = 4.0;
            }else if(grade>=80){
                gpa = 3.0;
            }else if(grade >= 70){
                gpa = 2.0;
            }else if(grade >= 60){
                gpa = 1.0;
            }else {
                gpa = 0.0;
            }

            weightedSum += gpa*credit;
            credits += credit;
        }

        System.out.printf("%.2f", weightedSum / credits);
        sc.close();
    }
}
