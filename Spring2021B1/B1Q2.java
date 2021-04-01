package Spring2021B1;

import java.util.Scanner;

public class B1Q2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        int sum=0;
        while (sc.hasNextInt()){
            sum+=sc.nextInt();
        }
        System.out.println(sum);
    }
}
