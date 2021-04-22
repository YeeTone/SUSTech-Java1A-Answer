package minesweeper;

/**
 * 此类是静态类，用于控制游戏总进程。
 * 它拥有玩家、雷区、计分板对象，以及各种操作游戏进程的方法。
 *
 * 根据喜好，其实你也可以选择将游戏窗口JFrame写在这里，
 * 或者直接让GameController继承JFrame，兼顾游戏主窗口的功能。
 *
 * emmm，如果静态的Controller无法满足需求，就自己改成非静态的吧~
 */
public class GameController {

    static Player p1;
    static Player p2;

    static Player onTurn;

    static MineField field;
    static ScoreBoard scoreBoard;

    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     * @param p1 玩家1
     * @param p2 玩家2
     * @param xSize 雷区宽度
     * @param ySize 雷区高度
     * @param mineCount 雷的数量
     */
    public static void init(Player p1, Player p2, int xSize, int ySize, int mineCount){
        GameController.p1 = p1;
        GameController.p2 = p2;
        onTurn=p1;
        field = new MineField(xSize,ySize,mineCount);
        scoreBoard = new ScoreBoard(p1,p2);

        //TODO: 在初始化游戏的时候，还需要做什么？


    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     *
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public static void nextTurn(){
        if(onTurn==p1){
            onTurn=p2;
        }else if(onTurn==p2){
            onTurn=p1;
        }
        System.out.println("Now it is "+onTurn.getUserName()+"'s turn.");
        scoreBoard.update();


        //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)

    }


    /**
     * 获取正在进行当前回合的玩家。
     * @return 正在进行当前回合的玩家
     */
    public static Player getOnTurnPlayer(){
        return onTurn;
    }


    public static Player getP1(){
        return p1;
    }
    public static Player getP2(){
        return p2;
    }
    public static MineField getMineField(){
        return field;
    }
    public static ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
