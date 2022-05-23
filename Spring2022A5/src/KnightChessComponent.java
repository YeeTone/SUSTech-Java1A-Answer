import java.util.ArrayList;
import java.util.List;

public class KnightChessComponent extends ChessComponent{

    static final char NAME = 'n';
    static final int FULL = 2;

    public KnightChessComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame){
        super(source, chessColor, name, currentChessGame);
    }

    @Override
    public List<ChessboardPoint> canMoveTo() {
        List<ChessboardPoint> result = new ArrayList<>();

        if(isInvalid()){
            return result;
        }

        int[][] knightMoveDirections = {{-2, -1}, {-1, -2}, {-2, 1}, {1, -2}, {-1, 2}, {2, -1}, {1, 2}, {2, 1}};

        int x = getSource().getX(), y = getSource().getY();

        for (int[] dir: knightMoveDirections){
            int curX = x + dir[0], curY = y + dir[1];

            if(outBoundary(curX, curY)){
                continue;
            }

            if(currentChessGame.getChess(curX, curY).getChessColor() != getChessColor()){
                result.add(currentChessGame.getChess(curX, curY).getSource());
            }
        }

        result.sort(comparator());
        return result;
    }


}
