package labs.lab11.before;

public class Rectangle {

    private double width;
    private double height;
    private double x;
    private double y;

    private static int screenSize = 10;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public RectangleColor getColor() {
        return color;
    }

    public void setColor(RectangleColor color) {
        this.color = color;
    }

    private RectangleColor color = RectangleColor.GRAY;

    public Rectangle(double x, double y, double width, double height) {
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;
    }

    public void checkColor() {
        if (isInBoundary()) {
            this.setColor(RectangleColor.GREEN);
        } else {
            this.setColor(RectangleColor.RED);
        }
    }

    private static double getScreenSize(){
        return screenSize;
    }

    public boolean isInBoundary() {
        if (-1 * Rectangle.getScreenSize() > this.x - this.width / 2 || Rectangle.getScreenSize() < this.x + this.width / 2) {
            return false;
        }
        if (-1 * Rectangle.getScreenSize() > this.y - this.height / 2 || Rectangle.getScreenSize() < this.y + this.height / 2) {
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

    public static void setScreenSize(int screenSize) {
        Rectangle.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }

    public void draw() {
        StdDraw.setPenColor(this.getColor().getColor());
        StdDraw.filledRectangle(this.getX(), this.getY(), this.width / 2, this.height / 2);
    }

}
