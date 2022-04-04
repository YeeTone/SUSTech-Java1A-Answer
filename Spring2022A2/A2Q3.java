package Spring2022A2;

import java.util.Arrays;
import java.util.Scanner;

public class A2Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] sis = new int[n];
        int[] dis = new int[n];

        for (int i = 0; i < n; i++) {
            sis[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            dis[i] = sc.nextInt();
        }

        Arrays.sort(sis);
        Arrays.sort(dis);

        int cost = 0;
        for (int i = 0; i < n; i++) {
            cost = Math.max(cost, Math.abs(sis[i] - dis[i]));
        }

        System.out.println(cost);

        sc.close();
    }
}
