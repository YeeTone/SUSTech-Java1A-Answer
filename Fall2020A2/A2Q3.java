package Fall2020A2;

import java.util.Map;
import java.util.Scanner;

public class A2Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] lines = new String[n];

        long largest = -1;
        long smallest = Long.MAX_VALUE - 7654321;
        long mod = 998244353;

        for (int i = 0; i < n; i++) {
            lines[i] = sc.nextLine();

            long l = lines[i].length();
            long freq = 0, l_n =1;
            for (int j = 0; j < l; j++, l_n*=l, l_n %= mod) {
                freq += ((long) lines[i].charAt(j)%mod) * (l_n%mod);
                freq %= mod;
            }

            largest = Math.max(largest,freq);
            smallest = Math.min(smallest,freq);
        }

        long big = Math.max(largest,smallest);
        long tin = Math.min(largest,smallest);

        while (big % tin !=0){
            long temp = big;
            big = tin;
            tin = temp % tin;
        }

        long gcd = tin;

        largest /= gcd;
        smallest /= gcd;

        System.out.println(largest+" "+smallest);

        sc.close();
    }
}
