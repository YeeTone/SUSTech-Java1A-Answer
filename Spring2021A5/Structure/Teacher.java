package Spring2021A5.Structure;

import java.util.List;

public class Teacher extends Person {
    private Location preferLocation;

    public Teacher() {

    }

    public Teacher(Location preferLocation) {
        this.preferLocation = preferLocation;
    }

    public Teacher(String id, String name) {
        super(id, name);
    }

    public Location getPreferLocation() {
        return preferLocation;
    }

    public void setPreferLocation(Location preferLocation) {
        this.preferLocation = preferLocation;
    }

    public List<Classroom> getFreeClassroom(CourseTime time, int capacity,
                                            CourseType type) {
        return null;
    }

    boolean createCourse(Course course) {
        return false;
    }

    boolean createCourse(String code, String name, String abbrevName, CourseTime
            time, Classroom room, int capacity, CourseType type) {
        return false;
    }

    @Override
    public boolean dropCourse(Course course) {
        return false;
    }

    @Override
    public boolean changeCourse(Course oldCourse1, Course newCourse2) {
        return false;
    }

    public String printSchedule() {
        return null;
    }

    public int getScheduleCourseNum() {
        return 0;
    }


}
