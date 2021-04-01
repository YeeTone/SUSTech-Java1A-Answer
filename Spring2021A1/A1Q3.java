package Spring2021A1;

import java.util.Scanner;

public class A1Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int h1 = sc.nextInt(), m1 = sc.nextInt(), s1 = sc.nextInt();
        int h2 = sc.nextInt(), m2 = sc.nextInt(), s2 = sc.nextInt();

        int allSecondsDiff = (h2 - h1) * 3600 + (m2 - m1) * 60 + (s2 - s1);

        int minutes = allSecondsDiff / 60;
        int seconds = allSecondsDiff - minutes * 60;

        if(allSecondsDiff==0){
            System.out.println("0s");
        }else if(minutes==0){
            System.out.println(seconds+"s");
        }else if(seconds==0){
            System.out.println(minutes+"m");
        }else {
            System.out.println(minutes+"m"+seconds+"s");
        }
        sc.close();
    }
}
