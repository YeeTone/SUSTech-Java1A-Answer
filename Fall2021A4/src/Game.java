package Fall2021A4;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private int gid;
    private String name;

    private static int gameCnt = 1;

    private Player whitePlayer;
    private Player blackPlayer;

    private ArrayList<Step> stepList;

    private int[][] board;

    public Game(String name, Player whitePlayer, Player blackPlayer) {
        this.name = name;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.stepList = new ArrayList<>();
        this.board = new int[8][8];
        board[3][3]= board[4][4] = 1;
        board[3][4]= board[4][3] = -1;
        this.gid = gameCnt;
        gameCnt++;
    }

    public int getGid() {
        return gid;
    }

    public String getName() {
        return name;
    }

    public static int getGameCnt() {
        return gameCnt;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public ArrayList<Step> getStepList() {
        return stepList;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkStep(int sid){
        for (Step s:this.stepList){
            if(s.getSid()==sid){
                return true;
            }
        }

        return false;
    }

    public boolean addStep(Step step){
        if(checkStep(step.getSid())){
            return false;
        }

        this.stepList.add(step);
        return true;
    }

    public void setBoard(int[][] board) {
        this.board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, 8);
        }
    }

    @Override
    public String toString() {
        return String.format("Game: %s, gid: %d," +
                        "whitePlayerId: %d, blackPlayerId: %d, stepList: %s, board: %s",
                this.name, this.gid, this.whitePlayer.getPid(), this.blackPlayer.getPid(),
                this.stepList.toString(),Arrays.deepToString(this.board));
    }

    public Player getWinner(){
        int black = 0, white = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == 1){
                    white++;
                }else if(board[i][j] == -1){
                    black++;
                }
            }
        }

        if(black > white){
            return blackPlayer;
        }else if(white > black){
            return whitePlayer;
        }else {
            return null;
        }
    }

    public boolean isParticipate(int pid){
        return whitePlayer.getPid() == pid || blackPlayer.getPid() == pid;
    }
}
