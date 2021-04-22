import minesweeper.GameController;
import minesweeper.Player;

import javax.swing.*;

/**
 * Simple MineSweeper Demo
 *     --by Yan_ice
 *
 * demo基本结构与内容介绍：
 *
 * GameController为游戏总控制器，包含雷区MineField、玩家Player、计分板ScoreBoard对象。
 * 方便起见，GameController被写成了静态的。
 * 如果静态的游戏总控制器并不能满足你们的需求，请自行修改吧~
 *
 * MineField为雷区，继承自JPanel，雷区对象包含若干个格子Grid对象。
 * Player为玩家，玩家对象具有名字、分数、失误数等属性。
 * ScoreBoard为计分板，继承自JPanel，能够显示玩家以及玩家的分数。
 *
 * Grid为雷区中的单个格子，继承自BasicButton，具有坐标、状态、内容等属性，且拥有鼠标点击事件监听器。
 * GridStatu为Grid状态的枚举。
 *
 *
 * 大部分可以补写/修改的内容，已经在代码中以 TO DO 的形式作出了注释。
 * 除了getter/setter，大家写代码时可能需要调用的方法基本都加了注释，便于大家理解。
 */

public class Main {

    public static void main(String[] args) {

        //这是一个临时的游戏启动器，可以用于理解demo，但十分不建议使用！

        GameController.init(new Player(),new Player(),10,10,10);

        JFrame fr = new JFrame();
        fr.setLayout(null);
        fr.setSize(300,400);
        GameController.getMineField().setLocation(0,0);
        fr.add(GameController.getMineField());
        GameController.getScoreBoard().setLocation(0,300);
        fr.add(GameController.getScoreBoard());
        fr.show();
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
