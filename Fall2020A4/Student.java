package Fall2020A4;

import java.util.ArrayList;
import java.util.List;

public abstract class Student {
    private int number;
    private int college;
    private List<Course> courses = new ArrayList<>();

    public abstract boolean checkGraduate();

    @Override
    public String toString() {
        return String.format("%d-%d", this.number, this.college);
    }

    public int getCreditByType(int v){
        int credit = 0;
        for (Course c:courses){
            if(c.getCourseType().getValue()==v){
                credit += c.getCredit();
            }
        }

        return credit;
    }

    public int getCourseNumber(){
        return courses.size();
    }

    public int getNumber() {
        return number;
    }

    public int getCollege() {
        return college;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course c){
        this.courses.add(c);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCollege(int college) {
        this.college = college;
    }

}

