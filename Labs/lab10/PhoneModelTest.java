package labs.lab10;

import java.util.Scanner;

public class PhoneModelTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your budget: ");
        int budget = sc.nextInt();

        boolean anyoneFit = false;
        for (PhoneModel pm: PhoneModel.values()){
            if(pm.getPrice() <= budget){
                if(!anyoneFit){
                    System.out.println("---Recommended Phone List---");
                    anyoneFit = true;
                }
                System.out.printf("%-16sprice: %d%n", pm.name(), pm.getPrice());
            }
        }

        if(!anyoneFit){
            System.out.println("You do not have sufficient money.");
        }

        sc.close();
    }
}
