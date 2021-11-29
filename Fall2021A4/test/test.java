package Fall2021A4;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        GameSystem gs = new GameSystem();
        Player p1 = new Player("Zhangsan");
        Player p2 = new Player("Lisi");
        Game g = new Game("Game1", p1, p2);
        int[] rowIndex1 = {3, 2, 3, 4, 1, 1, 5, 5, 5, 0, 4, 5, 5, 2, 1, 4, 1, 4, 1, 2, 1, 2, 5, 0, 3, 1, 0, 2, 3, 4, 3, 2, 3, 2, 0, 6, 6, 7, 5, 6, 2, 6, 6, 6, 5, 4, 7, 6, 7, 7, 7, 7, 7, 6, 0, 0, 7, 1, 0, 0};
        int[] columnIndex1 = {2, 4, 5, 2, 3, 4, 4, 3, 2, 2, 5, 5, 6, 6, 7, 6, 5, 1, 2, 1, 0, 3, 1, 3, 1, 1, 1, 0, 6, 7, 0, 2, 7, 7, 4, 7, 6, 6, 7, 4, 5, 5, 3, 0, 0, 0, 4, 1, 1, 2, 7, 5, 0, 2, 0, 5, 3, 6, 7, 6};
        int[] color1 = {-1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1};
        for (int i = 0; i < 60; i++) {
            Step step = new Step(rowIndex1[i], columnIndex1[i], color1[i]);
            g.addStep(step);
        }
        // winner is -1
        int[][] board1 = {{-1, -1, -1, -1, -1, 1, 1, -1},
                {-1, -1, 1, -1, -1, 1, 1, -1},
                {-1, -1, -1, -1, -1, -1, 1, -1},
                {-1, -1, -1, -1, -1, 1, 1, -1},
                {-1, -1, 1, -1, -1, 1, 1, -1},
                {-1, -1, 1, -1, -1, -1, 1, -1},
                {-1, -1, -1, -1, -1, 1, 1, -1},
                {-1, -1, -1, -1, -1, 1, 1, -1}};
        g.setBoard(board1);

        gs.addPlayer(p1);
        gs.addPlayer(p2);
        gs.addGame(g);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(gs.getGameList().get(0));
    }
}
