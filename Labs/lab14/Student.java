package labs.lab14;

public class Student implements Comparable<Student>{
    private String group;
    private String name;
    private int grade;

    public Student(String group, String name, int grade) {
        this.group = group;
        this.name = name;
        this.grade = grade;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
}
