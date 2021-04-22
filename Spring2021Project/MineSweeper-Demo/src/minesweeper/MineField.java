package minesweeper;

import javax.swing.*;
import java.awt.*;

public class MineField extends JPanel {

    private Grid[][] mineField;

    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     * @param xSize 列数
     * @param ySize 行数
     * @param mineCount 雷数
     */
    public MineField(int xSize,int ySize, int mineCount){

        mineField = new Grid[xSize][ySize];

        this.setSize(Grid.gridSize*xSize,Grid.gridSize*ySize);
        GridLayout lay = new GridLayout();
        lay.setRows(xSize);
        lay.setColumns(ySize);

        this.setLayout(lay);

        for(int a = 0 ;a<xSize;a++){
            for(int b = 0;b<ySize;b++){
                Grid gr = new Grid(a,b);
                mineField[a][b] = gr;
                this.add(gr);
            }
        }

        //mineCount 是预设应有雷的数量。

        //TODO: 埋雷、生成数字的工作需要你们完成噢！可以写在这，也可以写在第一次点击时。

        //TODO: 在初始化雷区时，除了以上内容，还需要做什么？

    }

    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public Grid getGrid(int x, int y){
        try{
            return mineField[x][y];
        }catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }



}
