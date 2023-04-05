import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the length of array:");
        int length = sc.nextInt();
        int[] array1 = new int[length];
        int[] array2 = new int[length];

        System.out.printf("Enter the 1st integer array of size %d:", length);
        for (int i = 0; i < length; i++) {
            array1[i] = sc.nextInt();
        }

        System.out.printf("Enter the 2nd integer array of size %d:", length);
        for (int i = 0; i < length; i++) {
            array2[i] = sc.nextInt();
        }

        boolean arrayEqual = true;
        for (int i = 0; i < length; i++) {
            if(array1[i] != array2[i]){
                arrayEqual = false;
                break;
            }
        }

        System.out.printf("The two arrays are %sequal.", arrayEqual?"":"not ");
        sc.close();
    }
}
