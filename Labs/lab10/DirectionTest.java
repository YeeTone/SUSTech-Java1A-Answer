public class DirectionTest {
    private Direction d;

    public DirectionTest(Direction d) {
        this.d = d;
    }

    public Direction getDirection() {
        return d;
    }

    public static void main(String[] args) {
        DirectionTest test = new DirectionTest(Direction.EAST);
        switch (test.getDirection()) {
            case EAST: // must be unqualified name of the enum constant
                System.out.println("Countries in the east: Japan, Korea");
                break;
            case WEST:
                System.out.println("Countries in the west: US, Germany");
                break;
            case SOUTH:
                System.out.println("Countries in the south: Australia, New Zealand");
                break;
            case NORTH:
                System.out.println("Countries in the north: Russia, Mongolia");
                break;
        }
    }
}