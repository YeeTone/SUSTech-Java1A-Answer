import chessboard.ChessboardComponent;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    public static final Dimension FRAME_SIZE = new Dimension(1080, 1080);
    public static final int CHESSBOARD_SIZE = (int) (FRAME_SIZE.getHeight() * 3 / 4);
    public ChessGameFrame() {
        setTitle("2022 CS102A Project Demo"); //设置标题
        setSize(FRAME_SIZE);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard();
        addLabel();
        addHelloButton();
    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard(){
        ChessboardComponent chessboard = new ChessboardComponent(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        chessboard.setLocation((1080 - CHESSBOARD_SIZE) / 2, (1080 - CHESSBOARD_SIZE) /  4);
        add(chessboard);
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel(){
        JLabel statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(FRAME_SIZE.width / 24, FRAME_SIZE.height / 4 * 3);
        statusLabel.setSize(266, 66);
        statusLabel.setFont(new Font("Rockwell",Font.BOLD, 20));
        add(statusLabel);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton(){
        JButton button = new JButton("Show Hello Here");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(FRAME_SIZE.width / 4, FRAME_SIZE.height / 4 * 3);
        button.setSize(266, 66);
        button.setFont(new Font("Rockwell",Font.BOLD, 20));
        add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame();
            mainFrame.setVisible(true);
        });
    }
}
