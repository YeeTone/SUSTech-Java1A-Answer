import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the length of myList1:");
        int n = sc.nextInt();
        double[] myList1 = new double[n];
        System.out.printf("Enter %d values:", n);
        for (int i = 0; i < n; i++) {
            myList1[i] = sc.nextDouble();
        }

        double[] myList2 = new double[n];
        for (int i = 0; i < n; i++) {
            myList2[(i + 1) % n] = myList1[i];
        }

        System.out.print("myList1:");
        for (int i = 0; i < n; i++) {
            System.out.print(myList1[i] + " ");
        }
        System.out.println();

        System.out.print("myList2:");
        for (int i = 0; i < n; i++) {
            System.out.print(myList2[i]+" ");
        }

        sc.close();
    }
}
