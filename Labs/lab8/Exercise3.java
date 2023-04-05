import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("s1: ");
        String str = sc.nextLine().trim();
        System.out.print("s2: ");
        String strFind = sc.nextLine().trim();
        sc.close();
        int count = 0, fromIndex = 0;
        while ((fromIndex = str.indexOf(strFind, fromIndex)) != -1) {
            System.out.println("Found at index: " + fromIndex);
            count++;
            fromIndex++;
        }
        System.out.println("Total occurrences: " + count);
        sc.close();
    }
}
