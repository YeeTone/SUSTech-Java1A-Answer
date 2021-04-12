package Spring2021A3;

import java.util.Scanner;

public class A3Q3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        char[][]map=new char[n][];
        for (int i = 0; i < n; i++) {
            map[i]=sc.next().toCharArray();
        }

        char[][]solution=new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j]=='*'){
                    solution[i][j]='F';
                }else {
                    int count=0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if(di==0&&dj==0){
                                continue;
                            }

                            if(i+di>=0&&i+di<n&&j+dj>=0&&j+dj<n){
                                if(map[i+di][j+dj]=='*'){
                                    count++;
                                }
                            }
                        }
                    }

                    solution[i][j]=(char)(count+'0');
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(solution[i][j]);
            }
            System.out.println();
        }
        sc.close();
    }
}
