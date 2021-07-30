package Fall2020A3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GradeSystem {
    private ArrayList<Student> studentList;
    private ArrayList<Course> courseList;
    private ArrayList<Grade> gradeList;

    public GradeSystem(){
        this.studentList= new ArrayList<>();
        this.courseList = new ArrayList<>();
        this.gradeList = new ArrayList<>();
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

    public boolean checkStudent(int sid){
        for (Student s:studentList){
            if(s.getSid()==sid){
                return true;
            }
        }
        return false;
    }

    public boolean checkCourse(int cid){
        for (Course c:courseList){
            if(c.getCid()==cid){
                return true;
            }
        }

        return false;
    }

    public boolean addStudent(Student student){
        if(checkStudent(student.getSid())){
            return false;
        }else {
            return this.studentList.add(student);
        }
    }

    public boolean addCourse(Course course){
        if(checkCourse(course.getCid())){
            return false;
        }else {
            return this.courseList.add(course);
        }
    }

    /*public static void main(String[] args) {
        Student wyt = new Student("wyt");
        Course cs102a = new Course("CS102A");
        GradeSystem gs = new GradeSystem();
        gs.addStudent(wyt);
        gs.addCourse(cs102a);

        boolean b1=gs.addGrade(new Grade(cs102a,wyt,12));
        boolean b2 = gs.addGrade(new Grade(cs102a,wyt,44));
    }*/

    public boolean addGrade(Grade grade){
        boolean contains = false;
        for (Grade g:gradeList){
            if(g.getStudent().getSid()==grade.getStudent().getSid()
                    && g.getCourse().getCid()==grade.getCourse().getCid()){
                contains = true;
                if(g.getGrade()<60f &&grade.getGrade()>=60f){
                    g.setGrade(grade.getGrade());
                    return true;
                }
            }
        }
        if(!contains && checkCourse(grade.getCourse().getCid()) && checkStudent(grade.getStudent().getSid())){
            return gradeList.add(grade);
        }else {
            return false;
        }
    }

    public float gpa(int sid){
        int count = 0;
        float sum = 0;
        for (Grade g: gradeList){
            if(g.getStudent().getSid()==sid){
                count+=1;
                sum += g.getGpa();
            }
        }

        return count==0?0:sum/count;
    }

    public float average(int cid){
        int count = 0;
        float sum = 0;
        for (Grade g:gradeList){
            if(g.getCourse().getCid()==cid){
                count+=1;
                sum+=g.getGrade();
            }
        }

        return count==0?0:sum/count;
    }

    public ArrayList<Grade> listStuGrade(int sid){
        ArrayList<Grade> result = new ArrayList<>();

        for (Grade g:gradeList){
            if(g.getStudent().getSid()==sid){
                result.add(g);
            }
        }
        result.sort(Comparator.comparingInt(g -> courseList.indexOf(g.getCourse())));

        return result;
    }

    public ArrayList<Grade> listCouGrade(int cid){
        ArrayList<Grade> result = new ArrayList<>();

        for (Grade g:gradeList){
            if(g.getCourse().getCid()==cid){
                result.add(g);
            }
        }
        result.sort(Comparator.comparingInt(g -> studentList.indexOf(g.getStudent())));

        return result;
    }
}
