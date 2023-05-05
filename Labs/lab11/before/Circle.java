package labs.lab11.before;

public class Circle {
    private double x;
    private double y;
    private double radius;//圆形独有的属性
    private static int screenSize = 10;//图形共有的属性
    static final int DEFAULT_RADIUS = 5;//圆形独有的属性

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public CircleColor getColor() {
        return color;
    }

    public void setColor(CircleColor color) {
        this.color = color;
    }

    private CircleColor color = CircleColor.GRAY;//图形共有的属性


    public Circle(double radius, double x, double y) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(double radius) {
        this.x = 0;
        this.y = 0;
        this.radius = radius;
    }

    public Circle(double x, double y) {
        this.x = x;
        this.y = y;
        this.radius = DEFAULT_RADIUS;
    }

    public void checkColor() {
        if (isInBoundary()) {
            setColor(CircleColor.GREEN);
        } else {
            setColor(CircleColor.RED);
        }
    }

    public static int getScreenSize() {
        return screenSize;
    }


    public boolean isInBoundary() {
        if (-1 * Circle.getScreenSize() > this.x - this.radius || Circle.getScreenSize() < this.x + this.radius) {
            return false;
        }
        if (-1 * Circle.getScreenSize() > this.y - this.radius || Circle.getScreenSize() < this.y + this.radius) {
            return false;
        }
        return true;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public static void setScreenSize(int screenSize) {
        Circle.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", color=" + color +
                '}';
    }

    public void draw() {
        StdDraw.setPenColor(getColor().getColor());
        StdDraw.filledCircle(getX(), getY(), radius);
    }
}
