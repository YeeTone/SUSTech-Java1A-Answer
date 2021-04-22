package xyz.model;

public class Board {
    private Square[][] grid;
    private int row;
    private int column;

    public Board(int row, int col) {
        grid = new Square[row][col];
        this.column = col;
        this.row = row;

        iniGrid();
        iniItem();
    }

    public void iniGrid () {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = new Square(new BoardLocation(i, j));
            }
        }
    }

    public void iniItem () {
        // TODO: This is only a demo implementation.
        grid[0][0].setNumberOfLandMine((byte) 3);
    }

    public byte calculateNum (int i, int j) {
        // TODO: You may implement a method here to calculate the number of mine among a grid
        return 0;
    }

    public Square getGridAt(BoardLocation location) {
        return grid[location.getRow()][location.getColumn()];
    }

    public int getNumAt(BoardLocation location) {
        return getGridAt(location).getNum();
    }

    public void openGrid(BoardLocation location) {
        getGridAt(location).setOpened(true);
    }

    public void flagGrid (BoardLocation location) {
        getGridAt(location).setFlag(true);
    }

    // click type == 1 means that is left click
    // click type == 2 means that is middle click
    // click type == 3 means that is right click
    public boolean isValidClick (BoardLocation location, int clickType) {
        // TODO: You should implement a method here to check whether it is a valid action
        switch (clickType) {
            case 1:
            case 2:
                if (!getGridAt(location).isOpened() && !getGridAt(location).isFlag()) return true;
                else return false;
            default:
                return true;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
