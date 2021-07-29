package Fall2020A1;

import java.util.Scanner;

public class A1Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hour = sc.nextInt();
        int minute = sc.nextInt();
        int totalTime = hour*60 + minute;
        int expectedTime = 13*60;

        int timeDifference = expectedTime - totalTime;
        if(timeDifference<0){
            timeDifference+=24*60;
        }

        int needHour = timeDifference/60;
        int needMinute = timeDifference%60;

        if(needHour!=0){
            System.out.print(needHour+" ");
        }
        System.out.println(needMinute);

        sc.close();
    }
}
