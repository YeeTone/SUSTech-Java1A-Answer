package Fall2020A1;

import java.util.Scanner;

public class A1Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double n = sc.nextDouble();
        int m = sc.nextInt();

        double sum = 0;
        for (int i = 0; i < m; i++) {
            sum += sc.nextDouble();
        }

        if(n>=sum){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
