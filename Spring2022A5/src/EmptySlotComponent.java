import java.util.Collections;
import java.util.List;

public class EmptySlotComponent extends ChessComponent{

    static final char NAME = '_';

    public EmptySlotComponent(ChessboardPoint source, ChessColor chessColor, char name, ChessGame currentChessGame){
        super(source, chessColor, name, currentChessGame);
    }
    @Override
    public List<ChessboardPoint> canMoveTo() {
        return Collections.emptyList();
    }
}
