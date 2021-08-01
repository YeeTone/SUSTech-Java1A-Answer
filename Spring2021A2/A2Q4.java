package Spring2021A2;

import java.util.Scanner;

public class A2Q4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long date1=sc.nextLong();
        long date2=sc.nextLong();

        int[] days={0,31,29,31,30,31,30,31,31,30,31,30,31};
        int answer=0;
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= days[i]; j++) {
                int d=j%10*1000+j/10*100+i%10*10+i/10;
                int sum=d*10000+i*100+j;
                if(sum<date1||sum>date2){
                    continue;
                }
                answer++;
            }
        }
        System.out.println(answer);
        sc.close();
    }
}