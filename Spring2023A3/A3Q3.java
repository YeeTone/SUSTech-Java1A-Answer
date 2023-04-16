package Spring2023A3;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class A3Q3 {

    static {
        try{
            System.setIn(new FileInputStream(new File("./src/Spring2023A3/A3Q2/10.in")));
            System.setOut(new PrintStream(new File("./src/Spring2023A3/A3Q2/10.out")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        String s = sc.next();
        String decompressed = decompress(s);
        char[][] sm = spiralMatrix(decompressed, m, n);
        printSpiralMatrix(sm);
        sc.close();
    }

    public static String decompress(String s) {
        StringBuilder b = new StringBuilder();
        int index = 0;
        while (index < s.length() - 1) {
            char c = s.charAt(index);
            StringBuilder numberFormatBuilder = new StringBuilder();
            int i = index + 1;
            while (i < s.length()) {
                if (Character.isDigit(s.charAt(i))) {
                    numberFormatBuilder.append(s.charAt(i));
                    i++;
                } else {
                    break;
                }
            }

            index = i;
            int repeatNumber = Integer.parseInt(numberFormatBuilder.toString());
            b.append(String.valueOf(c).repeat(Math.max(0, repeatNumber)));
        }

        return b.toString();
    }

    public static final int[][] DIRECTION_ARRAY = {
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 0}
    };

    private static boolean outOfBoundary(char[][] matrix, int m, int n, int x, int y) {
        return !(0 <= x && x < m && 0 <= y && y < n) || matrix[x][y] != '\u0000';
    }

    public static char[][] spiralMatrix(String decompressed, int m, int n) {
        char[][] matrix = new char[m][n];
        int x = m - 1, y = 0;
        int directionIndex = 0;
        for (char c : decompressed.toCharArray()) {
            matrix[x][y] = c;

            int[] currentDirection = DIRECTION_ARRAY[directionIndex];
            int repeatTimes = 0;
            while (outOfBoundary(matrix, m, n,
                    x + currentDirection[0], y + currentDirection[1])) {
                directionIndex = (directionIndex + 1) % DIRECTION_ARRAY.length;
                currentDirection = DIRECTION_ARRAY[directionIndex];
                repeatTimes++;
                if (repeatTimes >= DIRECTION_ARRAY.length) {
                    break;
                }
            }

            x = x + currentDirection[0];
            y = y + currentDirection[1];
        }

        return matrix;
    }

    public static void printSpiralMatrix(char[][] matrix) {
        for (char[] cs : matrix) {
            for (char c : cs) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
