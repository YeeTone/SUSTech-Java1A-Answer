import java.util.Scanner;

public class A2Q1 {
	public static void main(String[] args){
		final double d = 0.0001;
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int n = in.nextInt();
		double [] c = new double[n + 1];
		for(int i = 0; i < n + 1; ++i){
			c[i] = in.nextDouble();
		}
		double ans = 0;
		for(double x = a; x < b; x += d){		//calculate f(x)
			for(int i = 0; i < n + 1; ++i){     //calculate each term of f(x)
				double xp = 1;
				int p = n - i;
				while(p-- > 0){
					xp *= x;
				}
				ans += c[i]*xp;
			}
		}
		System.out.printf("%.1f\n", ans*d);
	}
}

