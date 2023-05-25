package labs.lab13;

import java.util.Comparator;

public class SortByDistance implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o1.distance(), o2.distance());
    }
}

