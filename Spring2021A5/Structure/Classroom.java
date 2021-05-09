package Spring2021A5.Structure;

import java.util.HashMap;
import java.util.Map;

public class Classroom {
    int id;//eg:101
    int seatNum;//eg:50
    CourseType type;// Lecture or Lab
    Building building;
    Map<CourseTime, Course> schedule;
    public Classroom( int id , int seatNum , Building building, CourseType type ){
        this.id = id;
        this.seatNum = seatNum;
        this.type = type;
        this.building = building;
        schedule = new HashMap<>();
    }
    public Classroom(){
        schedule= new HashMap<>();
    }

    @Override
    public String toString() {
        return String.format("%sR%d(%d)%s", type, id, seatNum, building.toString());
    }

    public String addCourse(Course course){
        return null;
    }

    public boolean deleteCourse(Course course){
        return false;
    }

    public Course getCourse(CourseTime courseTime){
        return null;
    }

    public String printSchedule(){
        return null;
    }

    public int getScheduleCourseNum(){
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Map<CourseTime, Course> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<CourseTime, Course> schedule) {
        this.schedule = schedule;
    }
}
