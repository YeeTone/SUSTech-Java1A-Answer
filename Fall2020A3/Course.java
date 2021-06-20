public class Course {
    private static int courseCnt = 1;

    private String name;
    private int cid;

    public Course(String name) {
        this.name = name;
        this.cid = courseCnt++;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Course: %s, cid: %d", name, cid);
    }

    public static int getCourseCnt() {
        return courseCnt;
    }

}
