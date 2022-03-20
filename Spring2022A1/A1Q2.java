package Spring2022A1;

import java.util.Scanner;

public class A1Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean sidValid = true, ynValid = true;

        String sid = sc.next();
        String yn = sc.next();

        if (sid.length() != 8 || sid.charAt(0) == '0') {
            sidValid = false;
        }

        for (int i = 0; i < sid.length(); i++) {
            if (!Character.isDigit(sid.charAt(i))) {
                sidValid = false;
                break;
            }
        }

        if (yn.length() != 7) {
            ynValid = false;
        }

        for (int i = 0; i < yn.length(); i++) {
            if (yn.charAt(i) != 'Y' && yn.charAt(i) != 'N') {
                ynValid = false;
                break;
            }
        }

        if (sidValid && ynValid) {
            boolean consecutiveN3 = false;
            int y = 0;

            for (int i = 0; i < yn.length(); i++) {
                if (yn.charAt(i) == 'Y') {
                    y++;
                }

                if (i + 2 < yn.length()) {
                    if (yn.charAt(i) == 'N' && yn.charAt(i + 1) == 'N' && yn.charAt(i + 2) == 'N') {
                        consecutiveN3 = true;
                    }
                }
            }

            if(consecutiveN3 || y <= 3){
                System.out.println("Has not participated in Nucleic Acid PCR tests as required!");
            }else {
                System.out.println("Good, keep it up!");
            }
        }else {
            System.out.println("Not valid");
        }

        sc.close();
    }
}
