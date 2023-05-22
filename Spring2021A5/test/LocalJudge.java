package Spring2021A5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LocalJudge {

    @BeforeAll
    public static void initDb(){
        Db.InitRooms();
        Db.InitCourse();
    }

    @Test
    public void test01_Building() {
        try {
            Class c=Class.forName("Spring2021A5.Person");
            if (!Modifier.isAbstract(c.getModifiers())){
                fail("Class 'Person' should be abstract");
            }
            assertTrue(Modifier.isAbstract(c.getModifiers()));
        }
        catch (ClassNotFoundException e) {
            fail("Cannot find class 'Person'. Please check the class name and its modifier. Class 'Person' should be abstract");

        }
        Building building1 = new Building(Location.TeachingBuilding, 1);
        assertEquals("TB#1", building1.toString());
        building1.setLocation(Location.LycheePark);
        building1.setId(2);
        assertEquals("LP#2", building1.toString());
        assertEquals(2, building1.getId());

        Classroom classroom1 = new Classroom(101, 200, building1, CourseType.Lecture);
        Building building2 = new Building(Location.TeachingBuilding, 2);
        Classroom classroom2 = new Classroom(101, 80, building2, CourseType.Lab);
        assertTrue(building1.addRoom(classroom1));
        assertTrue(building2.addRoom(classroom2));
        assertFalse(building1.addRoom(classroom2));
        assertFalse(building2.addRoom(classroom1));
        assertFalse(building2.deleteRoom(classroom1));
        assertFalse(building1.deleteRoom(classroom2));
        assertTrue(building1.deleteRoom(classroom1));
        assertTrue(building2.deleteRoom(classroom2));
        assertFalse(building1.deleteRoom(classroom1));
        assertFalse(building2.deleteRoom(classroom2));


    }
    @Test
    public void test_02_Classroom() {
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 200, building1, CourseType.Lecture);
        room1.setType(CourseType.Lecture);
        Building building2 = new Building(Location.LycheePark, 6);
        Classroom room2 = new Classroom(402, 50, building2, CourseType.Lab);
        room2.setType(CourseType.Lab);
        assertEquals("LectureR101(200)LP#1",room1.toString());
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 50, CourseType.Lecture);
        course1.setCapacity(201);
        assertEquals("ERROR: Not enough seats in the classroom for this course.", room1.addCourse(course1));
        course1.setCapacity(120);
        course1.setType(CourseType.Lab);
        course1.setTime(new CourseTime(Day.Wednesday,2));
        //assertEquals("ERROR: Course type not same as classroom.", room1.addCourse(course1));
        course1.setType(CourseType.Lecture);
        assertEquals("OK: Adding course to classroom success.", room1.addCourse(course1));
        //assertEquals(course1.getRoom().toString(), room1.toString());
        //assertEquals("ERROR: Another course already exists at the time.", room1.addCourse(course1));

        assertNotNull(room1.getCourse(course1.getTime()));
        assertNull(room2.getCourse(course1.getTime()));
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
                "3 \n" +
                "4 \n" +
                "5 \n" +
                "Wednesday\n" +
                "1 \n" +
                "2 CS102A, JavaA, ZHANG3\n" +
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
        assertEquals(strSchedule, room1.printSchedule());
        //System.out.print(room1.printSchedule());
        assertFalse(room2.deleteCourse(course1));
        assertTrue(room1.deleteCourse(course1));


    }
    @Test
    public void test_03_Course() {
        Teacher teacher = new Teacher("3000001" , "ZHANG3");
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher, 150, CourseType.Lecture);
        course1.setAbbrevName("JavaA");
        assertEquals("JavaA", course1.getAbbrevName());
        course1.setCapacity(120);
        assertEquals(120, course1.getCapacity());
        course1.setType(CourseType.Lab);
        assertEquals(CourseType.Lab, course1.getType());
        course1.setTime(new CourseTime(Day.Wednesday,2));
        assertTrue(course1.getTime().equals(new CourseTime(Day.Wednesday,2)));
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(101, 50, building1, CourseType.Lecture);
        room1.setType(CourseType.Lab);
        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "", teacher, 50, CourseType.Lab,new CourseTime(Day.Thursday,2),room1);
        assertEquals("ICPA", course2.getAbbrevName());
        Student student1 = new Student( "10000003" , "Zhang3" );
        course1.addStudent(student1);
        assertEquals(1,course1.getStudents().size());
        assertFalse(course2.deleteStudent(student1));
        assertTrue(course1.deleteStudent(student1));
    }
    @Test
    public void test_04_Teacher() {
        Teacher teacher1 = new Teacher("3000001" , "TZHAO1");
        teacher1.setPreferLocation(Location.TeachingBuilding);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 160, CourseType.Lecture);
        course1.setTime(new CourseTime(Day.Monday,3));
        assertEquals(4,teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType()).size());
        course1.setCapacity(100);
        assertEquals(6,teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType()).size());
        course1.setCapacity(60);
        course1.setType(CourseType.Lab);
        assertEquals(6,teacher1.getFreeClassroom(course1.getTime(),course1.getCapacity(),course1.getType()).size());
        //System.out.print(teacher1.printSchedule());
        assertEquals(0,teacher1.getScheduleCourseNum());
        //assertFalse(teacher1.courseExist(course1));
        assertFalse(teacher1.createCourse(course1));//classroom not initialized

        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(402, 60, building1, CourseType.Lab);
        course1.setRoomTime(room1, new CourseTime(Day.Monday,1));
        assertTrue(teacher1.createCourse(course1));
        assertEquals(1,teacher1.getScheduleCourseNum());
        //System.out.print(teacher1.printSchedule());
        //System.out.print(course1.getRoom().printSchedule());
        assertEquals(1,course1.getRoom().getScheduleCourseNum());


        Course course2 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 60, CourseType.Lab);
        Classroom room2 = new Classroom(404, 60, building1, CourseType.Lab);
        course2.setRoomTime(room2, new CourseTime(Day.Monday,2));
        assertTrue(teacher1.createCourse(course2));
        assertEquals(2,teacher1.getScheduleCourseNum());
        assertFalse(teacher1.createCourse(course2));
        assertEquals(2,teacher1.getScheduleCourseNum());

        Course course3 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 60, CourseType.Lab);
        course3.setRoomTime(room2, new CourseTime(Day.Monday,3));
        assertTrue(teacher1.createCourse(course3));

        Classroom room3 = new Classroom(406, 60, building1, CourseType.Lab);
        course3.setRoomTime(room3, new CourseTime(Day.Monday,2));
        assertFalse(teacher1.createCourse(course3));
        assertEquals(3,teacher1.getScheduleCourseNum());

        course3.setRoomTime(room3, new CourseTime(Day.Tuesday,2));
        assertTrue(teacher1.createCourse(course3));
        assertEquals(4,teacher1.getScheduleCourseNum());

        teacher1.dropCourse(course1);
        assertEquals(3,teacher1.getScheduleCourseNum());
        assertEquals(2,course2.getRoom().getScheduleCourseNum());
        /*System.out.println(Db.buildings.size());
        for (Building building : Db.buildings) {
                if (building.getRooms().size()==0) {
                    System.out.println("no classrooms in "+building.toString());
                    continue;
                }
                for (Classroom classroom : building.getRooms( )) {
                    //if(classroom == null) continue;
                    System.out.println(classroom.toString());

                }

        }*/

        Teacher teacher3 = Db.GetTeacher("30000001");
        //System.out.print(teacher3.getName());
        //System.out.print(teacher3.printSchedule());
        assertEquals(2, teacher3.getScheduleCourseNum());

    }

    /**
     * This test can simply help you check if your program is correct, but note that this test does not include test for method {@code removeApplicant}.
     * <p>
     * Passing the local test does not guarantee that you will pass the OnlineJudge test
     */
    @Test
    public void test_05_Student() {
        Student student1 = new Student( "10000003" , "SZhang3" );

        Teacher teacher1 = new Teacher("3000001" , "TZHAO1");
        teacher1.setPreferLocation(Location.LycheePark);
        Course course1 = new Course("CS102A","Introduction to Computer Programming A", "JavaA", teacher1, 50, CourseType.Lab);
        Building building1 = new Building(Location.LycheePark, 1);
        Classroom room1 = new Classroom(402, 50, building1, CourseType.Lab);
        course1.setRoomTime(room1, new CourseTime(Day.Monday,1));
        assertTrue(teacher1.createCourse(course1));
        student1.chooseCourse(course1);

        student1.chooseCourse(Db.courses.get(0));
        student1.chooseCourse(Db.courses.get(1));// can only choose 1 course with same code, name and type
        assertEquals(2, student1.getScheduleCourseNum());
        //System.out.print(Db.courses.get(0).getStudents().size());
        assertEquals(1,Db.courses.get(0).getStudents().size());

        student1.dropCourse(Db.courses.get(0));
        assertEquals(0,Db.courses.get(0).getStudents().size());
        student1.chooseCourse(Db.courses.get(1));// can choose now

        student1.changeCourse(Db.courses.get(1), Db.courses.get(0));
        assertEquals(1,Db.courses.get(0).getStudents().size());

        //System.out.print(student1.printSchedule());
        //System.out.print(course1.getStudents().size());
        assertEquals(2, student1.getScheduleCourseNum());

        Student student2 = Db.GetStudent("10000001");
        //System.out.print(student2.getName());
        assert student2 != null;
        System.out.print(student2.getScheduleCourseNum());
    }

}

