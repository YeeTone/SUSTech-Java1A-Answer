package labs.lab8;

public class Circle {
    private double radius;
    private double x;
    private double y;
    private static int cnt = 0;     //part 2 added
    private int id;                 //part 2 added

    //exercise 2 added
    @Override
    public String toString() {
        return String.format("Circle #%d: radius = %.2f, x = %.2f, y = %.2f\n"
                ,id,radius,x,y);
    }

    //part 2 added
//    @Override
//    public String toString() {
//        return "Circle{" +
//                "radius=" + radius +
//                ", x=" + x +
//                ", y=" + y +
//                '}';
//    }

    public Circle(double radius) {
        this.radius = radius;
        cnt++;      //part2 added
        id = cnt;   //exercise2 added
    }

    public Circle(double radius, double x, double y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        cnt++;          //part2 added
        this.id = cnt;  //exercise2 added
    }

    //exercise 2 added
    public int getId() {
        return id;
    }

    //part2 added
    public static int getCnt(){
        return cnt;
    }

    public double area() {
        return radius * radius * Math.PI;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public void position() {
        System.out.printf("Position of the circle is (%.1f, %.1f)\n", x, y);
    }

    public double distanceToOrigin() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}