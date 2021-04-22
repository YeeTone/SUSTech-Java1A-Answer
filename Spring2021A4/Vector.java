package Spring2021A3;

public class Vector {
    private int x;
    private int y;
    private int z;

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int quadrant() {
        if (x == 0 || y == 0 || z == 0) {
            return -1;
        }

        if (z > 0) {
            if (y > 0) {
                if (x > 0) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (x > 0) {
                    return 4;
                } else {
                    return 3;
                }
            }
        } else {
            if (y > 0) {
                if (x > 0) {
                    return 5;
                } else {
                    return 6;
                }
            } else {
                if (x > 0) {
                    return 8;
                } else {
                    return 7;
                }
            }
        }
    }

    public double modulus() {
        return Math.sqrt(x*1.0 * x + y*1.0 * y + z*1.0 * z);
    }

    @Override
    public String toString() {
        return "(" + x + ',' + y + ',' + z + ')';
    }

    public Vector add(Vector vector) {
        this.x+= vector.x;
        this.y+= vector.y;
        this.z+= vector.z;
        return this;
    }

    public static Vector add(Vector a, Vector b){
        return a.add(b);
    }

    public double area(Vector vector) {
        double length1 = this.modulus();
        double length2 = vector.modulus();
        double length3 = new Vector(this.x - vector.x, this.y - vector.y, this.z - vector.z).modulus();

        double p = (length1 + length2 + length3) / 2;
        return Math.sqrt(p * (p - length1) * (p - length2) * (p - length3));
    }

    public static double area(Vector a, Vector b){
        return a.area(b);
    }

    public static void main(String[] args) {
        Vector v1 = new Vector(1, -2, 3);
        Vector v2 = new Vector(-5, 2, 2);
        System.out.println("quadrant of v1 is " + v1.quadrant());
        System.out.println("quadrant of v2 is " + v2.quadrant());
        System.out.printf("Area %.5f\n", v1.area(v2));
        System.out.printf("Area %.5f\n", Vector.area(v1, v2));
        System.out.println(v1.add(v2));
        System.out.println(Vector.add(v1, v2));
    }
}
