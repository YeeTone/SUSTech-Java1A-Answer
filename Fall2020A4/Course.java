public class Course {
    private String courseNumber;
    private CourseType courseType;
    private int credit;


//    public Course(String courseNumber, CourseType courseType, int credit) {
//        this.courseNumber = courseNumber;
//        this.courseType = courseType;
//        this.credit = credit;
//    }


    /**
     *
     * @param courseInfo consist of "number type credit"
     */
    public Course(String courseInfo) {

        if(courseInfo.split(" ").length==3){
            this.courseNumber=courseInfo.split(" ")[0];
            this.courseType=CourseType.values()[Integer.parseInt(courseInfo.split(" ")[1])];
            this.credit= Integer.parseInt(courseInfo.split(" ")[2]);
        }else{
            throw new IllegalArgumentException("the format of course Info is wrong");
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNumber='" + courseNumber + '\'' +
                ", courseType=" + courseType +
                ", credit=" + credit +
                '}';
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public int getCredit() {
        return credit;
    }
}
