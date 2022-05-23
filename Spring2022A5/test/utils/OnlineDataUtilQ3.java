package utils;

public class OnlineDataUtilQ3 {

    /*
    String[][] chessType = {
            {"King","K","k"},
            {"Queen","Q","q"},
            {"Bishop","B","b"},
            {"Knight","N","n"},
            {"Rook","R","r"},
            {"Pawn","P","p"},
            {"Empty","-"}
    };*/
    private String[][] capturedList={
            {"",""},
            {"B 1\nP 1","q 1\nr 1\np 1"},
            {"Q 1\nR 1\nB 1\nN 2\nP 3","q 1\nn 1\np 2"},
            {"B 1\nP 2",""},
            {"Q 1\nR 1\nB 1\nN 1\nP 2","r 1\nb 1\nn 1\np 3"}
    };



    private int[][][] testChessPoint={
            {{2,0},{2,0},{7,5},{7,5},{6,7}},
            {{0,0},{0,0}},
            {{7,4},{0,2},{0,2},{0,4},{7,7},{7,7}},
            {{0,1},{7,1},{7,1},{4,1},{6,5},{1,1},{0,7},{7,3},{4,6},{0,2}},
            {{4,6},{4,6},{7,1},{6,1},{1,2},{2,6},{4,0},{0,7}}
    };

    private String[][][] ChessAnswerList={
            {{"Empty"},{"Empty","_"},{"Bishop"},{"Bishop","b"},{"Pawn","p"}},
            {{"Rook"},{"Rook","R"}},
            {{"King","k"},{"King","K"},{"King"},{"Rook","R"},{"Rook","r"},{"Rook"}},
            {{"Knight","N"},{"Knight","n"},{"Knight"},{"Queen","q"},{"Pawn","p"},{"Pawn","P"},{"Empty","_"},{"Empty"},{"Pawn","p"},{"Bishop","B"}},
            {{"Knight"},{"Knight","N"},{"King"},{"Pawn"},{"Pawn","P"},{"Bishop","B"},{"Knight"},{"Empty"}}
    };
    private boolean[][] testType = {
            {false,true,false,true,true},
            {false,true},
            {true,true,false,true,true,false},
            {true,true,false,false,true,true,true,false,true,true},
            {false,true,false,false,true,true,false,false}
    };

    public String[][] getCapturedList() {
        return capturedList;
    }

    public int[][][] getTestChessPoint() {
        return testChessPoint;
    }

    public String[][][] getChessAnswerList() {
        return ChessAnswerList;
    }

    public boolean[][] getTestType() {
        return testType;
    }
}
