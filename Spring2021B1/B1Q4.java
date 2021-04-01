package Spring2021B1;

import java.util.Scanner;

public class B1Q4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        long[][]triangle=new long[40][40];
        for (int i = 0; i < 40; i++) {
            triangle[i][0]=1;
            triangle[i][i]=1;
            for (int j = 1; j < i; j++) {
                triangle[i][j]=triangle[i-1][j]+triangle[i-1][j-1];
            }
        }

        for (int i = 0; i <= n; i++) {
            if(triangle[n][i]!=0){
                System.out.print(triangle[n][i]+" ");
            }
        }
        System.out.println();
    }
}
