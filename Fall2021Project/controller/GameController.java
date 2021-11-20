package Fall2021Project.controller;

import Fall2021Project.model.ChessPiece;
import Fall2021Project.util.GameUtil;
import Fall2021Project.view.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GameController {
    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private int blackScore;
    private int whiteScore;

    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;
    }

    public void swapPlayer() {
        countScore();
        if(GameUtil.isPlayable(gamePanel.getChessGrids(),(currentPlayer == ChessPiece.BLACK) ? Color.WHITE : Color.BLACK)){
            currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        }
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public void countScore() {
        int[] bw = this.gamePanel.getBW();
        this.blackScore = bw[0];
        this.whiteScore = bw[1];
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public Color whoWin() {
        countScore();
        if (this.blackScore > this.whiteScore) {
            return Color.BLACK;
        } else if (this.blackScore < this.whiteScore) {
            return Color.WHITE;
        } else {
            return null;
        }
    }

    public void setCurrentPlayer(ChessPiece currentPlayer) {
        this.currentPlayer = currentPlayer;
        statusPanel.repaint();
    }

    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }
}
