package labs.lab12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeTest2 {
    public static void main(String[] args) {
        List<Circle> circleList = new ArrayList<Circle>();
        Shape.setScreenSize(14);
        StdDraw.setScale(-Shape.getScreenSize(), Shape.getScreenSize());

        for (int i = 1; i < Shape.getScreenSize(); i += 2) {
            circleList.add(new Circle(i, 0, -Shape.getScreenSize()));
        }
        Collections.sort(circleList);
        for (int i = 0; i < circleList.size(); i++) {
            circleList.get(i).customizedColor(ColorScheme.RAINBOW, i);
        }
    }
}
