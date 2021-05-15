package Spring2021A5;

import org.junit.jupiter.api.*;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class A5OnlineJudge {

    @BeforeAll
    public static void initDb(){
        Db.InitRooms();
        Db.InitCourse();
    }
    @Test void test00_25_Course_idCnt(){
        CourseTime time = new CourseTime(Day.Saturday,3);
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 50, building1, CourseType.Lecture);
        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", Db.teachers.get(5), 150, CourseType.Lecture,time,room1);

        //System.out.println(Db.courses.get(Db.courses.size()-1).getTeacher().getName());
        //for (Course c : Db.courses){
        //    System.out.println(c.getId());
        //}
        assertEquals(Db.courses.size()+1, course2.getId(),"Course.idCnt and id value error.");

    }
    @Test
    public void test_01_Building() {
        Building building1 = new Building(Location.LycheePark, 1);
        assertEquals(1, building1.getId());
        assertEquals("LycheePark", building1.getLocation().toString());
        building1.setLocation(Location.TeachingBuilding);
        building1.setId(2);
        assertEquals(2, building1.getId(),"Error in Building.getId()");
        assertEquals("TeachingBuilding", building1.getLocation().toString(),"Error in Building.getLocation()");

    }
    @Test
    public void test_02_Building() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom classroom1 = new Classroom(102, 200, building1, CourseType.Lecture);
        Building building2 = new Building(Location.TeachingBuilding, 2);
        Classroom classroom2 = new Classroom(201, 80, building2, CourseType.Lab);
        assertTrue(building1.addRoom(classroom1),"Error in Building.addRoom()");
        assertTrue(building2.addRoom(classroom2),"Error in Building.addRoom()");
        assertFalse(building1.addRoom(classroom2),"Error in Building.addRoom()");
        assertFalse(building2.addRoom(classroom1),"Error in Building.addRoom()");
    }
    @Test
    public void test_03_Building() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom classroom1 = new Classroom(102, 200, building1, CourseType.Lecture);
        Building building2 = new Building(Location.TeachingBuilding, 2);
        Classroom classroom2 = new Classroom(201, 80, building2, CourseType.Lab);
        building1.addRoom(classroom1);
        building2.addRoom(classroom2);
        assertFalse(building2.deleteRoom(classroom1),"Error in Building.deleteRoom() if the room is not in the building.");
        assertFalse(building1.deleteRoom(classroom2),"Error in Building.deleteRoom() if the room is not in the building.");

    }
    @Test
    public void test_04_Building() {
        Building building1 = new Building(Location.LycheePark, 1);
        assertEquals("LP#1", building1.toString());
        building1.setLocation(Location.TeachingBuilding);
        building1.setId(2);
        assertEquals("TB#2", building1.toString(),"Error in Building.toString()");
        assertEquals(2, building1.getId(),"Error in Building.getId()");
    }
    @Test
    public void test_05_Building() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom classroom1 = new Classroom(102, 200, building1, CourseType.Lecture);
        Building building2 = new Building(Location.TeachingBuilding, 2);
        Classroom classroom2 = new Classroom(201, 80, building2, CourseType.Lab);
        building1.addRoom(classroom1);
        building2.addRoom(classroom2);

        assertTrue(building1.deleteRoom(classroom1),"Error in Building.deleteRoom()");
        assertTrue(building2.deleteRoom(classroom2),"Error in Building.deleteRoom()");

    }

    @Test
    public void test_06_Classroom() {

        Building building2 = new Building(Location.TeachingBuilding, 6);
        Classroom room2 = new Classroom(402, 30, building2, CourseType.Lab);

        assertEquals("LabR402(30)TB#6",room2.toString(),"Error in Classroom.toString().");


    }
    @Test
    public void test_07_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 200, building1, CourseType.Lecture);
        room1.setType(CourseType.Lecture);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);
        course1.setCapacity(201);
        course1.setTime(new CourseTime(Day.Wednesday,2));
        assertEquals("ERROR: Not enough seats in the classroom for this course.", room1.addCourse(course1),"Error in Classroom.addCourse() if not enough seats in classroom for this course.");
    }
    @Test
    public void test_08_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 200, building1, CourseType.Lecture);
        room1.setType(CourseType.Lecture);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);

        course1.setCapacity(120);
        course1.setType(CourseType.Lab);
        course1.setTime(new CourseTime(Day.Wednesday,2));
        assertEquals("ERROR: Course type not same as classroom.", room1.addCourse(course1),"Error in Classroom.addCourse() if course type is not same as classroom.");

        course1.setType(CourseType.Lecture);
        course1.setTime(new CourseTime(Day.Wednesday,2));
        assertEquals("OK: Adding course to classroom success.", room1.addCourse(course1),"Error in Classroom.addCourse().");
        //assertEquals(course1.getRoom().toString(), room1.toString());
        //assertEquals("ERROR: Another course already exists at the time.", room1.addCourse(course1));
    }
    @Test
    public void test_09_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = Db.buildings.get(0).getRooms().get(0);
        room1.setType(CourseType.Lecture);
        room1.setSeatNum(200);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);
        course1.setTime(new CourseTime(Day.Wednesday,2));
        course1.setCapacity(200);
        assertEquals("OK: Adding course to classroom success.", room1.addCourse(course1),"Error in Classroom.addCourse().");

    }
    @Test
    public void test_10_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 200, building1, CourseType.Lecture);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);

        course1.setTime(new CourseTime(Day.Wednesday,2));
        room1.addCourse(course1);
        assertEquals("ERROR: Another course already exists at the time.", room1.addCourse(course1),"Error in Classroom.addCourse() if another course already exists at the time.");
    }
    @Test
    public void test_11_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 200, building1, CourseType.Lecture);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);

        course1.setTime(new CourseTime(Day.Wednesday,2));
        room1.addCourse(course1);

        room1.setType(CourseType.Lab);
        course1.setCapacity(205);

        assertEquals("ERROR: Another course already exists at the time.", room1.addCourse(course1),"Error in Classroom.addCourse() if another course already exists at the time.");
    }

    @Test
    public void test_12_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 200, building1, CourseType.Lecture);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);
        course1.setTime(new CourseTime(Day.Wednesday,2));
        course1.setCapacity(200);
        room1.addCourse(course1);
        //assertEquals("OK: Adding course to classroom success.", room1.addCourse(course1));
        assertNotNull(room1.getCourse(course1.getTime()),"Error in Classroom.GetCourse() or Course.getTime().");
    }
    @Test
    public void test_13_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 200, building1, CourseType.Lecture);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);
        course1.setTime(new CourseTime(Day.Tuesday,3));
        room1.addCourse(course1);
        String strSchedule = "LectureR101(200)LP#1 Schedule\n" +
                "Monday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Tuesday\n" +
                "1 \n" +
                "2 \n" +
                "3 CS102A, JavaA, ZHANG3\n" +
                "4 \n" +
                "5 \n" +
                "Wednesday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Thursday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Friday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Saturday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Sunday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n";
        assertEquals(strSchedule, room1.printSchedule(),"Error in Classroom.printSchedule().");
        assertEquals(1, room1.getScheduleCourseNum(),"Error in Classroom.getScheduleCourseNum().");
        //System.out.print(room1.printSchedule());
    }

    @Test
    public void test_14_Classroom() {
        Building building1 = new Building(Location.TeachingBuilding, 2);
        Classroom room1 = new Classroom(201, 80, building1, CourseType.Lab);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");

        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Tuesday,3));
        room1.addCourse(course1);

        Course course2 = new Course("CS102B","Introduction to Computer Programming B", "JavaB", teacher, 50, CourseType.Lab);
        course2.setTime(new CourseTime(Day.Thursday,4));
        room1.addCourse(course2);

        String strSchedule = "LabR201(80)TB#2 Schedule\n" +
                "Monday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Tuesday\n" +
                "1 \n" +
                "2 \n" +
                "3 CS102A, JavaA, ZHANG3\n" +
                "4 \n" +
                "5 \n" +
                "Wednesday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Thursday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 CS102B, JavaB, ZHANG3\n" +
                "5 \n" +
                "Friday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Saturday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Sunday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n";
        assertEquals(strSchedule, room1.printSchedule(),"Error in Classroom.printSchedule().");
        assertEquals(2, room1.getScheduleCourseNum(),"Error in Classroom.getScheduleCourseNum().");
        //System.out.print(room1.printSchedule());
    }

    @Test
    public void test_15_Classroom() {
        Building building2 = new Building(Location.LycheePark, 6);
        Classroom room2 = new Classroom(402, 50, building2, CourseType.Lab);
        room2.setType(CourseType.Lab);
        Building building1 = new Building(Location.TeachingBuilding, 2);
        Classroom room1 = new Classroom(201, 80, building1, CourseType.Lab);
        Teacher teacher = new Teacher("3000001" , "ZHANG3");

        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Tuesday,3));
        room1.addCourse(course1);

        Course course2 = new Course("CS102B","Introduction to Computer Programming B", "JavaB", teacher, 50, CourseType.Lab);
        course2.setTime(new CourseTime(Day.Thursday,4));
        room1.addCourse(course2);
        assertFalse(room2.deleteCourse(course1),"Error in Classroom.deleteCourse().");
        assertTrue(room1.deleteCourse(course1),"Error in Classroom.deleteCourse().");


    }
    @Test
    public void test_16_Course() {
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS105","Computer System Design and Applications", "CSD", teacher, 150, CourseType.Lecture);
        assertEquals("CSD", course1.getAbbrevName(),"Error in getting the default course abbrevName from its name.");

    }
    @Test
    public void test_17_Course() {
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS105","Computer System Design and Applications", "", teacher, 150, CourseType.Lecture);
        assertEquals("CSDA", course1.getAbbrevName(),"Error in getting the default course abbrevName from its name.");
    }
    @Test
    public void test_18_Course() {
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS105","Computer System Design and Applications", "CSDA", teacher, 150, CourseType.Lecture);
        course1.setCapacity(120);
        assertEquals(120, course1.getCapacity(),"Error in Course.get/setCapacity()");
        course1.setType(CourseType.Lecture);
        assertNotEquals(CourseType.Lab, course1.getType(),"Error in Course.get/setType()");
        course1.setTime(new CourseTime(Day.Wednesday,5));
        assertTrue(course1.getTime().equals(new CourseTime(Day.Wednesday,5)),"Error in Course.get/setTime()");

    }
    @Test
    public void test_19_Course() {
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 60, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Monday,2));
        Building building1 = new Building(Location.TeachingBuilding, 1);
        Classroom room1 = new Classroom(201, 80, building1, CourseType.Lab);
        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lab,new CourseTime(Day.Thursday,2),room1);

        Student student1 = new Student( "10000003" , "Zhang3" );
        course1.addStudent(student1);
        course1.addStudent(Db.GetStudent("10000005"));
        assertEquals(2,course1.getStudents().size(),"Error in Course.addStudent().");
    }
    @Test
    public void test_20_Course() {
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 60, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Monday,2));
        Building building1 = new Building(Location.TeachingBuilding, 1);
        Classroom room1 = new Classroom(201, 80, building1, CourseType.Lab);
        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lab,new CourseTime(Day.Thursday,2),room1);

        Student student1 = new Student( "10000003" , "Zhang3" );
        course1.addStudent(student1);
        course1.addStudent(Db.GetStudent("10000005"));

        assertFalse(course2.deleteStudent(student1),"ERROR: cannot delete a student who did not select the course.");
        assertEquals(2,course1.getStudents().size());
        assertTrue(course1.deleteStudent(student1),"Error in Course.deleteStudent.");
        assertEquals(1,course1.getStudents().size(),"Error in Course.deleteStudent.");
        course1.deleteStudent(Db.GetStudent("10000005"));
        assertEquals(0,course1.getStudents().size(),"Error in Course.deleteStudent.");
    }
    @Test
    public void test_21_Course() {
        Teacher teacher = new Teacher("3000003" , "ZHANG3");
        CourseTime time = new CourseTime(Day.Saturday,3);
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 50, building1, CourseType.Lecture);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 150, CourseType.Lecture,time,room1);

        Student student1 = new Student( "10000003" , "Zhang3" );
        course1.addStudent(student1);
        assertEquals(1,course1.getStudents().size(),"Error in Course.addStudent().");

    }
    @Test
    public void test_22_Course() {
        Teacher teacher = new Teacher("3000003" , "ZHANG3");
        CourseTime time = new CourseTime(Day.Saturday,3);
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 50, building1, CourseType.Lecture);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 150, CourseType.Lecture,time,room1);

        Student student1 = new Student( "10000003" , "Zhang3" );
        course1.addStudent(student1);
        assertEquals(1,course1.getStudents().size(),"Error in Course.addStudent().");

        assertTrue(course1.deleteStudent(student1),"Error in Course.deleteStudent().");
    }
    @Test
    public void test_23_Course() {
        Teacher teacher = new Teacher("3000003" , "ZHANG3");
        CourseTime time = new CourseTime(Day.Saturday,3);
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 50, building1, CourseType.Lecture);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 150, CourseType.Lecture,time,room1);
        Student student1 = new Student( "10000003" , "Zhang3" );
        assertFalse(course1.deleteStudent(student1),"Error in Course.deleteStudent().");
    }
    @Test
    public void test_24_Course() {
        //System.out.print(Db.courses.size());
        Course course1 = Db.courses.get(10);
        //System.out.print(course1.getId());
        assertEquals(11,course1.getId(),"Error in setting static Course.idCnt and Course.id");
        CourseTime time = new CourseTime(Day.Saturday,3);
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 50, building1, CourseType.Lecture);
        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", Db.teachers.get(5), 150, CourseType.Lecture,time,room1);
        //System.out.print(course2.getId());
    }


    @Test
    public void test_26_Course() {
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 150, CourseType.Lecture);
        Teacher t = Db.teachers.get("HU Chunfeng");
        course1.setTeacher(t);
        assertEquals("HU Chunfeng", course1.getTeacher().getName(),"Error in Course.Get/SetTeacher()");
    }


    @Test
    public void test_27_Teacher() {
        Teacher teacher1 = new Teacher("30000001" , "TZHAO222");
        teacher1.setPreferLocation(Location.TeachingBuilding);
        assertEquals(Location.TeachingBuilding, teacher1.getPreferLocation(), "Error in Teacher.get/setPreferLocation.");

    }
    @Test
    public void test_28_Teacher() {
        Teacher teacher1 = Db.GetTeacher("HU Chunfeng");
        String strSchedule = "HU Chunfeng's Schedule\n" +
                "Monday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Tuesday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Wednesday\n" +
                "1 \n" +
                "2 CS102B, JavaB, LabR201(80)TB#2\n" +
                "3 CS102A, JavaA, LabR201(80)TB#2\n" +
                "4 \n" +
                "5 \n" +
                "Thursday\n" +
                "1 \n" +
                "2 CS102A, JavaA, LabR406(50)LP#6\n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Friday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Saturday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Sunday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n";
        System.out.print(teacher1.printSchedule());
        assertEquals(strSchedule, teacher1.printSchedule().toString(),"Error in Teacher.printSchedule()");
        assertEquals(3, teacher1.getScheduleCourseNum(),"Error in Teacher.getScheduleCourseNum()");
    }
    @Test
    public void test_29_Teacher() {
        Teacher teacher1 = Db.GetTeacher("HU Chunfeng");
        CourseTime time1 = new CourseTime(Day.Wednesday,3);
        Course course1 = teacher1.schedule.get(time1);


        //System.out.print(teacher1.printSchedule());
        //System.out.print(course1.getRoom().printSchedule());
        //assertEquals(1,course1.getRoom().getScheduleCourseNum());

        Building building1 = Db.buildings.get(0);
        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 40, CourseType.Lab);
        Classroom room2 = new Classroom(404, 50, building1, CourseType.Lab);
        course2.setRoomTime(room2, new CourseTime(Day.Tuesday,2));
        //System.out.print(teacher1.printSchedule());
        assertTrue(teacher1.changeCourse(course1,course2),"Error in Teacher.changeCourse().");

        String strSchedule = "HU Chunfeng's Schedule\n" +
                "Monday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Tuesday\n" +
                "1 \n" +
                "2 CS102A, JavaA, LabR404(50)LP#1\n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Wednesday\n" +
                "1 \n" +
                "2 CS102B, JavaB, LabR201(80)TB#2\n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Thursday\n" +
                "1 \n" +
                "2 CS102A, JavaA, LabR406(50)LP#6\n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Friday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Saturday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Sunday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n";
        //System.out.print(teacher1.printSchedule());
        assertEquals(strSchedule, teacher1.printSchedule().toString(),"Error in Teacher.printSchedule()");
        assertEquals(3, teacher1.getScheduleCourseNum(),"Error in Teacher.getScheduleCourseNum()");
    }
    @Test
    public void test_30_Teacher() {
        Teacher teacher1 = Db.GetTeacher("HU Chunfeng");
        teacher1.setPreferLocation(Location.LycheePark);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "Javaa", teacher1, 60, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Thursday,3));
        assertEquals(5,teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType()).size(),"ERROR in Teacher.getFreeClassroom() with preferLocation");


    }

    @Test
    public void test_31_Teacher() {
        Teacher teacher1 = Db.GetTeacher("HU Chunfeng");
        teacher1.setPreferLocation(Location.LycheePark);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "Javaa", teacher1, 60, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Thursday,3));
        course1.setCapacity(100);
        assertEquals(0,teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType()).size(),"ERROR in Teacher.getFreeClassroom() with preferLocation");

    }
    @Test
    public void test_32_Teacher() {
        Teacher teacher1 = Db.GetTeacher("HU Chunfeng");
        teacher1.setPreferLocation(Location.LycheePark);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "Javaa", teacher1, 50, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Tuesday,2));
        //course1.setCapacity(50);
        assertEquals(5,teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType()).size(),"ERROR in Teacher.getFreeClassroom() with preferLocation");
        for(Classroom room : teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType())){
            System.out.println(room.toString());
        }
    }
    @Test
    public void test_33_Teacher() {
        Teacher teacher1 = Db.GetTeacher("HU Chunfeng");
        teacher1.setPreferLocation(Location.LycheePark);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "Javaa", teacher1, 50, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Thursday,2));
        course1.setCapacity(65);
        assertEquals(4,teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType()).size(),"ERROR in Teacher.getFreeClassroom() with preferLocation");
        //for(Classroom room : teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType())){
        //    System.out.println(room.toString());
        //}
    }
    @Test
    public void test_34_Teacher() {
        Teacher teacher1 = Db.GetTeacher("ZHANG Fengwei");
        teacher1.setPreferLocation(Location.LycheePark);
        System.out.print(teacher1.printSchedule());
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 40, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Tuesday,3));

        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(402, 60, building1, CourseType.Lab);
        course1.setRoomTime(room1, course1.getTime());
        assertTrue(teacher1.createCourse(course1),"Error in Teacher.CreateCourse().");
        assertEquals(3,teacher1.getScheduleCourseNum(),"Error in getting course number in teacher's schedule.");
        //System.out.print(teacher1.printSchedule());
        //System.out.print(course1.getRoom().printSchedule());
        assertEquals(1,course1.getRoom().getScheduleCourseNum(),"Error in getting course number in teacher's schedule.");

    }
    @Test
    public void test_35_Teacher() {
        Teacher teacher1 = Db.GetTeacher("ZHANG Fengwei");
        teacher1.setPreferLocation(Location.LycheePark);
        System.out.print(teacher1.printSchedule());
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 40, CourseType.Lab);
        course1.setTime(new CourseTime(Day.Tuesday,3));

        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(402, 60, building1, CourseType.Lab);
        course1.setRoomTime(room1, course1.getTime());
        assertFalse(teacher1.createCourse(course1),"cannot create a duplicated course.");
        assertEquals(3,teacher1.getScheduleCourseNum(),"Error in getting course number in teacher's schedule.");
        //System.out.print(teacher1.printSchedule());
        //System.out.print(course1.getRoom().printSchedule());
        //assertEquals(1,course1.getRoom().getScheduleCourseNum());




    }
    @Test
    public void test_36_Teacher() {
        Teacher teacher1 = Db.GetTeacher("ZHANG Fengwei");
        teacher1.setPreferLocation(Location.LycheePark);
        //System.out.print(teacher1.printSchedule());
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 40, CourseType.Lab);
        CourseTime time1 = new CourseTime(Day.Tuesday,3);

        Classroom room1 = teacher1.schedule.get(time1).getRoom();
        course1.setRoomTime(room1, time1);
        assertEquals(3,teacher1.getScheduleCourseNum(),"Error in getting course number in teacher's schedule.");
        assertTrue(teacher1.dropCourse(course1));
        assertEquals(2,teacher1.getScheduleCourseNum(),"Error in getting course number in teacher's schedule.");
        assertTrue(teacher1.createCourse(course1));
        assertEquals(3,teacher1.getScheduleCourseNum(),"Error in getting course number in teacher's schedule.");
        //System.out.print(teacher1.printSchedule());
        //System.out.print(course1.getRoom().printSchedule());
        //assertEquals(1,course1.getRoom().getScheduleCourseNum());


    }
    @Test
    public void test_37_Teacher() {
        Teacher teacher1 = Db.GetTeacher("ZHANG Fengwei");
        teacher1.setPreferLocation(Location.LycheePark);
        //System.out.print(teacher1.printSchedule());
        CourseTime time1 = new CourseTime(Day.Tuesday,3);
        Course course1 = teacher1.schedule.get(time1);


        //System.out.print(teacher1.printSchedule());
        //System.out.print(course1.getRoom().printSchedule());
        //assertEquals(1,course1.getRoom().getScheduleCourseNum());

        Building building1 = Db.buildings.get(0);
        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 40, CourseType.Lab);
        Classroom room2 = new Classroom(404, 50, building1, CourseType.Lab);
        course2.setRoomTime(room2, new CourseTime(Day.Tuesday,2));
        //System.out.print(teacher1.printSchedule());
        assertTrue(teacher1.changeCourse(course1,course2),"Error in Teacher.ChangeCourse()");
        //System.out.print(teacher1.printSchedule());
        assertEquals(3,teacher1.getScheduleCourseNum(),"Error in Teacher.ChangeCourse(), to delete old course and add new course.");
        assertNull(teacher1.schedule.get(course1.getTime()),"Error in Teacher.ChangeCourse(), old course should be deleted from schedule.");
        assertNotNull(teacher1.schedule.get(course2.getTime()),"Error in Teacher.ChangeCourse(), new course should be be added to schedule.");
    }
    /**
     * This test can simply help you check if your program is correct, but note that this test does not include test for method {@code removeApplicant}.
     * <p>
     * Passing the local test does not guarantee that you will pass the OnlineJudge test
     */
    @Test
    public void test_38_Student() {
        Student student1 = Db.GetStudent("10000002");

        Teacher teacher1 = Db.GetTeacher("30000005");
        System.out.print(teacher1.printSchedule());
        teacher1.setPreferLocation(Location.LycheePark);
        Course course1 = teacher1.schedule.get(new CourseTime(Day.Tuesday, 2));
        assertTrue(student1.chooseCourse(course1),"Error in Student.ChooseCourse().");
        assertEquals(1, course1.getStudents().size(),"Error to get the students number of selecting a course.");
    }
    @Test
    public void test_39_Student() {
        Student student1 = Db.GetStudent("10000002");

        Teacher teacher1 = Db.GetTeacher("30000005");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        //System.out.print(student1.printSchedule());
        Course course3 = teacher1.schedule.get(new CourseTime(Day.Thursday, 2));
        assertTrue(student1.chooseCourse(course2),"ERROR in Student.ChooseCourse().");
        //System.out.print(student1.printSchedule());
        assertFalse(student1.chooseCourse(course3),"ERROR: cannot choose a course exist");
        //student1.chooseCourse(course3);
        //System.out.print(student1.printSchedule());
    }
    @Test
    public void test_40_Student() {
        Student student1 = Db.GetStudent("10000002");

        Teacher teacher1 = Db.GetTeacher("30000005");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        //System.out.print(student1.printSchedule());
        Course course3 = teacher1.schedule.get(new CourseTime(Day.Thursday, 2));
        assertTrue(student1.courseExist(course2),"Cannot choose an existing course.");
        //System.out.print(student1.printSchedule());
        assertTrue(student1.courseExist(course3),"Cannot choose an existing course.");
    }
    @Test
    public void test_41_Student() {
        Student student1 = Db.GetStudent("10000003");

        Teacher teacher1 = Db.GetTeacher("30000005");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        CourseTime time1 = new CourseTime(Day.Tuesday,3);
        Course course1 = teacher1.schedule.get(time1);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        //System.out.print(student1.printSchedule());
        Course course3 = teacher1.schedule.get(new CourseTime(Day.Thursday, 2));
        student1.chooseCourse(course2);
        student1.chooseCourse(course3);
        assertEquals(2,course2.getStudents().size(),"Error to get the students number of selecting a course.");
    }
    @Test
    public void test_42_Student() {
        Student student1 = Db.GetStudent("10000004");

        Teacher teacher1 = Db.GetTeacher("30000005");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        CourseTime time1 = new CourseTime(Day.Tuesday,3);
        Course course1 = teacher1.schedule.get(time1);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        //System.out.print(student1.printSchedule());
        Course course3 = teacher1.schedule.get(new CourseTime(Day.Thursday, 2));
        course2.setCapacity(2);
        assertFalse(student1.chooseCourse(course2),"Error: cannot choose a course the capacity is full. ");
        assertEquals(2,course2.getStudents().size(),"Error: cannot choose a course the capacity is full. ");
    }
    @Test
    public void test_43_Student() {
        Student student1 = Db.GetStudent("10000005");

        Teacher teacher1 = Db.GetTeacher("30000005");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        CourseTime time1 = new CourseTime(Day.Tuesday,3);
        Course course1 = teacher1.schedule.get(time1);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        //System.out.print(student1.printSchedule());
        Course course3 = teacher1.schedule.get(new CourseTime(Day.Thursday, 2));
        course2.setCapacity(30);
        assertTrue(student1.chooseCourse(course2),"Error in Student.chooseCourse(). ");

        Course course4 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 50, CourseType.Lab);
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(402, 50, building1, CourseType.Lab);
        course4.setRoomTime(room1, course2.getTime());
        assertFalse(student1.chooseCourse(course4),"ERROR: cannot choose a new course at an old courseTime. The student does does have a course at this time.");
    }
    @Test
    public void test_44_Student() {
        Student student1 = Db.GetStudent("10000002");

        Teacher teacher1 = Db.GetTeacher("30000005");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        CourseTime time1 = new CourseTime(Day.Tuesday,2);
        Course course1 = teacher1.schedule.get(time1);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        Course course3 = teacher1.schedule.get(new CourseTime(Day.Thursday, 2));
        //System.out.print(student1.printSchedule());

        assertTrue(student1.changeCourse(course1, course3),"Error in Student.changeCourse() with same teacher. ");

    }
    @Test
    public void test_45_Student() {
        Student student1 = Db.GetStudent("10000002");

        Teacher teacher1 = Db.GetTeacher("30000005");
        Teacher teacher2 = Db.GetTeacher("30000006");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        CourseTime time1 = new CourseTime(Day.Tuesday,2);

        Course course1 = teacher1.schedule.get(time1);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        Course course3 = teacher2.schedule.get(new CourseTime(Day.Thursday, 2));
        student1.chooseCourse(course1);
        System.out.print(student1.printSchedule());

        assertTrue(student1.changeCourse(course1, course3),"Error in Student.changeCourse() with different teachers. ");
        System.out.print(student1.printSchedule());
    }
    @Test
    public void test_46_Student() {
        Student student1 = Db.GetStudent("10000002");

        Teacher teacher1 = Db.GetTeacher("30000005");
        Teacher teacher2 = Db.GetTeacher("30000006");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        CourseTime time1 = new CourseTime(Day.Tuesday,2);
        Course course1 = teacher1.schedule.get(time1);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        Course course3 = teacher2.schedule.get(new CourseTime(Day.Thursday, 2));
        System.out.print(student1.printSchedule());
        assertFalse(student1.dropCourse(course1),"Error: a student cannot drop a course not chosen.");

    }
    @Test
    public void test_47_Student() {
        Student student1 = Db.GetStudent("10000002");

        Teacher teacher1 = Db.GetTeacher("30000005");
        Teacher teacher2 = Db.GetTeacher("30000006");
        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        CourseTime time1 = new CourseTime(Day.Tuesday,2);
        Course course1 = teacher1.schedule.get(time1);
        Course course2 = teacher1.schedule.get(new CourseTime(Day.Wednesday, 2));
        Course course3 = teacher2.schedule.get(new CourseTime(Day.Thursday, 2));
        //System.out.print(student1.printSchedule());
        assertEquals(2, student1.getScheduleCourseNum(),"Error in Student.dropCourse() and getScheduleCourseNum().");
        assertTrue(student1.dropCourse(course2),"Error in Student.dropCourse().");
        assertEquals(1, student1.getScheduleCourseNum(),"Error in Student.dropCourse() and getScheduleCourseNum().");
    }
    @Test
    public void test_48_Student() {
        Student student1 = Db.GetStudent("10000002");

        //System.out.print(teacher1.printSchedule());
        //teacher1.setPreferLocation(Location.LycheePark);
        System.out.print(student1.printSchedule());
        String strSchedule = "Qian2's Schedule\n" +
                "Monday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Tuesday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Wednesday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Thursday\n" +
                "1 \n" +
                "2 CS102A, JavaA, WANG Wei, LabR206(80)TB#2\n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Friday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Saturday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Sunday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n";
        //System.out.print(teacher1.printSchedule());
        assertEquals(strSchedule, student1.printSchedule().toString(),"Error in Student.printSchedule()");
    }
    @Test
    public void test_49_Student() {
        Student student1 = Db.GetStudent("10000003");

        System.out.print(student1.printSchedule());
        String strSchedule = "Zhang3's Schedule\n" +
                "Monday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Tuesday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Wednesday\n" +
                "1 \n" +
                "2 CS102B, JavaB, HU Chunfeng, LabR201(80)TB#2\n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Thursday\n" +
                "1 \n" +
                "2 CS102A, JavaA, HU Chunfeng, LabR406(50)LP#6\n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Friday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Saturday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Sunday\n" +
                "1 \n" +
                "2 \n" +
                "3 \n" +
                "4 \n" +
                "5 \n";
        //System.out.print(teacher1.printSchedule());
        assertEquals(strSchedule, student1.printSchedule().toString(),"Error in Student.printSchedule()");
    }
    @Test
    public void test_50_Student() {
        Student student2 = Db.GetStudent("10000005");
        //System.out.print(student2.getName());
        assertEquals(1,student2.getScheduleCourseNum(),"Error in Student.getScheduleCourseNum()");
    }
}


