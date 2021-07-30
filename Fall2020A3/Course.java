package Fall2020A3;

public class Course {
    private int cid;
    private String name;
    private static int courseCnt = 1;

    public Course(String name){
        this.name = name;
        this.cid = courseCnt;
        courseCnt++;
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

    public static int getCourseCnt() {
        return courseCnt;
    }

    @Override
    public String toString() {
        return String.format("Course: %s, cid: %d",name,cid);
    }
}
