package Spring2021A1;

import java.util.Scanner;

public class A1Q1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String name=sc.next();
        String idStr=sc.next();

        boolean success=true;
        for (int i = 0; i < idStr.length(); i++) {
            if(!Character.isDigit(idStr.charAt(i))){
                success=false;
            }
        }
        if(success){
            if(idStr.length()!=8){
                success=false;
            }
            if(success){
                int id=Integer.parseInt(idStr);
                if(id<11500000||id>12099999){
                    success=false;
                }
            }
        }
        if(success){
            System.out.println(name+", welcome to Baoneng City!");
        }else {
            System.out.println(idStr);
        }
        sc.close();
    }
}
