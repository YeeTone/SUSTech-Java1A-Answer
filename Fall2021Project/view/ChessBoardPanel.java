package Fall2021Project.view;

import Fall2021Project.util.GameReader;
import Fall2021Project.util.GameUtil;
import Fall2021Project.components.ChessGridComponent;
import Fall2021Project.model.ChessPiece;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;

    public ChessBoardPanel(int width, int height) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        repaint();
    }

    public int[] getBW() {
        int[] bw = {0, 0};

        for (ChessGridComponent[] cgcs : chessGrids) {
            for (ChessGridComponent cgc : cgcs) {
                cgc.repaint();
                if (cgc.getChessPiece() == null) {
                    continue;
                }

                if (cgc.getChessPiece().getColor() == Color.BLACK) {
                    bw[0]++;
                } else if (cgc.getChessPiece().getColor() == Color.WHITE) {
                    bw[1]++;
                }
            }
        }

        return bw;
    }

    public void setChessGrids(ChessGridComponent[][] chessGs) {
        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                chessGrids[i][j].setChessPiece(chessGs[i][j].getChessPiece());
                chessGrids[i][j].repaint();
            }
        }
    }

    /**
     * set an empty chessboard
     */
    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];

        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                if (chessGrids[i][j] != null) {
                    this.remove(chessGrids[i][j]);
                }

                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setChessPiece(null);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[3][4].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[4][4].setChessPiece(ChessPiece.BLACK);

    }

    public ChessGridComponent[][] getChessGrids() {
        return chessGrids;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        if (chessGrids[row][col].getChessPiece() != null) {
            return false;
        }
        Color color = currentPlayer.getColor();
        return GameUtil.canFlip(this.chessGrids, row, col, color);
    }
}
