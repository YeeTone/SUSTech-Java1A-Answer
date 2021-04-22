package minesweeper;

/**
 * 此类的每个对象代表扫雷雷区上的一个格子(是一个按钮)。
 * x,y属性为格子在雷区上的位置，
 * 而statu为格子的状态，包含未点击、已点击和已插旗。
 * 同时，此类中包含关于点击格子的监听器！
 */
public class Grid extends BasicButton{

    /**
     * 此变量为单个正方形格子的大小(单位：像素)，
     * 可以根据需要进行修改。
     */
    public static int gridSize = 30;


    private int x;
    private int y;
    private GridStatu statu = GridStatu.Covered;
    private int content = 0;


    public Grid(int x,int y){
        this.setSize(gridSize,gridSize);
        this.setFocusable(false);
        this.x = x;
        this.y = y;
    }

    /**
     * 设置格子的内容。内容可以是0~8以及-1(-1代表这是个雷)。
     * @param content 该格子的内容
     */
    public void setContent(int content){
        this.content = content;
    }

    /**
     * 获得格子目前所处的状态。
     * @return 格子状态
     */
    public GridStatu getStatu(){
        return statu;
    }

    /**
     * 当该按钮被左击时将调用本方法。
     */
    @Override
    public void onMouseLeftClicked() {
        System.out.printf("Gird (%d,%d) is left-clicked.\n",x,y);
        this.setText(content+"");
        this.statu = GridStatu.Clicked;
        GameController.nextTurn();

        //TODO: 在左键点击一个格子的时候，还需要做什么？

    }


    /**
     * 当该按钮被右击时将调用本方法。
     */
    @Override
    public void onMouseRightClicked() {
        System.out.printf("Gird (%d,%d) is right-clicked.\n",x,y);
        this.setText("F");
        this.statu = GridStatu.Flag;
        GameController.nextTurn();

        //TODO: 在右键点击一个格子的时候，还需要做什么？

    }
}
