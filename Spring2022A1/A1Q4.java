package Spring2022A1;

import java.util.Scanner;

public class A1Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean inputValid = true;
        String input = sc.nextLine();

        input = input.trim();

        if(input.length() != 4*7 + 4 - 1){
            inputValid = false;
        }

        String[] split = input.split(" ");

        if(split.length != 4){
            inputValid = false;
        }

        for (int i = 0; i < 4; i++) {
            if(!inputValid){
                break;
            }

            if(split[i].length() != 7){
                inputValid = false;
                break;
            }

            for (int j = 0; j < 7; j++) {
                if(split[i].charAt(j) != 'Y'  && split[i].charAt(j) !='N'){
                    inputValid = false;
                    break;
                }
            }
        }

        if(!inputValid){
            System.out.println("Not valid");
            sc.close();
            return;
        }

        int punishment = 10, score = 100;

        for (int i = 0; i < 4; i++) {

            String yn = split[i];

            int y = 0;
            boolean consecutiveN3 = false;

            for (int j = 0; j < 7; j++) {
                if(yn.charAt(j) == 'Y'){
                    y++;
                }

                if(j + 2 < yn.length()){
                    if(yn.charAt(j) =='N' && yn.charAt(j + 1) =='N' && yn.charAt(j + 2) =='N'){
                        consecutiveN3 = true;
                    }
                }
            }

            if(consecutiveN3 || y <= 3){
                score -= punishment;
                punishment += 5;
            }
        }

        System.out.println(score);

        sc.close();

    }
}
