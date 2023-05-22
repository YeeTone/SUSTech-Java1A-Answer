package Spring2023A5;

public class Cone extends GraphicObject {
    private double radius;
    private double length; //for all test data, the length is larger than radius

    public Cone(ObjectColor oc, double radius, double length) {
        super(oc);
        this.radius = radius;
        this.length = length;
    }

    @Override
    public double surfaceMeanSize() {
        return Math.PI * radius * radius + Math.PI * radius * length;
    }

    @Override
    public double volume() {
        double h = Math.sqrt(length*length - radius*radius);
        return Math.PI * radius * radius * h / 3;
    }

    public String toString(){
        return String.format("Cone: r=%.2f, l=%.2f", radius, length);
    }
}
