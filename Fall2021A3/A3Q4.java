package Fall2021A3;

import java.util.Scanner;

public class A3Q4 {
    public static void main(String[] args) {
        answer();
    }

    public static void answer(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int color = sc.nextInt();
            int[][]chessboard = inputChessboard(sc);
            boolean[][] valids = calculate(color,chessboard);
            printValid(valids);
        }
        sc.close();
    }

    private static int[][] inputChessboard(Scanner sc){
        int[][] chessboard = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessboard[i][j] = sc.nextInt();
            }
        }

        return chessboard;
    }

    private static boolean[][] calculate(int color, int[][]chessboard){
        int validCount = 0;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
        boolean[][] validMap = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int[] dir:directions){
                    if(isValidIn1Direction(color,chessboard,dir,i,j)){
                        validMap[i][j] = true;
                        validCount++;
                        break;
                    }
                }
            }
        }

        if(validCount == 0){
            return null;
        }else {
            return validMap;
        }
    }

    private static boolean isValidIn1Direction(int color, int[][]chessboard, int[] direction,int i,int j){
        if(chessboard[i][j]!=0){
            return false;
        }

        int opponent = 0;

        int count = 1;
        while (true){
            int currentI = i + count*direction[0];
            int currentJ = j + count*direction[1];

            if(!(0<= currentI && currentI<8 && 0<= currentJ && currentJ < 8)){
                return false;
            }

            if(chessboard[currentI][currentJ] == 0){
                return false;
            }

            if(chessboard[currentI][currentJ] == color){
                return opponent > 0;
            }

            if(chessboard[currentI][currentJ] == color*-1){
                opponent++;
            }
            count++;
        }
    }

    private static void printValid(boolean[][] valids){
        if(valids == null){
            System.out.println(-1);
        }else {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    if(valids[j][k]){
                        System.out.printf("%3d",1);
                    }else {
                        System.out.printf("%3d",0);
                    }
                }
                System.out.println();
            }
        }
    }
}
