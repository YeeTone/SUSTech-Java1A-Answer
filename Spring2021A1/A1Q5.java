package Spring2021A1;

import java.util.Scanner;

public class A1Q5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String level=sc.next();

        double sum=0;
        for (int i = 0; i < n; i++) {
            sum+=sc.nextDouble()*sc.nextInt();
        }

        switch (level){
            case "Diamond":{
                sum*=0.7;
                break;
            }
            case "Gold":{
                sum*=0.8;
                break;
            }
            case "Silver":{
                sum*=0.9;
                break;
            }
            default:{

            }
        }

        System.out.printf("%.1f",sum);
        sc.close();
    }

}
