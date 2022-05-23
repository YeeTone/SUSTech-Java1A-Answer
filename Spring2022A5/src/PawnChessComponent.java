import java.util.ArrayList;
import java.util.List;

public class PawnChessComponent extends ChessComponent {

    static final char NAME = 'p';
    static final int FULL = 8;

    public PawnChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame) {
        super(source, chessColor, name, currentChessGame);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> result = new ArrayList<>();

        if (isInvalid()) {
            return result;
        }

        int x = getSource().getX(), y = getSource().getY();

        int dx;
        if (this.getChessColor() == ChessColor.WHITE) {
            dx = -1;
        } else {
            dx = 1;
        }

        if (!outBoundary(x + dx, y - 1) && currentChessGame.getChess(x + dx, y - 1).getChessColor() == this.getChessColor().reverseColor()) {
            result.add(currentChessGame.getChess(x + dx, y - 1).getSource());
        }

        if (!outBoundary(x + dx, y + 1) && currentChessGame.getChess(x + dx, y + 1).getChessColor() == this.getChessColor().reverseColor()) {
            result.add(currentChessGame.getChess(x + dx, y + 1).getSource());
        }

        if (!outBoundary(x + dx, y) && currentChessGame.getChess(x + dx, y).getChessColor() == ChessColor.NONE) {
            result.add(currentChessGame.getChess(x + dx, y).getSource());
        }

        if(isFirst()){
            if(!outBoundary(x + dx, y) && !outBoundary(x + dx * 2, y)){
                if(currentChessGame.getChess(x + dx, y).getChessColor() == ChessColor.NONE && currentChessGame.getChess(x + dx*2, y).getChessColor() == ChessColor.NONE){
                    result.add(currentChessGame.getChess(x + dx*2, y).getSource());
                }
            }
        }

        result.sort(comparator());

        return result;
    }

    private boolean isFirst(){
        return (this.getChessColor() == ChessColor.BLACK && this.getSource().getX() == 1)
                || (this.getChessColor() == ChessColor.WHITE && this.getSource().getX() == 6);
    }
}
