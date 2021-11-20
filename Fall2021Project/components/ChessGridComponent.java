package Fall2021Project.components;

import Fall2021Project.model.ChessPiece;
import Fall2021Project.util.GameSaver;
import Fall2021Project.util.GameUtil;
import Fall2021Project.model.Step;
import Fall2021Project.view.GameFrame;

import javax.swing.*;
import java.awt.*;

public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(255, 150, 50);

    private static boolean cheatingMode = false;

    private ChessPiece chessPiece;
    private final int row;
    private final int col;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);

        this.row = row;
        this.col = col;

    }

    public static void setCheatingMode() {
        cheatingMode = !cheatingMode;
    }

    @Override
    public void onMouseClicked() {
        //todo: complete mouse click method

        if (GameFrame.controller.canClick(row, col) || cheatingMode) {
            if (this.chessPiece == null) {
                this.chessPiece = GameFrame.controller.getCurrentPlayer();
                Step s = new Step(cheatingMode, row, col, chessPiece.getColor());
                GameSaver.recordStep(s);

                GameUtil.flip(GameFrame.controller.getGamePanel().getChessGrids(), row, col, chessPiece.getColor());
                if (!GameUtil.isTerminate(GameFrame.controller.getGamePanel().getChessGrids())) {
                    GameFrame.controller.swapPlayer();
                } else {
                    Color winner = GameFrame.controller.whoWin();
                    String winnerInfo = getWinnerInformation(winner);
                    JOptionPane.showMessageDialog(this,winnerInfo,"Game is terminated!",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            repaint();
        }
    }
    private String getWinnerInformation(Color winner){
        if(winner == null){
            return "Draw!";
        }else {
            if(winner == Color.BLACK){
                return "BLACK is the winner!";
            }else {
                return "WHITE is the winner!";
            }
        }
    }


    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {
        g.clearRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece != null) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
    }

    @Override
    public String toString() {
        if (chessPiece == null) {
            return "0";
        } else if (chessPiece == ChessPiece.WHITE) {
            return "1";
        } else {
            return "-1";
        }
    }
}
