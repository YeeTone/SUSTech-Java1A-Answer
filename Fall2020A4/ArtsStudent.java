package Fall2020A4;

public class ArtsStudent extends Student{

    @Override
    public boolean checkGraduate() {
        boolean b1 = getCreditByType(CourseType.ARTS.getValue())>=8;
        boolean b2 = getCreditByType(CourseType.GENERAL.getValue())>=4;
        boolean b3 = getCreditByType(CourseType.SCIENCE.getValue())>=4;

        return b1 && b2 && b3;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-course %d",super.toString(),"ARTS",getCourseNumber());
    }
}
