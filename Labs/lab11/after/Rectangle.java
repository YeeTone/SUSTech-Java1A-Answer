package labs.lab11.after;

public class Rectangle extends Shape {

    private double width;
    private double height;


    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public void checkColor() {
        if (isInBoundary()) {
            this.setColor(ShapeColor.GREEN);
        } else {
            this.setColor(ShapeColor.RED);
        }
    }

    public boolean isInBoundary() {
        if (-1 * Shape.getScreenSize() > x - this.width / 2 || Shape.getScreenSize() < x + this.width / 2) {
            return false;
        }
        if (-1 * Shape.getScreenSize() > y - this.height / 2 || Shape.getScreenSize() < y + this.height / 2) {
            return false;
        }
        return true;
    }


    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", "+
                super.toString() +
                "}\n";
    }

    public void draw() {
        StdDraw.setPenColor(this.getColor().getColor());
        StdDraw.filledRectangle(this.getX(), this.getY(), this.width / 2, this.height / 2);
    }

}
