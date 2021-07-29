package Fall2020A1;

import java.util.Scanner;

public class A1Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        while (sc.hasNextInt()){
            int number = sc.nextInt();
            if(number>=4){
                count+=1;
            }
        }
        System.out.println(count);

        sc.close();
    }
}
