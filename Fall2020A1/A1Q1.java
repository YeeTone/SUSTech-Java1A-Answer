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
public class A1Q1	 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
		Scanner input = new Scanner(System.in);
		int count = input.nextInt();
		int total = 0;
		while(count != 0){
			total+=input.nextInt();
			count--;
		}
		System.out.println(total);
	}
	
}
