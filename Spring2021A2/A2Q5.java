package Spring2021A2;
import java.util.Scanner;

public class A2Q5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        int[]array=new int[n];
        for (int i = 0; i < n; i++) {
            array[i]= sc.nextInt();
        }

        int start=0,maxLength=1;
        int currentLength=1;
        for (int i = 1; i < n; i++) {
            if(array[i]>=array[i-1]){
                currentLength++;
            }else {
                currentLength=1;
            }
            if(currentLength>=maxLength){
                maxLength=currentLength;
                start=i-maxLength+1;
            }
        }

        /* curLength <- 0
            maxLength <- 0
            start <- 0
        for i from 1 to n:
                if(no-decreasing in array[i]):
                    curLength = ...
                else:
                    curLength = ...
                if(curLength > ...):
                    maxLength = ...
                    start = ...
        for i from 0 to maxLength:
            print(array[i+...]);

        * */

        for (int i = 0; i < maxLength; i++) {
            System.out.print(array[start+i]);
            if(i!=maxLength-1){
                System.out.print(" ");
            }
        }
        sc.close();
    }
}
