package Fall2020A1;

import java.util.Scanner;

public class A1Q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int weekSize = sc.nextInt();
        int expectedYear = sc.nextInt();
        int expectedMonth = sc.nextInt();
        int expectedDay = sc.nextInt();

        int dayCount = 0;

        for (int year = 1; year < expectedYear; year++) {
            if(year %4!=0||(year %100==0&& year %400!=0)){
                dayCount += 365;
            }else {
                dayCount += 366;
            }
        }

        for (int month = 1; month < expectedMonth; month++) {
            switch (month){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dayCount += 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dayCount += 30;
                    break;
                case 2:
                    if(expectedYear%4 != 0||(expectedYear%100==0 && expectedYear%400!=0)){
                        dayCount += 28;
                    }else {
                        dayCount += 29;
                    }
            }
        }

        dayCount += expectedDay;

        if(dayCount%weekSize==0){
            System.out.println(weekSize);
        }else {
            System.out.println(dayCount%weekSize);
        }


        sc.close();

    }
}
