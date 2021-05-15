package Spring2021A5;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private static int idCnt = 0;// number of courses created
    private int id;//generated automatically from 1
    private String name; // Introduction to Computer Programming A
    private String abbrevName;// JavaA
    private String code; // CS102A
    private CourseTime time;
    private Teacher teacher;
    private Classroom room;
    private List<Student> students;// who selected this course
    private int capacity;// maxium number of students
    private CourseType type;//Lecture, Lab

    public Course(String code, String name, String abbrevName, Teacher
            teacher, int capacity, CourseType type) {
        this(code,name,abbrevName,teacher,capacity,type,null,null);
    }

    public Course(String code, String name, String abbrevName, Teacher
            teacher, int capacity, CourseType type, CourseTime time, Classroom room) {
        this.code = code;
        this.name = name;
        this.abbrevName = abbrevName;
        this.teacher = teacher;
        this.capacity = capacity;
        this.type = type;
        this.time = time;
        this.room = room;
        idCnt++;
        this.id=idCnt;
        this.students=new ArrayList<>();
    }

    public static int getIdCnt() {
        return idCnt;
    }

    public static void setIdCnt(int idCnt) {
        Course.idCnt = idCnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrevName() {
        setAbbrevName();
        return this.abbrevName;
    }
    public void setAbbrevName(){
        if(this.abbrevName==null||this.abbrevName.isEmpty()){
            StringBuilder b=new StringBuilder();
            String[]split=this.name.split(" ");
            for (String s:split){
                char first=s.charAt(0);
                if(Character.isUpperCase(first)){
                    b.append(first);
                }
            }
            this.abbrevName=b.toString();
        }
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CourseTime getTime() {
        return time;
    }

    public void setTime(CourseTime time) {
        this.time = time;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Classroom getRoom() {
        return room;
    }

    public void setRoom(Classroom room) {
        this.room = room;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public boolean addStudent(Student student) {
        if(students.contains(student)){
            return false;
        }
        return students.add(student);
    }

    public boolean deleteStudent(Student student) {
        return students.remove(student);
    }

    public void setRoomTime(Classroom room, CourseTime time) {
        this.room=room;
        this.time=time;
    }

    public boolean isFull(){
        return this.students.size()>=this.getCapacity();
    }
}
