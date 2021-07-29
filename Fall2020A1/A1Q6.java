package Fall2020A1;

import java.util.Scanner;

public class A1Q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int moveTime = 0;
        for (int i = 0; i < n; i++) {
            String schedule = sc.next();

            int currentPlace = 2;
            for (int j = 0; j < schedule.length(); j++) {
                if(schedule.charAt(j)-'0'!=currentPlace){
                    moveTime++;
                    if(currentPlace == 2){
                        currentPlace = 1;
                    }else {
                        currentPlace = 2;
                    }
                }
            }

            if(currentPlace!=2){
                moveTime++;
            }
        }
        System.out.println(moveTime);

        sc.close();
    }
}
