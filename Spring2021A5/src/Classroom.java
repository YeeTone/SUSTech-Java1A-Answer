package Spring2021A5;

import java.util.HashMap;
import java.util.Map;

public class Classroom {
    private int id;//eg:101
    private int seatNum;//eg:50
    private CourseType type;// Lecture or Lab
    private Building building;
    private Map<CourseTime, Course> schedule;
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
    public String toString(){
        return this.type+"R"+this.id+"("+this.seatNum+")"+building.toString();
    }

    public String addCourse(Course course){
        if(schedule.containsKey(course.getTime())){
            return "ERROR: Another course already exists at the time.";
        }

        if(this.type!=course.getType()){
            return "ERROR: Course type not same as classroom.";
        }

        if(this.seatNum<course.getCapacity()){
            return "ERROR: Not enough seats in the classroom for this course.";
        }

        schedule.put(course.getTime(), course);
        return "OK: Adding course to classroom success.";
    }

    public boolean deleteCourse(Course course){
        return schedule.remove(course.getTime(),course);
    }

    public Course getCourse(CourseTime courseTime){
        return schedule.get(courseTime);
    }

    public String printSchedule(){
        StringBuilder b=new StringBuilder();
        b.append(this).append(" Schedule\n");
        for (Day d:Day.values()){
            b.append(d.toString()).append("\n");
            for (int i = 1; i <= 5; i++) {
                CourseTime time=new CourseTime(d,i);
                b.append(i).append(" ");
                if(schedule.containsKey(time)){
                    Course course=schedule.get(time);
                    b.append(course.getCode()).append(", ").append(course.getAbbrevName())
                            .append(", ").append(course.getTeacher().getName());
                }

                b.append("\n");
            }
        }

        return b.toString();
    }

    public int getScheduleCourseNum(){
        return schedule.size();
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
