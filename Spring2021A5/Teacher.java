package Spring2021A5;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person implements CourseOperator{

    private Location preferLocation;

    public Teacher() {
        super();
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
        List<Classroom> prefer=getFreeClassroom(time, capacity, type, preferLocation);
        Location anotherLocation=(preferLocation==Location.LycheePark
                                    ?Location.TeachingBuilding
                                    :Location.LycheePark);
        List<Classroom> another=getFreeClassroom(time,capacity,type,anotherLocation);

        return !prefer.isEmpty()?prefer:another;
    }

    public List<Classroom> getFreeClassroom(CourseTime time, int capacity,
                                            CourseType type,Location location){
        List<Classroom> result=new ArrayList<>();
        for (Building b:Db.buildings){
            for (Classroom cr:b.getRooms()){
                if(cr.getType().equals(type) && cr.getSeatNum()>=capacity
                    && !cr.getSchedule().containsKey(time) && b.getLocation().equals(location)){
                    result.add(cr);
                }
            }
        }

        return result;
    }

    public boolean createCourse(Course course) {
        if(course.getTime()==null||course.getRoom()==null){
            return false;
        }

        if(schedule.containsKey(course.getTime())){
            return false;
        }

        if(course.getType()!=course.getRoom().getType()){
            return false;
        }

        if(course.getRoom().getSchedule().containsKey(course.getTime())){
            return false;
        }

        if(course.getRoom().getSeatNum()<course.getCapacity()){
            return false;
        }

        schedule.put(course.getTime(),course);
        course.getRoom().addCourse(course);
        return true;
    }

    public boolean createCourse(String code, String name, String abbrevName, CourseTime
            time, Classroom room, int capacity, CourseType type) {
        return createCourse(new Course(code,name,abbrevName,this,capacity,type,time,room));
    }

    @Override
    public boolean dropCourse(Course course) {
        if(!schedule.containsKey(course.getTime())){
            return false;
        }

        Course c=schedule.get(course.getTime());
        if(c.getCode().equals(course.getCode())
            && c.getTime().equals(course.getTime())
            && c.getTeacher().equals(course.getTeacher())){
            this.schedule.remove(course.getTime());
        }

        c.getRoom().getSchedule().remove(c.getTime());
        return true;
    }

    @Override
    public boolean changeCourse(Course oldCourse1, Course newCourse2) {
        return dropCourse(oldCourse1) && createCourse(newCourse2);
    }

    public String printSchedule() {
        StringBuilder b=new StringBuilder();
        b.append(this.getName()).append("'s Schedule\n");
        for (Day d:Day.values()){
            b.append(d).append("\n");
            for (int i = 1; i <= 5; i++) {
                CourseTime time=new CourseTime(d,i);
                b.append(i).append(" ");
                if(schedule.containsKey(time)){
                    Course course=schedule.get(time);
                    b.append(course.getCode()).append(", ")
                            .append(course.getAbbrevName()).append(", ")
                            .append(course.getRoom());
                }

                b.append("\n");
            }
        }

        return b.toString();
    }

    public int getScheduleCourseNum() {
        return schedule.size();
    }
}
