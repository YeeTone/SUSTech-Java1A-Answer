import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Student {
    private int number;
    private int college;
    List<Course> courses;

    static final int ART_CREDIT = 8;
    static final int GENERAL_CREDIT = 4;
    static final int SCIENCE_CREDIT = 4;
    static final int TOTAL_CREDIT = 10;
    static final int COURSE_COUNT = 4;


    /**
     * @param info input type "number college"
     */
    public Student(String info) {
        if (info.split(" ").length == 2) {
            this.number = Integer.parseInt(info.split(" ")[0]);
            this.college = Integer.parseInt(info.split(" ")[1]);
        } else {
            throw new IllegalArgumentException("wrong student parameter format");
        }
        this.courses = new ArrayList<>();

    }

    public void courseSelection(Course course) {
        courses.add(course);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public abstract boolean checkGraduate();

    public int getNumber() {
        return number;
    }

    public int getCollege() {
        return college;
    }

    public boolean checkSelected(Course course) {
        return this.courses.contains(course);
    }

    public List<String> getSelectedCoursesNumber() {
        return courses.stream().map(Course::getCourseNumber).collect(Collectors.toList());
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", this.number, this.college);
    }
}
