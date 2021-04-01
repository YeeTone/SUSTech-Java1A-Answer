package Spring2021A1;

import java.util.Scanner;

public class A1Q4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t= sc.nextInt();

        int sum=0,max=0;
        for (int i = 0; i < t; i++) {
            int value=sc.nextInt();
            sum+=value;
            max=Math.max(max,value);
        }

        String level;
        if((t>=10&&sum>=3000)||max>=5000){
            level="Diamond";
        }else if((t>=5&&sum>=2000)||max>=3000){
            level="Gold";
        }else if((t>=3&&sum>=1000)||max>=1500){
            level="Silver";
        }else {
            level="Ordinary";
        }

        System.out.println(level);
        sc.close();
    }
}
