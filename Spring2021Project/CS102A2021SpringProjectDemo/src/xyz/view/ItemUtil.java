package xyz.view;

import java.awt.*;

public class ItemUtil {
    private final static Image mask;

    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        mask = toolkit.getImage("src/xyz/view/pic/maskgrid.png");
    }

    public static Image genItem (int i) {
        return mask;
        // TODO: This is just a sample. You should implement the method here to provide the 可见的 component according to the argument i

    }
}
