package labs.lab11.after;

public class Circle extends Shape {
    private double radius;//圆形独有的属性
    static final int DEFAULT_RADIUS = 5;//圆形独有的属性


    public Circle(double radius, double x, double y) {
        super(x, y);
        this.radius = radius;
    }

    public Circle(double radius) {
        super(0, 0);
        this.radius = radius;
    }

    public Circle(double x, double y) {
        super(x, y);
        this.radius = DEFAULT_RADIUS;
    }

    public void checkColor() {
        if (isInBoundary()) {
            setColor(ShapeColor.GREEN);
        } else {
            setColor(ShapeColor.RED);
        }
    }

    public boolean isInBoundary() {
        if (-1 * Shape.getScreenSize() > x - this.radius || Shape.getScreenSize() < x + this.radius) {
            return false;
        }
        if (-1 * Shape.getScreenSize() > y - this.radius || Shape.getScreenSize() < y + this.radius) {
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

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + this.radius + ", " +
                super.toString() +
                "}\n";
    }

    public void draw() {
        StdDraw.setPenColor(getColor().getColor());
        StdDraw.filledCircle(getX(), getY(), radius);
    }
}
