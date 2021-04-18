package Spring2021A4;

public enum Library {
    Lynn,Yidan,LearningNexus;

    @Override
    public String toString() {
        switch (this){
            case Lynn:{
                return "Lynn";
            }
            case Yidan:{
                return "Yidan";
            }
            case LearningNexus:{
                return "LearningNexus";
            }
            default:{
                return "";
            }
        }
    }
}
