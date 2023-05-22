package Spring2023A5;

public class Sphere extends GraphicObject {
    private double radius;

    public Sphere(ObjectColor oc, double radius) {
        super(oc);
        this.radius = radius;
    }

    @Override
    public double surfaceMeanSize() {
        return 4 * Math.PI * radius * radius;
    }

    @Override
    public double volume() {
        return 4 * Math.PI * radius * radius * radius / 3;
    }

    @Override
    public String toString() {
        return String.format("Sphere: r=%.2f", radius);
    }
}
