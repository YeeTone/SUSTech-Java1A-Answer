package labs.lab5;

import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] scores = new double[10];
        System.out.print("Please input 10 scores of these students:");
        double highest = -1, lowest = 101;
        for (int i = 0; i < 10; i++) {
            scores[i] = sc.nextDouble();
            if(scores[i] > highest){
                highest = scores[i];
            }else if(scores[i] < lowest){
                lowest = scores[i];
            }
        }

        double sum = 0;
        for (int i = 0; i < 10; i++) {
            if(scores[i] != lowest && scores[i] != highest){
                sum += scores[i];
            }
        }
        System.out.printf("Average score is %.2f", sum / 8);
        sc.close();
    }
}
