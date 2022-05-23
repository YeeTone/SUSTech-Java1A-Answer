import java.util.ArrayList;
import java.util.List;

public class QueenChessComponent extends ChessComponent {

    static final char NAME = 'q';
    static final int FULL = 1;

    public QueenChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame){
        super(source, chessColor, name, currentChessGame);
    }


    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> result = new ArrayList<>();

        if (isInvalid()) {
            return result;
        }

        int[][] directions = {{-1, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        directionMove(directions, result, MAX_MOVE);
        result.sort(comparator());
        return result;
    }
}
