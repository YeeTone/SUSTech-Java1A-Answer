package xyz.view;

import javax.swing.*;
import java.awt.*;

public class SquareComponent extends JPanel {
    private static Image grid = Toolkit.getDefaultToolkit().getImage("src/xyz/view/pic/emptygrid.png");
    private int size;

    public SquareComponent (int size) {
        setLayout(new GridLayout(1, 1));
        setSize(size, size);

        this.size = size;
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent(g);
        paintSquare(g);
    }

    private void paintSquare (Graphics g) {
        g.drawImage(grid, size, size, getWidth() - 2 * size, getHeight() - 2 * size, this);
    }
}
