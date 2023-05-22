package Spring2023A5;

public class Cuboid extends GraphicObject {
    private double x; //The length of cuboid
    private double y; //The width of cuboid
    private double z; //The height of cuboid

    public Cuboid(ObjectColor oc, double x, double y, double z) {
        super(oc);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double surfaceMeanSize() {
        return 2 * (x * y + y * z + x * z);
    }

    @Override
    public double volume() {
        return x * y * z;
    }

    public String toString(){
        return String.format("Cuboid: x=%.2f, y=%.2f, z=%.2f", x, y, z);
    }
}
