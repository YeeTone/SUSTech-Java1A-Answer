/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
/**
 *
 * @author Bill
 */
public class A1Q4 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double money = input.nextDouble();
		int count = input.nextInt();
		double temp = 0;
		
		while(count != 0){
			temp += input.nextDouble();
			count--;
		}
		if(money >= temp){
			System.out.println("Yes");
		}
		else{
			System.out.println("No");
		}
		
	}
}
