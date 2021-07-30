package Fall2020A3;

public class Grade {
    private Course course;
    private Student student;
    private float grade;
    private float gpa;

    public Grade(Course course, Student student,float grade){
        this.course= course;
        this.student =student;
        this.grade = grade;
        this.gpa = calGpa(grade);
    }

    public static float calGpa(float grade){
        if(0<=grade &&grade<=59){
            return 0;
        }else if(60<= grade && grade<=62){
            return 1.15f;
        }else if(63<=grade && grade<=66){
            return 1.63f;
        }else if(67<=grade && grade<= 69){
            return 2.08f;
        }else if(70<=grade && grade<=72){
            return 2.42f;
        }else if(73<=grade && grade<= 76){
            return 2.78f;
        }else if(77<= grade && grade<= 79){
            return 3.09f;
        }else if(80<= grade && grade<=82){
            return 3.32f;
        }else if(83<=grade && grade<= 86){
            return 3.55f;
        }else if(87<=grade && grade<=89){
            return 3.73f;
        }else if(90 <= grade && grade<= 92){
            return 3.85f;
        }else if(93<=grade && grade<= 96){
            return 3.94f;
        }else if (97<=grade && grade<=100){
            return 4.00f;
        }else {
            return 0f;
        }
    }

    public Course getCourse() {
        return course;
    }

    public float getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGrade(float grade) {
        this.grade = grade;
        this.gpa = calGpa(grade);
    }

    @Override
    public String toString() {
        return String.format("sid: %d, cid: %d, grade: %.1f, gpa: %.2f",student.getSid(),course.getCid(),grade,gpa);
    }
}
