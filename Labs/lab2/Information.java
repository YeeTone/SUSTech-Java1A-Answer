package labs.lab2;

import java.util.Scanner;

public class Information {
    public static void main(String[] args) {
        String name;
        int age;
        float weight;
        char grade;
        // Creating object of Scanner class
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = input.next();
        System.out.print("Enter your age: ");
        age = input.nextInt();
        System.out.print("Enter your weight in KG: ");
        weight = input.nextFloat();
        System.out.print("Enter your highest grade in last semester: ");
        grade = input.next().charAt(0);
        System.out.printf("You are %s.\nYou are %d years old.\n", name, age);
        System.out.printf("You weigh %.1f KG.\nThe highest grade you got is %c\n", weight, grade);
    }
}
