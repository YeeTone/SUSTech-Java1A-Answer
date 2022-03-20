package Spring2022A1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A1Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sid = sc.next();
        double temp = sc.nextDouble();
        String testResult = sc.next();

        boolean sidValid = true, tempValid, testValid;

        if(sid.length() != 8 || sid.charAt(0) == '0'){
            sidValid = false;
        }else {
            for (char c: sid.toCharArray()){
                if(!Character.isDigit(c)){
                    sidValid = false;
                    break;
                }
            }
        }

        tempValid = (35.0 < temp && temp < 45.0);
        testValid = testResult.length() == 1 &&(testResult.charAt(0) == 'Y' || testResult.charAt(0) == 'N');

        if(sidValid && tempValid && testValid){
            System.out.println("Submit successfully");
        }else {
            System.out.print("Error in ");
            List<String> errorMessages = new ArrayList<>();

            if(!sidValid){
                errorMessages.add("Student ID");
            }

            if(!tempValid){
                errorMessages.add("Temperature");
            }

            if(!testValid){
                errorMessages.add("Nucleic Acid PCR test");
            }

            System.out.println(String.join(" and ", errorMessages));
            errorMessages.clear();
        }

        sc.close();
    }
}
