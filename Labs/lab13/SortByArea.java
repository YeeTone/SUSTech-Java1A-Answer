package labs.lab13;

import java.util.Comparator;

public class SortByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.area(), o2.area());
    }
}

