package Fall2021A2;

import java.util.Arrays;
import java.util.Scanner;

public class A2Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), p = sc.nextInt();

        int[] stationPeople = new int[n];
        Arrays.fill(stationPeople, 0);

        for (int i = 0; i < p; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            for (int j = start; j < end; j++) {
                stationPeople[j] ++;
            }
        }

        int a = sc.nextInt(), b = sc.nextInt();

        int tickets = m;

        for (int i = a; i < b; i++) {
            int restPlace = m - stationPeople[i];
            tickets = Math.min(tickets, restPlace);
        }
        System.out.println(tickets);

        sc.close();
    }
}
