import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type a string:");
        String s = sc.next();
        if(s.isBlank()){
            System.out.println("Empty string, exit...");
        }

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String singleString = s.substring(i, i + 1);
            if(singleString.isBlank()){
                continue;
            }

            if(b.indexOf(singleString) == -1){
                b.append(singleString);
            }
        }

        System.out.printf("After removing repeating chars and spaces: %s%n", b);
        sc.close();
    }
}
