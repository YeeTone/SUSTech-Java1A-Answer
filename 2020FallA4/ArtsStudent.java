public class ArtsStudent extends Student {


    public ArtsStudent(String info) {
        super(info);
    }

    @Override
    public boolean checkGraduate() {
        int artCredit = 0;
        int generalCredit = 0;
        int scienceCredit = 0;
        for (Course course : getCourses()) {
            if (course.getCourseType() == CourseType.ARTS) {
                artCredit += course.getCredit();
            } else if (course.getCourseType() == CourseType.SCIENCE) {
                scienceCredit += course.getCredit();
            } else {
                generalCredit += course.getCredit();
            }
        }
        return artCredit >= Student.ART_CREDIT && scienceCredit >= Student.SCIENCE_CREDIT && generalCredit >= Student.GENERAL_CREDIT;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-course %d", super.toString(), "ARTS", courses.size());
    }
}
