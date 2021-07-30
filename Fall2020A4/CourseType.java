package Fall2020A4;

public enum CourseType {

    ARTS(0),SCIENCE(1),GENERAL(2);

    private final int value;
    CourseType(int v){
        this.value = v;
    }

    static CourseType valueOf(int v){
        switch (v){
            case 0:{
                return ARTS;
            }
            case 1:{
                return SCIENCE;
            }
            case 2:{
                return GENERAL;
            }
            default:{
                return null;
            }
        }
    }

    public int getValue() {
        return value;
    }
}
