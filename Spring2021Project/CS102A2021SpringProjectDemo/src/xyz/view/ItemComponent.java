package xyz.view;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ItemComponent extends JComponent {

    private static Font font;
    private int num;

    static {
        try {
            font = Font.createFont( Font.TRUETYPE_FONT,
                    new FileInputStream("src/xyz/view/Font/FrozenNeutra.otf") );
        } catch(Exception e) {
            e.printStackTrace();
        }
        font = font.deriveFont(Font.PLAIN, 80);
    }

    public ItemComponent (int num) {
        this.num = num;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        painting(g);
    }

    private void painting (Graphics g) {
        int spacing = (int) (getWidth() * 0.05);
        Image image = ItemUtil.genItem(num);

        if (image != null) {
            g.drawImage(image, spacing, spacing, getWidth() - 2 * spacing, getHeight() - 2 * spacing, this);
        } else {
            if (num == 0) return;
            g.setFont(font);
            g.drawString("" + num, 18, 55);
        }
    }
}
