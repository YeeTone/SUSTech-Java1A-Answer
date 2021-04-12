package Spring2021A3;

import java.util.ArrayList;
import java.util.Scanner;

public class A3Q2 {
    private static final int[][] directions={{1,0},{0,1},{1,1},{1,-1}};
    private static boolean judgeIsOK5(char[][]chessBoard,int y,int x,int directionIndex,int n) {

        int maxCount = 0;
        int curCount=0;
        for (int dl = -4; dl <= 4; dl++) {
            int curY=y+dl*directions[directionIndex][1];
            int curX=x + dl * directions[directionIndex][0];

            if (curX >= 0 && curX < n && curY >= 0 && curY < n) {
                if (chessBoard[curY][curX] == 'O') {
                    curCount++;
                    maxCount = Math.max(maxCount, curCount);
                } else {
                    curCount = 0;
                }
            }
        }

        return maxCount>=5;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = sc.next().charAt(0);
            }
        }
        ArrayList<Point>points=new ArrayList<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if(chessBoard[y][x]=='O'||chessBoard[y][x]=='X'){
                    continue;
                }

                char origin=chessBoard[y][x];
                chessBoard[y][x]='O';
                for (int i = 0; i < directions.length; i++) {
                    if(judgeIsOK5(chessBoard,y,x,i,n)){
                        points.add(new Point(x,y));
                        break;
                    }
                }


                chessBoard[y][x]=origin;
            }
        }

        points.sort((p1, p2) -> {
            if(p1.y!=p2.y){
                return p1.y-p2.y;
            }else {
                return p1.x-p2.x;
            }
        });

        if(points.isEmpty()){
            System.out.println("No");
        }else {
            for(Point p:points){
                System.out.println(p);
            }
        }

        sc.close();
    }
    private static class Point {
        int x;
        int y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public String toString() {
            return "("+(this.x+1)+","+(this.y+1)+")";
        }

    }
}
