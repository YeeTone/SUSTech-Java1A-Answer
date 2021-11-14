package Fall2021A3;

import java.util.Scanner;
import java.util.Stack;

public class A3Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] graph = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        Stack<Integer> exploredStack = exploreGraph(graph,m,n);

        while (true){
            System.out.print(exploredStack.pop());
            if(!exploredStack.isEmpty()){
                System.out.print(" ");
            }else {
                break;
            }
        }

        sc.close();

    }

    private static Stack<Integer> exploreGraph(int[][] graph,int m,int n){
        boolean[][] explored = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                explored[i][j] = false;
            }
        }

        Stack<Integer> exploredStack = new Stack<>();

        int x = 0;
        int y = 0;
        char direction = 'R';

        while (exploredStack.size()!=m*n){
            exploredStack.push(graph[x][y]);
            explored[x][y] = true;
            switch (direction){
                case 'R':{
                    try{
                        y++;
                        if(explored[x][y]){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        y--;
                        x++;
                        direction = 'D';
                    }
                    break;
                }

                case 'D':{
                    try{
                        x++;
                        if(explored[x][y]){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        x--;
                        y--;
                        direction = 'L';
                    }
                    break;
                }

                case 'L':{
                    try{
                        y--;
                        if(explored[x][y]){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        y++;
                        x--;
                        direction = 'U';
                    }
                    break;
                }

                case 'U':{
                    try{
                        x--;
                        if(explored[x][y]){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        x++;
                        y++;
                        direction = 'R';
                    }
                    break;
                }
            }
        }

        return exploredStack;
    }
}
