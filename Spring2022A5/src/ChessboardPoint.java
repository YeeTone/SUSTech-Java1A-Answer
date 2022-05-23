public class ChessboardPoint {
    private int x;
    private int y;

    public ChessboardPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return String.format("(%d,%d)", x, y);
    }

    public ChessboardPoint offset(int dx, int dy){
        if(ChessComponent.outBoundary(x + dx, y + dy)){
            return null;
        }

        return new ChessboardPoint(x + dx, y + dy);
    }
}

