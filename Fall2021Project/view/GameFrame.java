package Fall2021Project.view;


import Fall2021Project.components.ChessGridComponent;
import Fall2021Project.controller.GameController;
import Fall2021Project.model.ChessPiece;
import Fall2021Project.util.GameReader;
import Fall2021Project.util.GameSaver;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GameFrame extends JFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;

    private final JButton restartBtn;
    private final JButton loadGameBtn;
    private final JButton saveGameBtn;
    private final JButton cheatBtn;

    public GameFrame(int frameSize) {
        this.setTitle("2021F CS102A Project Reversi");
        this.setLayout(null);

        //获取窗口边框的长度，将这些值加到主窗口大小上，这能使窗口大小和预期相符
        Insets inset = this.getInsets();
        this.setSize(frameSize + inset.left + inset.right, frameSize + inset.top + inset.bottom);

        this.setLocationRelativeTo(null);

        restart();
        restartBtn = this.addRestart();
        loadGameBtn = this.addLoadGame();
        saveGameBtn = this.addSaveGame();
        cheatBtn = this.addCheatMode();
        GameSaver.initiate();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JButton addRestart() {
        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        restartBtn.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, (this.getHeight() + chessBoardPanel.getHeight()) / 2);
        add(restartBtn);
        restartBtn.addActionListener(e -> restart());

        return restartBtn;
    }

    private JButton addLoadGame() {
        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        loadGameBtn.setLocation(restartBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            String path = readPath();
            GameReader.readGame(path);
            loadGame();
        });

        return loadGameBtn;
    }

    private JButton addSaveGame() {
        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        saveGameBtn.setLocation(loadGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            String filePath = JOptionPane.showInputDialog(this, "input the name here");
            GameSaver.saveGame(filePath + ".game", chessBoardPanel.getChessGrids());
        });
        return saveGameBtn;
    }

    private JButton addCheatMode() {
        JButton cheatingMode = new JButton("Cheating Mode");
        cheatingMode.setSize(120, 50);
        cheatingMode.setLocation(saveGameBtn.getX() + restartBtn.getWidth() + 30, restartBtn.getY());
        add(cheatingMode);

        cheatingMode.addActionListener(e -> ChessGridComponent.setCheatingMode());

        return cheatingMode;
    }

    private void restart() {
        if (chessBoardPanel != null) {
            this.remove(chessBoardPanel);
        }
        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2,
                (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        if (statusPanel != null) {
            this.remove(statusPanel);
        }
        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        controller = new GameController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);

        this.add(chessBoardPanel);
        this.add(statusPanel);
        repaint();
    }

    private String readPath() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("."));
        fc.showOpenDialog(this);

        return fc.getSelectedFile().getAbsolutePath();
    }

    private void loadGame() {
        if (chessBoardPanel != null) {
            this.remove(chessBoardPanel);
        }
        chessBoardPanel = new ChessBoardPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.7));
        chessBoardPanel.setChessGrids(GameReader.getChessboard());
        chessBoardPanel.repaint();
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2,
                (this.getHeight() - chessBoardPanel.getHeight()) / 3);

        if (statusPanel != null) {
            this.remove(statusPanel);
        }
        statusPanel = new StatusPanel((int) (this.getWidth() * 0.8), (int) (this.getHeight() * 0.1));
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
        statusPanel.setPlayerText(GameReader.getCurrentPlayer() == Color.BLACK ? ChessPiece.BLACK.name() : ChessPiece.WHITE.name());

        controller = new GameController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);
        controller.setCurrentPlayer(GameReader.getCurrentPlayer() == Color.BLACK ? ChessPiece.BLACK : ChessPiece.WHITE);
        GameFrame.controller.countScore();

        this.add(chessBoardPanel);
        this.add(statusPanel);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        if (chessBoardPanel == null || statusPanel == null) {
            return;
        }
        chessBoardPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2,
                (this.getHeight() - chessBoardPanel.getHeight()) / 3);
        statusPanel.setLocation((this.getWidth() - chessBoardPanel.getWidth()) / 2, 0);
    }
}
