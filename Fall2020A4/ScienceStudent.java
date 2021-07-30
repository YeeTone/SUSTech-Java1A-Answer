package Fall2020A4;

public class ScienceStudent extends Student{
    private double generalWeight;
    private double artsWeight;
    private final double scienceWeight = 1.0;

    @Override
    public boolean checkGraduate() {
        double creditWeighted
                = getCreditByType(CourseType.GENERAL.getValue()) * generalWeight+
                getCreditByType(CourseType.ARTS.getValue())* artsWeight+
                getCreditByType(CourseType.SCIENCE.getValue())*scienceWeight;

        boolean b1 = creditWeighted>=10;
        boolean b2 = getCourseNumber()>=4;

        return b1 && b2;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-course %d",super.toString(),"SCIENCE",getCourseNumber());
    }

    public void setArtsWeight(double artsWeight) {
        this.artsWeight = artsWeight;
    }

    public void setGeneralWeight(double generalWeight) {
        this.generalWeight = generalWeight;
    }
}
