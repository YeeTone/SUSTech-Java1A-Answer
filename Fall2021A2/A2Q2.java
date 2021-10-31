package Fall2021A2;

import java.util.Scanner;

public class A2Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int h = sc.nextInt(), w = sc.nextInt();
        int[][] graph = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        boolean isMatch = false;

        for (int x = 0; x < h; x++) {
            for (int y = 0; y < w; y++) {
                int currentColor = graph[x][y];
                boolean match = false;

                if(x + 2 < h){
                    if(currentColor == graph[x + 1][y] && currentColor == graph[x + 2][y]){
                        match = true;
                    }
                }

                if (y + 2 < w){
                    if(currentColor == graph[x][y + 1] && currentColor == graph[x][y + 2]){
                        match = true;
                    }
                }
                isMatch = isMatch || match;
            }
        }

        if(isMatch){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }

        sc.close();
    }
}
