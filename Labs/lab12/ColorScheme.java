package labs.lab12;

import java.awt.*;

public enum ColorScheme {
    SKY(new Color[]{new Color(0, 102, 204),
            new Color(0, 128, 255),
            new Color(51, 153, 255),
            new Color(102, 178, 255),
            new Color(153, 204, 255),
            new Color(204, 229, 255)}),
    RAINBOW(new Color[]{
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            new Color(0, 128, 255),
            new Color(204, 153, 255)}),
    GRAY(new Color[]{
            Color.DARK_GRAY,

    Color.GRAY,
    Color.LIGHT_GRAY});
        Color[] colorList;
private ColorScheme(Color[] color) {
        colorList = color;
        }
public Color[] getColorScheme() {
        return colorList;
        }
}
