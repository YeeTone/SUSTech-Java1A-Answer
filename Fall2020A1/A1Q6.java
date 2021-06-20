/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 * @author Bill
 */
public class A1Q6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int count = 0;
        int days = input.nextInt();
        while (days != 0) {
            String A = '2' + input.next() + '2';
            int counta = 0;
            while (counta != A.length() - 1) {
                count += ('2' - A.charAt(counta)) ^ ('2' - A.charAt(counta + 1));
                counta++;
            }
            days--;
        }
        System.out.println(count);

    }
}
