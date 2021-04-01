package Spring2021A2;

import java.util.Scanner;

public class A2Q3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        StringBuilder str1=new StringBuilder(sc.next()).reverse();
        StringBuilder str2=new StringBuilder(sc.next()).reverse();

        int maxLength=Math.max(str1.length(),str2.length());

        while (str1.length()<maxLength){
            str1.append(0);
        }
        while (str2.length()<maxLength){
            str2.append(0);
        }

        int[]numbers1=new int[maxLength];
        int[]numbers2=new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            numbers1[i]=str1.charAt(i)-'0';
            numbers2[i]=str2.charAt(i)-'0';
        }

        int[]finalResult=new int[2*maxLength];
        int carry=0;
        for (int i = 0; i < maxLength; i++) {
            int rest = (numbers1[i] + numbers2[i]+carry) % 2;
            carry = (numbers1[i] + numbers2[i]+carry) / 2;
            finalResult[i] = rest;
        }
        finalResult[maxLength]=carry;

        int start=-1;
        for (int i = 2*maxLength-1; i >= 0; i--) {
            if(finalResult[i]!=0){
                start=i;
                break;
            }
        }

        if(start==-1){
            System.out.println(0);
        }else {
            for (int i = start; i >= 0; i--) {
                System.out.print(finalResult[i]);
            }
        }
    }
}
