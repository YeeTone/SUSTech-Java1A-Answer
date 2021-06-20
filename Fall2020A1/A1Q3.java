/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 * @author Bill
 */
public class A1Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int hours = input.nextInt();
        int minutes = input.nextInt();
        int total = 60 * hours + minutes;

        int hour = 0;
        int minute = 0;
        if (total > 60 * 13) {
            hour = (37 * 60 - total) / 60;
            minute = (37 * 60 - total) % 60;
        } else {
            hour = (13 * 60 - total) / 60;
            minute = (13 * 60 - total) % 60;
        }
        if (hour == 0) {
            System.out.println(minute);
        } else {
            System.out.printf("%d %d", hour, minute);
        }
    }
}
