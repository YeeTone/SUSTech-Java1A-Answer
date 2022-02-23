package listener;


import chess.ChessComponent;
import chessboard.ChessboardComponent;

public class ChessboardChessListener extends ChessListener {
    private final ChessboardComponent chessboardComponent;
    private ChessComponent first;

    public ChessboardChessListener(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    @Override
    public void onClick(ChessComponent chessComponent) {
        System.out.println(chessComponent.getChessboardPoint());
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                chessboardComponent.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                first = null;
                chessboardComponent.repaint();
            } else if (handleSecond(chessComponent)) {
                chessboardComponent.swapChessComponents(first, chessComponent);
                chessboardComponent.swapColor();

                first.setSelected(false);
                first = null;
                chessboardComponent.repaint();
            }
        }
    }

    /**
     *
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboardComponent.getCurrentColor();
    }

    /**
     *
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboardComponent.getCurrentColor() &&
                first.canMoveTo(chessboardComponent.getChessboard(), chessComponent.getChessboardPoint());
    }
}
