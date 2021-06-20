public class ScienceStudent extends Student {
    private double generalWeight;
    private double artsWeight;


    /**
     * @param info "name college,generalWeight artWeight"
     */
    public ScienceStudent(String info) {
        super(info.split(" ")[0] + " " + info.split(" ")[1]);
        this.generalWeight = Double.parseDouble(info.split(" ")[2]);
        this.artsWeight = Double.parseDouble(info.split(" ")[3]);
    }


    @Override
    public boolean checkGraduate() {
        double total = 0;
        for (Course course : getCourses()) {
            if (course.getCourseType() == CourseType.SCIENCE) {
                total += course.getCredit();
            } else if (course.getCourseType() == CourseType.ARTS) {
                total += course.getCredit() * artsWeight;
            } else {
                total += course.getCredit() * generalWeight;
            }
        }
        return total >= Student.TOTAL_CREDIT && getCourses().size() >= Student.COURSE_COUNT;
    }

    @Override
    public String toString() {
        return String.format("%s-%s-course %d", super.toString(), "SCIENCE", courses.size());

    }
}
