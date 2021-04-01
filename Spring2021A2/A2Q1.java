package Spring2021A2;

import java.util.Scanner;

public class A2Q1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();

        int large=Math.max(m,n);
        int small=Math.min(m,n);

        while (large%small!=0){
            int temp=large;
            large=small;
            small=temp%small;
        }
        int gcd=small;

        n/=gcd;
        m/=gcd;

        System.out.println(n+" "+m);
        sc.close();
    }
}
