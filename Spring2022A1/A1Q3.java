package Spring2022A1;

import java.util.Arrays;
import java.util.Scanner;

public class A1Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean inputValid = true;
        String[] line1 = sc.nextLine().split(" ");
        String[] line2 = sc.nextLine().split(" ");

        if (line1.length != 3 || line2.length != 3) {
            inputValid = false;
        }

        if(!inputValid){
            System.out.println("Not valid");
            sc.close();
            return;
        }


        String[] allInput = {line1[0], line1[1], line1[2], line2[0], line2[1], line2[2]};

        for(String s: allInput){
            if(s.length() > 2){
                inputValid = false;
                break;
            }

            for (char c: s.toCharArray()){
                if(!Character.isDigit(c)){
                    inputValid = false;
                    break;
                }
            }

            if(!inputValid){
                break;
            }
        }

        if (!inputValid) {
            System.out.println("Not valid");
            sc.close();
            return;
        }

        int h1 = Integer.parseInt(line1[0]), m1 = Integer.parseInt(line1[1]), s1 = Integer.parseInt(line1[2]);
        int h2 = Integer.parseInt(line2[0]), m2 = Integer.parseInt(line2[1]), s2 = Integer.parseInt(line2[2]);

        int deltaTime = (h2 * 3600 + m2 * 60 + s2) - (h1 * 3600 + m1 * 60 + s1);

        if (h1 < 0 || h1 >= 24) {
            inputValid = false;
        }

        if (m1 < 0 || m1 >= 60) {
            inputValid = false;
        }

        if (s1 < 0 || s1 >= 60) {
            inputValid = false;
        }

        if (h2 < 0 || h2 >= 24) {
            inputValid = false;
        }

        if (m2 < 0 || m2 >= 60) {
            inputValid = false;
        }

        if (s2 < 0 || s2 >= 60) {
            inputValid = false;
        }

        if (deltaTime < 0) {
            inputValid = false;
        }

        if (!inputValid) {
            System.out.println("Not valid");
            sc.close();
            return;
        }

        int deltaH = deltaTime / 3600, deltaM = deltaTime % 3600 / 60, deltaS = deltaTime % 60;

        if (deltaH != 0) {
            System.out.printf("%dh", deltaH);
        }

        if (deltaM != 0) {
            System.out.printf("%dm", deltaM);
        }

        if (deltaS != 0) {
            System.out.printf("%ds", deltaS);
        }

        if (deltaH == 0 && deltaM == 0 && deltaS == 0) {
            System.out.print("0s");
        }

        sc.close();

    }
}
