package labs.lab8;

import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Type a string (\"quit\" to finish):");
            String s = sc.next();
            if (s.equals("quit")) {
                break;
            }
            String reverse = new StringBuilder(s.toLowerCase()).reverse().toString();

            if (reverse.equals(s.toLowerCase())) {
                System.out.printf("%s is a palindrome%n", s);
            } else {
                System.out.printf("%s is not a palindrome%n", s);
            }
        }

        sc.close();
    }

}
