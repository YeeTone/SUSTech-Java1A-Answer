package Spring2021A5;

public class Student extends Person implements CourseOperator{
    public Student(){
        super();
    }
    public Student( String id , String name ) {
        super(id,name);
    }

    public boolean courseExist( String code , String name , CourseType type ){
        for (Course c: schedule.values()){
            if(c!=null && c.getCode().equals(code) && c.getName().equals(name)
                    && c.getType().equals(type)){
                return true;
            }
        }

        return false;
    }
    public boolean courseExist( Course course ){
        return courseExist(course.getCode(),course.getName(),course.getType());
    }

    public boolean chooseCourse( Course course){
        if(course.isFull()){
            return false;
        }

        if(schedule.containsKey(course.getTime())){
            return false;
        }

        for (Course c: schedule.values()){
            if(c.getName().equals(course.getName())
                    && c.getType().equals(course.getType())
                    && c.getCode().equals(course.getCode())){
                return false;
            }
        }

        schedule.put(course.getTime(),course);

        return course.addStudent(this);
    }

    @Override
    public boolean dropCourse(Course course) {
        if (schedule.containsValue(course)) {
            schedule.remove(course.getTime());
            course.getStudents().remove(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeCourse(Course course1, Course course2) {
        if (course2 == null) {
            return false;
        }
        if (course2.isFull()) {
            return false;
        }
        this.schedule.remove(course1.getTime());
        this.schedule.put(course2.getTime(), course2);
        course1.getStudents().remove(this);
        course2.getStudents().add(this);
        return true;
    }

    public String printSchedule(){
        StringBuilder b=new StringBuilder();
        b.append(this.getName()).append("'s Schedule\n");
        for (Day d:Day.values()){
            b.append(d.toString()).append("\n");
            for (int i = 1; i <= 5; i++) {
                CourseTime time=new CourseTime(d,i);
                b.append(i).append(" ");
                if(schedule.containsKey(time)){
                    Course course=schedule.get(time);
                    b.append(course.getCode())
                            .append(", ").append(course.getAbbrevName())
                            .append(", ").append(course.getTeacher().getName())
                            .append(", ").append(course.getRoom().toString());
                }
                b.append("\n");
            }
        }

        return b.toString();
    }

    public int getScheduleCourseNum(){
        return schedule.size();
    }
}


