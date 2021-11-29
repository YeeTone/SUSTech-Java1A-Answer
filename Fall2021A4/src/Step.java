package Fall2021A4;

public class Step {
    private int sid;
    private int rowIndex;
    private int columnIndex;
    private int color;
    private static int stepCnt = 1;

    public Step(int rowIndex, int columnIndex, int color) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.color = color;
        this.sid = stepCnt;
        stepCnt++;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSid() {
        return sid;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getColor() {
        return color;
    }

    public static int getStepCnt() {
        return stepCnt;
    }

    @Override
    public String toString() {
        return String.format("sid: %d, rowIndex: %d, columnIndex: %d, color: %d", sid,rowIndex,columnIndex,color);
    }
}
