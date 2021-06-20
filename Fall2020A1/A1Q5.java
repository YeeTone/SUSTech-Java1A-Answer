/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 * @author Bill
 */
public class A1Q5 {
    public static void main(String[] args) {
        int flag = 7;
        int temp = 1;
        int out = 0;
        Scanner input = new Scanner(System.in);
        int in = input.nextInt();

        while (in != 0) {
            out += (temp) * ((in % flag) / (flag / 7));
            in -= in % flag;
            flag *= 7;
            temp *= 10;
        }
        for (int pow = 7; pow >= 0; --pow) {
            if (out < Math.pow(10, pow)) {
                System.out.print('0');
            } else {
                System.out.println(out);
                break;
            }
        }
    }
}
