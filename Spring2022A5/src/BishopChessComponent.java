import java.util.ArrayList;
import java.util.List;

public class BishopChessComponent extends ChessComponent {

    static final char NAME = 'b';
    static final int FULL = 2;

    public BishopChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame){
        super(source, chessColor, name, currentChessGame);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> result = new ArrayList<>();

        if (isInvalid()) {
            return result;
        }

        int[][] directions = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
        directionMove(directions, result, MAX_MOVE);
        result.sort(comparator());
        return result;
    }

}
