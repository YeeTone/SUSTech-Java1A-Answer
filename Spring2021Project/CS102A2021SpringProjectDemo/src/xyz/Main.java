package xyz;

import xyz.controller.GameController;
import xyz.model.Board;
import xyz.view.BoardComponent;
import xyz.view.GameFrame;
import xyz.view.ScoreBoard;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BoardComponent boardComponent = new BoardComponent(10, 10, 900, 800);
            Board board = new Board(10, 10);
            ScoreBoard scoreBoard = new ScoreBoard();
            GameController gameController = new GameController(boardComponent, board, scoreBoard);

            GameFrame gameFrame = new GameFrame();
            gameFrame.add(boardComponent);
            gameFrame.setVisible(true);
            scoreBoard.setVisible(true);
        });
    }
}
