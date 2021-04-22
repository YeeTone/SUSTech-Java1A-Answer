package xyz.model;

public class BoardLocation {
    private int row;
    private int column;

    public BoardLocation (int row, int column) {
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
