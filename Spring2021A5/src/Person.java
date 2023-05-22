package Spring2021A5;

import java.util.HashMap;
import java.util.Map;

public abstract class Person  implements CourseOperator{
    String id;
    String name;
    Map<CourseTime, Course> schedule;

    public Person() {
        schedule= new HashMap<>();
    }

    public Person(String id, String name){
        this();
        this.id= id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<CourseTime, Course> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<CourseTime, Course> schedule) {
        this.schedule = schedule;
    }
}
