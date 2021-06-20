public class Student {
    private static int studentCnt = 1;

    private String name;
    private int sid;

    public Student(String name) {
        this.name = name;
        this.sid = studentCnt++;
    }

    public int getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Student: %s, sid: %d", name, sid);
    }

    public static int getStudentCnt() {
        return studentCnt;
    }

}
