package A1;

import java.io.*;
import java.util.Scanner;

public class A1Q2 {
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt(),b=sc.nextInt();
        boolean end=false;
        while (!end){
            char c=sc.next().charAt(0);
            switch (c){
                case 'a':{
                    a--;
                    break;
                }
                case 'w':{
                    b++;
                    break;
                }
                case 's':{
                    b--;
                    break;
                }
                case 'd':{
                    a++;
                    break;
                }
                default:{
                    end=true;
                    break;
                }
            }
        }

        System.out.println(a+" "+b);
        sc.close();
    }
}
