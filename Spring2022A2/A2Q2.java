package Spring2022A2;

import java.util.Scanner;

public class A2Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int[] queensX = new int[8];
            int[] queensY = new int[8];
            for (int j = 0; j < 8; j++) {
                String q = sc.next();
                queensX[j] = q.charAt(0) - 'a';
                queensY[j] = q.charAt(1) - '1';
            }

            boolean isConflict = false;
            for (int firstQueen = 0; firstQueen < 8; firstQueen++) {
                int firstQueenX = queensX[firstQueen], firstQueenY = queensY[firstQueen];

                for (int secondQueen = firstQueen + 1; secondQueen < 8; secondQueen++) {
                    int secondQueenX = queensX[secondQueen], secondQueenY = queensY[secondQueen];

                    boolean sameRow = firstQueenX == secondQueenX;
                    boolean sameColumn = firstQueenY == secondQueenY;

                    boolean sameDiagonal = Math.abs(firstQueenX - secondQueenX) == Math.abs(firstQueenY - secondQueenY);

                    if (sameRow || sameColumn || sameDiagonal) {
                        isConflict = true;
                        break;
                    }
                }

                if (isConflict) {
                    break;
                }
            }

            if (isConflict) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
        }

        sc.close();
    }
}
