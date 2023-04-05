import java.util.Scanner;

public class Exercise4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the integers between 1 and 100:");
        int[] occurrenceTimes = new int[100];
        while (true) {
            int x = sc.nextInt();
            if (x == 0) {
                break;
            }

            occurrenceTimes[x - 1]++;
        }

        for (int i = 0; i < 100; i++) {
            if (occurrenceTimes[i] != 0) {
                System.out.printf("%d occurs %d %s%n",
                        i + 1, occurrenceTimes[i],
                        occurrenceTimes[i] == 1 ? "time" : "times");
            }
        }
        sc.close();
    }
}
