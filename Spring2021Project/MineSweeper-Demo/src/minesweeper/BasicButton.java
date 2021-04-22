package minesweeper;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 该类型是按钮元件的抽象类，继承它可以更简便地设定好一个按钮。
 * Grid类就是继承自此抽象类的。
 */
public abstract class BasicButton extends JButton {

    public BasicButton(String text){
        this.setText("text");
        init();
    }

    public BasicButton(){
        init();
    }

    private void init(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==1){
                    BasicButton.this.onMouseLeftClicked();
                }
                if(e.getButton()==3){
                    BasicButton.this.onMouseRightClicked();
                }
            }

            /*
            如果你们愿意着手于更好看的UI，下面关于鼠标事件的四个方法都可以补充。
            (例如...鼠标悬停在上方时给按钮加一个蓝色边框？)
             */
            @Override
            public void mousePressed(MouseEvent e) {
                //TODO: 是否需要按下鼠标时的动作？
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                //TODO: 是否需要松开鼠标时的动作？
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                //TODO: 是否需要鼠标悬停时的动作？
            }
            @Override
            public void mouseExited(MouseEvent e) {
                //TODO: 是否需要鼠标离开时的动作？
            }

        });
    }

    /**
     * 修改按钮上的文字。
     * @param text 新的文字
     */
    public void changeText(String text){
        this.setText(text);
    }


    /**
     * 当该按钮被左击时将调用本方法。
     */
    public abstract void onMouseLeftClicked();

    /**
     * 当该按钮被右击时将调用本方法。
     */
    public abstract void onMouseRightClicked();

}
