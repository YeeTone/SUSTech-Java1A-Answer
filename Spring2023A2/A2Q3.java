package Spring2023A2;

import java.util.Scanner;

public class A2Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            int ans = 0;
            for (int j = 0; j < s.length(); j++) {
                for (int k = j + 1; k <= s.length(); k++) {
                    String sub = s.substring(j , k);
                    if(sub.equals(new StringBuilder(sub).reverse().toString())){
                        ans++;
                    }
                }
            }

            System.out.println(ans);
        }

        sc.close();
    }
}
