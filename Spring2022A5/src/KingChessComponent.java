import java.util.ArrayList;
import java.util.List;

public class KingChessComponent extends ChessComponent{

    static final char NAME = 'k';
    static final int FULL = 1;

    public KingChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame){
        super(source, chessColor, name, currentChessGame);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> result = new ArrayList<>();

        if (isInvalid()) {
            return result;
        }

        int[][] directions = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        directionMove(directions, result, MIN_MOVE);

        result.sort(comparator());
        return result;
    }
}
