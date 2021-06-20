/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author Bill
 */
public class A1Q7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int circle = input.nextInt();
        int year = input.nextInt();
        int month = input.nextInt();
        int days = input.nextInt();

        int total = (year-1)*365 + (year-1)/4-(year-1)/100+(year-1)/400;
        switch (month) {
            case 1:
                break;
            case 2:
                total += 31;
                break;
            case 3:
                total += 60;
                break;
            case 4:
                total += 91;
                break;
            case 5:
                total += 121;
                break;
            case 6 :
                total += 152;
                break;
            case 7 :
                total += 182;
                break;
            case 8 :
                total += 213;
                break;
            case 9 :
                total += 244;
                break;
            case 10 :
                total += 274;
                break;
            case 11 :
                total += 305;
                break;
            case 12 :
                total += 335;
        }
        //闰年
        if(month>=3 && (year %4 != 0 || (year %100 == 0 && year % 400 != 0))){
            total--;
        }
        total+=days;
        if(total % circle == 0){
            System.out.println(circle);
        }else{
            System.out.println(total % circle);
        }
    }
}