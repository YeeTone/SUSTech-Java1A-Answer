package components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        initial();
    }

    private void initial(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1){
                    onMouseLeftClicked();
                }
                if(e.getButton()==3){
                   onMouseRightClicked();
                }
            }
        });
    }

    /**
     * invoke this method when mouse left clicked
     */
    public abstract void onMouseLeftClicked();

    /**
     * invoke this method when mouse right clicked
     */
    public abstract void onMouseRightClicked();
}
