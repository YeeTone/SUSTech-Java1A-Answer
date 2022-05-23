import java.util.Comparator;
import java.util.List;

public abstract class ChessComponent {
    private ChessboardPoint source;
    private ChessColor chessColor;
    protected char name;

    protected ChessGame currentChessGame;

    protected static final int MAX_MOVE = 7;
    protected static final int MIN_MOVE = 1;

    private static final int CHESSBOARD_SIZE = 8;

    private static final Comparator<ChessboardPoint> COMPARATOR = (o1, o2) -> {
        if(o1.getX() != o2.getX()) {
            return Integer.compare(o1.getX(), o2.getX());
        }else {
            return Integer.compare(o1.getY(), o2.getY());
        }
    };

    public static Comparator<ChessboardPoint> comparator(){
        return COMPARATOR;
    }

    public ChessComponent(){
        this.source = null;
        this.chessColor = ChessColor.NONE;
        this.name = EmptySlotComponent.NAME;
        this.currentChessGame = null;
    }

    public ChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame){
        this.source = source;
        this.chessColor = chessColor;
        this.name = name;
        this.currentChessGame = currentChessGame;
    }

    public ChessColor getChessColor() {
        return chessColor;
    }

    public void setChessColor(ChessColor chessColor) {
        this.chessColor = chessColor;
    }

    public ChessboardPoint getSource() {
        return source;
    }

    public void setSource(ChessboardPoint source) {
        this.source = source;
    }

    public void setCurrentChessGame(ChessGame currentChessGame) {
        this.currentChessGame = currentChessGame;
    }

    public abstract List<ChessboardPoint> canMoveTo();

    @Override
    public String toString() {
        return String.valueOf(this.name);
    }

    protected boolean isInvalid(){
        if(this.getChessColor() == null || this.getChessColor() == ChessColor.NONE){
            return true;
        }

        if(this.getSource() == null || outBoundary(this.source.getX(), this.source.getY())){
            return true;
        }

        return this instanceof EmptySlotComponent;
    }

    public char getName() {
        return name;
    }

    protected static boolean outBoundary(int x, int y){
        return x < 0 || x >= CHESSBOARD_SIZE || y < 0 || y >= CHESSBOARD_SIZE;
    }

    protected void directionMove(int[][] directions, List<ChessboardPoint> result, int bounds) {
        int x = this.getSource().getX(), y = this.getSource().getY();

        for (int[] dir: directions){
            for (int i = 1; i <= bounds; i++) {
                int curX = x + dir[0] * i, curY = y + dir[1] * i;
                if(outBoundary(curX, curY)){
                    break;
                }

                if(currentChessGame.getChess(curX, curY) == null){
                    result.add(new ChessboardPoint(curX, curY));
                    continue;
                }

                if(currentChessGame.getChess(curX, curY).getSource() == null){
                    continue;
                }

                if(currentChessGame.getChess(curX, curY).getChessColor() == this.getChessColor()){
                    break;
                }

                if(currentChessGame.getChess(curX, curY).getChessColor() == this.getChessColor().reverseColor()){
                    result.add(currentChessGame.getChess(curX, curY).getSource());
                    break;
                }

                result.add(currentChessGame.getChess(curX, curY).getSource());
            }
        }
    }
}
