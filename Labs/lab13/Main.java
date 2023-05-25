package labs.lab13;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void printByDefaultSort(List<Shape> shapes){
        Collections.sort(shapes);
        for (Shape s : shapes) {
            System.out.println(s);
        }
    }

    public static void printByArea(List<Shape> shapes){
        System.out.println("print by area");
        shapes.sort(new SortByArea());
        for (Shape s : shapes) {
            System.out.println(s);
        }
    }
    public static void printByDistance(List<Shape> shapes) {
        System.out.println("print by distance");
        shapes.sort(new SortByDistance());
        for (Shape s : shapes) {
            System.out.println(s);
        }
    }
}
