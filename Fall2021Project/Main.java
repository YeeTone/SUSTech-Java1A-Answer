package Fall2021Project;

import Fall2021Project.view.GameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GameFrame mainFrame = new GameFrame(800);
            mainFrame.setVisible(true); 
        });
    }
}
