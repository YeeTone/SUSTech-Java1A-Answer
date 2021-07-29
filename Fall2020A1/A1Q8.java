package Fall2020A1;

import java.util.Scanner;

public class A1Q8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lucky = sc.nextInt();
        String numberString = sc.next();
        int[] numberCounts = new int[10];

        for (int i=0;i<numberString.length();i++){
            numberCounts[numberString.charAt(i)-'0']++;
        }

        for (int i=0;i<numberCounts[lucky];i++){
            System.out.print(lucky);
        }

        for (int i = 9; i >= 0; i--) {
            if(i==lucky){
                continue;
            }
            for (int j = 0; j < numberCounts[i]; j++) {
                System.out.print(i);
            }
        }
        System.out.println();

        sc.close();
    }
}
