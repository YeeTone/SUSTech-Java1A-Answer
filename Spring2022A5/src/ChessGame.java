import java.util.List;

public interface ChessGame {
    void loadChessGame(List<String> chessboard);

    ChessColor getCurrentPlayer();

    ChessComponent getChess(int x, int y);

    String getChessboardGraph();

    String getCapturedChess(ChessColor player);
}
