package A2;

import java.util.Scanner;

public class A2Q2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();

        int[]array1=new int[n];
        int[]array2=new int[m];

        for (int i = 0; i < n; i++) {
            array1[i]= sc.nextInt();
        }

        for (int i = 0; i < m; i++) {
            array2[i]= sc.nextInt();
        }

        int ptr1=0;
        for(int ptr2=0;ptr2<m;ptr2++){
            if(array1[ptr1]==array2[ptr2]){
                ptr1++;
                if(ptr1>=n){
                    break;
                }
            }
        }

        if(ptr1>=n){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }

        sc.close();
    }
}
