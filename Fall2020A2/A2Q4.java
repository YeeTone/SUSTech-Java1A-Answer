package Fall2020A2;

import java.util.Scanner;

public class A2Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = sc.nextLine();
        if(line.charAt(0)=='-'){
            line = "0"+line;
        }
        line = line+"+0";

        StringBuilder b1 = new StringBuilder();
        StringBuilder b2 = new StringBuilder();
        char operator = '\0';

        for (int i = 0; i < line.length(); i++) {
            if(Character.isDigit(line.charAt(i))){
                if(operator=='\0'){
                    b1.append(line.charAt(i));
                }else {
                    b2.append(line.charAt(i));
                }
            }
            else {
                if(operator=='\0'){
                    operator = line.charAt(i);
                }else {
                    long number1 = Long.parseLong(b1.toString());
                    long number2 = Long.parseLong(b2.toString());
                    long result = 0;

                    switch (operator){
                        case '+':{
                            result = number1+ number2;
                            break;
                        }
                        case '-':{
                            result = number1 - number2;
                            break;
                        }
                        case '*':{
                            result = number1 * number2;
                            break;
                        }
                        case '/':{
                            result = Math.floorDiv(number1,number2);
                            break;
                        }
                    }

                    operator = line.charAt(i);
                    b1.setLength(0);
                    b2.setLength(0);
                    b1.append(result);
                }
            }
        }

        System.out.println(b1);

        sc.close();
    }
}
