public class Grade {
    private Course course;
    private Student student;
    private float grade;
    private float gpa;

    public Grade(Course course, Student student, float grade) {
        this.course = course;
        this.student = student;
        this.grade = grade;
        this.gpa = calGpa(grade);
    }

    public static float calGpa(float grade) {
        if (grade <= 62 && grade >= 60)
            return 1.15f;
        if (grade <= 66 && grade >= 63)
            return 1.63f;
        if (grade <= 69 && grade >= 67)
            return 2.08f;
        if (grade <= 72 && grade >= 70)
            return 2.42f;
        if (grade <= 76 && grade >= 73)
            return 2.78f;
        if (grade <= 79 && grade >= 77)
            return 3.09f;
        if (grade <= 82 && grade >= 80)
            return 3.32f;
        if (grade <= 86 && grade >= 83)
            return 3.55f;
        if (grade <= 89 && grade >= 87)
            return 3.73f;
        if (grade <= 92 && grade >= 90)
            return 3.85f;
        if (grade <= 96 && grade >= 93)
            return 3.94f;
        if (grade <= 100 && grade >= 97)
            return 4.00f;
        return 0f;
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
        return String.format("sid: %d, cid: %d, grade: %.1f, gpa: %.2f", student.getSid(), course.getCid(), grade, gpa);
    }
}
