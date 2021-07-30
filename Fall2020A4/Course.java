package Fall2020A4;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseNumber;
    private CourseType courseType;
    private List<ConcreteUniversity.PrerequisiteRelation> prerequisiteRelation = new ArrayList<>();
    private int credit;

    @Override
    public String toString() {
        return "Course{" +
                "courseNumber='" + courseNumber + '\'' +
                ", courseType=" + courseType +
                ", credit=" + credit +
                '}';
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public List<ConcreteUniversity.PrerequisiteRelation> getPrerequisiteRelation() {
        return prerequisiteRelation;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public int getCredit() {
        return credit;
    }

    public void setPrerequisiteRelation(List<ConcreteUniversity.PrerequisiteRelation> prerequisiteRelation) {
        this.prerequisiteRelation = prerequisiteRelation;
    }
}
