public class Person {
    private Direction[] directions;
    private int i;
    private int j;

    private int index;

    public Person(int i, int j, int index) {
        this.i = i;
        this.j = j;
        this.index = index;
        this.directions = new Direction[]{
                new Direction(0, 1),
                new Direction(-1, 1),
                new Direction(-1, 0),
                new Direction(-1, -1),
                new Direction(0, -1),
                new Direction(1, -1),
                new Direction(1, 0),
                new Direction(1, 1),
        };
    }

    public int getIndex() {
        return index;
    }

    public void changeDirection(){
        this.index = (this.index + 1) % 8;
    }

    public void walk(int step){
        this.i += directions[this.index].getRow() * step;
        this.j += directions[this.index].getCol() * step;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", i, j);
    }

    public static void main(String[] args) {
        Person p = new Person(0,-1,0);
        p.walk(3);
        p.changeDirection();
        System.out.println(p);
        p.walk(2);
        p.changeDirection();
        System.out.println(p);
        p.walk(5);
        p.changeDirection();
        System.out.println(p);
    }
}
