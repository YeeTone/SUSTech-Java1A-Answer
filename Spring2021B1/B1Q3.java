package Spring2021B1;

import java.util.Scanner;

public class B1Q3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        int x =(int)(Math.sqrt((t+1)*1.0/2));
        int[][]matrix=new int[2* x -1][2* x -1];

        for (int i = 0; i < x; i++) {
            for (int j = i; j < 2*x-i-1; j++) {
                matrix[i][j]='*';
                matrix[2*x-i-2][j]='*';
            }
        }

        for (int i = 0; i < 2*x-1; i++) {
            for (int j = 0; j < 2*x-1; j++) {
                if(matrix[i][j]!='*'){
                    System.out.print(" ");
                }else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }

        int rest=t-2*x*x+1;
        System.out.println(rest);


    }
}
