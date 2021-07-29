package Fall2020A1;

import java.util.Scanner;

public class A1Q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long money = sc.nextInt();
        long pow7 = 823543;

        while (pow7!=0){
            System.out.print(money/pow7);
            money%=pow7;
            pow7/=7;
        }
        System.out.println();

        sc.close();
    }
}
