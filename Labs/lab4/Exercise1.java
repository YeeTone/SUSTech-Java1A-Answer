import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input n: ");
        int n = sc.nextInt();
        double pi = 0.0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                pi += 4.0 / (2 * i + 1);
            } else {
                pi -= 4.0 / (2 * i + 1);
            }
        }

        System.out.printf("The estimation of Pi is %f", pi);
        sc.close();
    }
}
