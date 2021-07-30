package Fall2020A2;

import java.util.Scanner;

public class A2Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        long q = sc.nextLong();

        long sum = 0;
        long[] standard = new long[m];
        for (int i = 0; i < m; i++) {
            standard[i]= sc.nextLong();
            sum += standard[i];
            sum %= q;
        }

        long luckyNumber = sum % q;

        for (int i = 0; i < n; i++) {
            long myScore = 0;
            long mySum = 0;

            long[] myNumber = new long[m];
            for (int j = 0; j < m; j++) {
                myNumber[j] = sc.nextLong();
                mySum += myNumber[j];
                mySum %= q;

                if(myNumber[j]==standard[j]){
                    myScore+=2;
                }else if(Math.abs(myNumber[j]-standard[j])<3){
                    myScore++;
                }
            }

            long myLucky = mySum % q;

            if(myLucky==luckyNumber){
                myScore+=2;
            }else if(Math.abs(myLucky-luckyNumber)<3){
                myScore++;
            }

            System.out.println(myScore);
        }

        sc.close();
    }
}
