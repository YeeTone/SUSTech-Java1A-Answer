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
public class A1Q2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int count = 0;
		int temp = 0;
		while(temp != 5){
			if(input.nextInt() >= 4){
			count++;
			}
			temp++;
		}
		System.out.println(count);
	
	}
}
