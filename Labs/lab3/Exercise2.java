package labs.lab3;

import java.util.Random;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Random random = new Random();
        int magicNum = random.nextInt(10);
        int inputNum;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input an Integer in {0,1,2,...,9}:");
        inputNum = sc.nextInt();
        while(inputNum != magicNum) {// to finish it
            if (inputNum > magicNum)// to finish it
                System.out.println("Too big!Please try again:");
            else
                System.out.println("Too small!Please try again:");
            inputNum = sc.nextInt(); //to finish it
        }
        System.out.println("Congratulations!");
        sc.close();
    }
}
