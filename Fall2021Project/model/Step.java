package Fall2021Project.model;

import java.awt.*;

public class Step {
    private final boolean isCheating;
    private final int x;
    private final int y;
    private final Color color;
    private static int stepCnt = 0;
    private final int sid;

    public Step(boolean isCheating, int x, int y, Color color) {
        this(++stepCnt,isCheating,x,y,color);
    }


    public Step(int sid,boolean isCheating, int x, int y, Color color) {
        this.isCheating = isCheating;
        this.x = x;
        this.y = y;
        this.color = color;
        this.sid = sid;
    }

    public int colorInt(){
        if(color == null){
            return 0;
        }else if(color == Color.BLACK){
            return -1;
        }else {
            return 1;
        }
    }

    public static void setStepCnt(int stepCnt) {
        Step.stepCnt = stepCnt;
    }

    @Override
    public String toString() {
        return sid +" " + isCheating+" "+x+" "+y +" "+ colorInt();
    }
}
