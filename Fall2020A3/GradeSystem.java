import java.util.*;

public class GradeSystem {
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<Grade> gradeList;

    public GradeSystem() {
        studentList = new ArrayList<Student>();
        courseList = new ArrayList<Course>();
        gradeList = new ArrayList<Grade>();
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Grade> getGradeList() {
        return gradeList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public boolean checkStudent(int sid) {
        for (Student student : studentList) {
            if (sid == student.getSid()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCourse(int cid) {
        for (Course course : courseList) {
            if (cid == course.getCid()) {
                return true;
            }
        }
        return false;
    }

    public boolean addStudent(Student student) {
        if (checkStudent(student.getSid())) {
            return false;
        }
        studentList.add(student);
        return true;
    }

    public boolean addCourse(Course course) {
        if (checkCourse(course.getCid())) {
            return false;
        }
        courseList.add(course);
        return true;
    }

    public boolean addGrade(Grade grade) {
        if (!(checkCourse(grade.getCourse().getCid()) && checkStudent(grade.getStudent().getSid())))
            return false;
        for (Grade value : gradeList) {
            if (grade.getStudent().getSid() == value.getStudent().getSid() && grade.getCourse().getCid() == value.getCourse().getCid()) {
                if (value.getGrade() < 60 && grade.getGrade() >= 60) {
                    value.setGrade(grade.getGrade());
                    return true;
                } else {
                    return false;
                }
            }
        }
        gradeList.add(grade);
        return true;
    }

    public float gpa(int sid) {
        float sum = 0;
        int num = 0;
        for (Grade grade : gradeList) {
            if (grade.getStudent().getSid() == sid) {
                sum += grade.getGpa();
                num++;
            }
        }
        if (num == 0) {
            return 0;
        }
        return sum / num;
    }

    public float average(int cid) {
        float sum = 0;
        int num = 0;
        for (Grade grade : gradeList) {
            if (grade.getCourse().getCid() == cid) {
                sum += grade.getGrade();
                num++;
            }
        }
        if (num == 0) {
            return 0;
        }
        return sum / num;
    }

    public ArrayList<Grade> listStuGrade(int sid) {
        ArrayList<Grade> stuGradeList = new ArrayList<Grade>();
        for (Course course : courseList) {
            for (Grade grade : gradeList) {
                if (grade.getStudent().getSid() == sid && grade.getCourse().getCid() == course.getCid()) {
                    stuGradeList.add(grade);
                }
            }
        }
        return stuGradeList;
    }

    public ArrayList<Grade> listCouGrade(int cid) {
        ArrayList<Grade> couGradeList = new ArrayList<Grade>();
        for (Student student : studentList) {
            for (Grade grade : gradeList) {
                if (grade.getCourse().getCid() == cid && grade.getStudent().getSid() == student.getSid()) {
                    couGradeList.add(grade);
                }
            }
        }
        return couGradeList;
    }
}
