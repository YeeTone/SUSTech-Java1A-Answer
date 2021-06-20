import java.util.Scanner;

public class A2Q2 {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		boolean[][] walls = new boolean[n+1][m+1];
		for(int i = 1; i <= n; ++i){
			String str = in.next();
			for(int j = 1; j <= m; ++j){
				walls[i][j] = str.charAt(j-1) == 'W';
			}
		}
		
		int [] row = new int[2002];
		int [] col = new int[2002];
		int k = 1, x = 1, y = 1;
		char flag = '\0';
		while(true){
			row[k] = x;
			col[k] = y;
			++k;
			if(flag != 'D' && x != 1 && !walls[x-1][y]
				&& (x != 2 && !walls[x-2][y] || y != 1 && !walls[x-1][y-1] || y != m && !walls[x-1][y+1])){
				--x;
				flag = 'U';
				continue;
			}
			if(flag != 'U' && x != n && !walls[x+1][y]
				&& (x != n - 1 && !walls[x+2][y] || y != 1 && !walls[x+1][y-1] || y != m && !walls[x+1][y+1])){
				++x;
				flag = 'D';
				continue;
			}
			if(flag != 'R' && y != 1 && !walls[x][y-1]
				&& (y != 2 && !walls[x][y-2] || x != 1 && !walls[x-1][y-1] || x != n && !walls[x+1][y-1])){
				--y;
				flag = 'L';
				continue;
			}
			if(flag != 'L' && y != m && !walls[x][y+1]
				&& (y != m - 1 && !walls[x][y+2] || x != 1 && !walls[x-1][y+1] || x != n && !walls[x+1][y+1])){
				++y;
				flag = 'R';
				continue;
			}
			break;
		}
		
		if(x == n && y == m){
			System.out.println("Yes");
			for(int i = 1; i < k; ++i){
				System.out.printf("(%d,%d)\n", row[i], col[i]);
			}
		}else if(x + 1 == n && y == m || x == n && y + 1 == m){
			System.out.println("Yes");
			for(int i = 1; i < k; ++i){
				System.out.printf("(%d,%d)\n", row[i], col[i]);
			}
			System.out.printf("(%d,%d)\n", n, m);
		}else{
			System.out.println("No");
		}
		
	}
}