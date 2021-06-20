import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class LocalJudgeA4Test {

    University SUSTC;


    @BeforeEach
    public void createData() {
        SUSTC = new ConcreteUniversity();
        String[] courseList = new String[]{
                "A 1 2",
                "B 1 3",
                "C 1 3",
                "D 0 3",
                "E 0 3",
                "F 0 2",
                "G 2 2",
                "H 2 3",
                "I 2 3",
                "J 2 2",
                "K 2 3",
                "L 2 2"
        };
        String[] studentList = new String[]{
                "1 1",
                "2 1 0.33 0.12",
                "3 1 0.31 0.11",
                "4 2",
                "5 2",
                "6 2 0.12 0.91",
                "7 3",
                "8 3 0.4 0.5",
                "9 3 0.1 0.3",
                "10 4 0.4 0.2",
                "11 4 0.22 0.91",
                "12 4 0.31 0.28",
                "13 5 0.23 0.32",
                "14 5 0.12 0.39",
                "15 5",
                "16 6",
                "17 6 0.12 0.67",
                "18 6"
        };
        String[] relationList = new String[]{
                "A",
                "A 1 B",
                "C D 1 B",
                "C",
                "A 1 D",
                "E",
                "F",
                "A E F 2 G",
                "E 1 H",
                "I",
                "A B C D E F G 4 J",
                "I 1 J",
                "J 1 K",
                "L I 1 K",
                "L",
        };
        for (String s : courseList) {
            SUSTC.addOneCourse(s);
        }
        for (String s : studentList) {
            SUSTC.addOneStudent(s);
        }
        SUSTC.addCourseRelations(new ArrayList<>(Arrays.asList(relationList)));
    }


    @Test
    void checkStudentClass() throws NoSuchFieldException, NoSuchMethodException {
        Class<Student> student = Student.class;
        assertEquals(Modifier.toString(student.getModifiers()), "public abstract", "Class student's modifiers should be public abstract");
        assertEquals(student.getDeclaredField("number").getType(), int.class, "Field number in class student should be int type");
        assertEquals(student.getDeclaredField("college").getType(), int.class, "Field college in class student should be int type");
        assertEquals(Modifier.toString(student.getDeclaredField("number").getModifiers()), "private", "Field number in class student should be private");
        assertEquals(Modifier.toString(student.getDeclaredField("college").getModifiers()), "private", "Field college in class student should be private");
        assertEquals(student.getDeclaredMethod("toString").getReturnType(), String.class, "Method toString in class student should return string value");
        assertEquals(student.getDeclaredMethod("checkGraduate").getReturnType(), boolean.class, "Method checkGraduate in class student should return boolean value");
        assertEquals(Modifier.toString(student.getDeclaredMethod("checkGraduate").getModifiers()), "public abstract", "Method checkGraduate in class student should be public abstract");

        Class<ArtsStudent> artStudent = ArtsStudent.class;
        assertEquals(Modifier.toString(artStudent.getModifiers()), "public", "Class ArtsStudent's modifiers should be public");
        assertEquals(artStudent.getDeclaredMethod("checkGraduate").getReturnType(), boolean.class, "Method checkGraduate in class ArtsStudent should return boolean value");
        assertEquals(Modifier.toString(artStudent.getDeclaredMethod("checkGraduate").getModifiers()), "public", "Method checkGraduate in class ArtsStudent should be public");
        assertEquals(artStudent.getDeclaredMethod("toString").getReturnType(), String.class, "Method toString in class ArtsStudent should return string value");

        Class<ScienceStudent> scienceStudent = ScienceStudent.class;
        assertEquals(Modifier.toString(scienceStudent.getModifiers()), "public", "Class ScienceStudent's modifiers should be public");
        assertEquals(scienceStudent.getDeclaredField("generalWeight").getType(), double.class, "Field generalWeight in class ScienceStudent should be double type");
        assertEquals(scienceStudent.getDeclaredField("artsWeight").getType(), double.class, "Field artsWeight in class ScienceStudent should be double type");
        assertEquals(Modifier.toString(scienceStudent.getDeclaredField("generalWeight").getModifiers()), "private", "Field generalWeight in class ScienceStudent should be private");
        assertEquals(Modifier.toString(scienceStudent.getDeclaredField("artsWeight").getModifiers()), "private", "Field artsWeight in class ScienceStudent should be private");
        assertEquals(scienceStudent.getDeclaredMethod("toString").getReturnType(), String.class, "Method toString in class ScienceStudent should return string value");
        assertEquals(scienceStudent.getDeclaredMethod("checkGraduate").getReturnType(), boolean.class, "Method checkGraduate in class ScienceStudent should return boolean value");
        assertEquals(Modifier.toString(scienceStudent.getDeclaredMethod("checkGraduate").getModifiers()), "public", "Method checkGraduate in class ScienceStudent should be public");

    }


    @Test
    void checkCourseClass() throws NoSuchFieldException, NoSuchMethodException {
        assertArrayEquals(CourseType.class.getEnumConstants(), new CourseType[]{CourseType.ARTS, CourseType.SCIENCE, CourseType.GENERAL}, "You need to check your enum value");
        Class<Course> course = Course.class;
        assertEquals(course.getDeclaredField("courseNumber").getType(), String.class, "Field courseNumber in class Course should be String");
        assertEquals(course.getDeclaredField("courseType").getType(), CourseType.class, "Field courseType in class Course should be CourseType type");
        assertEquals(course.getDeclaredField("credit").getType(), int.class, "Field credit in class Course should be int type");
        assertEquals(Modifier.toString(course.getDeclaredField("courseNumber").getModifiers()), "private", "Field courseNumber in class Course should be Private");
        assertEquals(Modifier.toString(course.getDeclaredField("courseType").getModifiers()), "private", "Field courseType in class Course should be Private");
        assertEquals(Modifier.toString(course.getDeclaredField("credit").getModifiers()), "private", "Field credit in class Course should be Private");
        assertEquals(course.getDeclaredMethod("toString").getReturnType(), String.class, "Method toString in class Course should return String value");
    }


    @Test
    void checkConcreteUniversityClass() throws NoSuchMethodException {
        Class<ConcreteUniversity> concreteUniversity = ConcreteUniversity.class;
        assertEquals(concreteUniversity.getDeclaredMethod("addOneCourse", String.class).getReturnType(), void.class, "Method addOneCourse in class concreteUniversity should return nothing");
        assertEquals(concreteUniversity.getDeclaredMethod("getAllCourses").getReturnType(), List.class, "Method getAllCourses in class concreteUniversity should return List value");
        assertEquals(concreteUniversity.getDeclaredMethod("addOneStudent", String.class).getReturnType(), void.class, "Method addOneStudent in class concreteUniversity should return nothing");
        assertEquals(concreteUniversity.getDeclaredMethod("getAllStudents").getReturnType(), List.class, "Method getAllStudents in class concreteUniversity should return List value");
        assertEquals(concreteUniversity.getDeclaredMethod("showArtsANDScienceCount").getReturnType(), String.class, "Method showArtsANDScienceCount in class concreteUniversity should return String value");
        assertEquals(concreteUniversity.getDeclaredMethod("addCourseRelations", List.class).getReturnType(), void.class, "Method addCourseRelations in class concreteUniversity should return nothing");
        assertEquals(concreteUniversity.getDeclaredMethod("selectCourse", int.class, String.class).getReturnType(), boolean.class, "Method selectCourse in class concreteUniversity should return boolean value");
        assertEquals(concreteUniversity.getDeclaredMethod("getCoursesOfOneStudentOrderByCourseNumber", int.class).getReturnType(), List.class, "Method getCoursesOfOneStudentOrderByCourseNumber in class concreteUniversity should return List value");
        assertEquals(concreteUniversity.getDeclaredMethod("checkGraduateForOneStudent", int.class).getReturnType(), boolean.class, "Method checkGraduateForOneStudent in class concreteUniversity should return boolean value");
        assertEquals(concreteUniversity.getDeclaredMethod("selectCourseByCollege", int.class, String.class).getReturnType(), void.class, "Method selectCourseByCollege in class concreteUniversity should return nothing");
    }


    @Test
    void testShowArtsAndScienceCount() {
        assertEquals(SUSTC.showArtsANDScienceCount(), "ARTS-7-SCIENCE-11", "The number of student in your statistic is wrong");
    }


    @Test
    void testAddStudent() {
        assertEquals(SUSTC.showArtsANDScienceCount(), "ARTS-7-SCIENCE-11", "The number of student in your statistic is wrong");
        SUSTC.addOneStudent("19 4");
        SUSTC.addOneStudent("20 4");
        SUSTC.addOneStudent("22 4 0.3 0.6");
        assertEquals(SUSTC.showArtsANDScienceCount(), "ARTS-9-SCIENCE-12", "Add student failed");
    }

    @Test
    void testAddCourse() throws NoSuchFieldException, IllegalAccessException {
        List<Course> course = SUSTC.getAllCourses();
        Field courseType = Course.class.getDeclaredField("courseType");
        Field courseCredit = Course.class.getDeclaredField("credit");
        courseType.setAccessible(true);
        courseCredit.setAccessible(true);
        assertEquals(course.size(), 12);
        int[] courseTypeNum = new int[3];
        int[] courseTypeCredit = new int[3];
        for (Course c : course) {
            courseTypeNum[((CourseType) courseType.get(c)).ordinal()] += 1;
            courseTypeCredit[((CourseType) courseType.get(c)).ordinal()] += (int) courseCredit.get(c);
        }
        assertArrayEquals(courseTypeNum, new int[]{3, 3, 6}, "The record of the course type is wrong");
        assertArrayEquals(courseTypeCredit, new int[]{8, 8, 15}, "The record of the credit is wrong");

        SUSTC.addOneCourse("M 1 4");
        SUSTC.addOneCourse("N 2 8");

        course = SUSTC.getAllCourses();
        assertEquals(course.size(), 14, "You haven't add all the courses");
        courseTypeNum = new int[3];
        courseTypeCredit = new int[3];
        for (Course c : course) {
            courseTypeNum[((CourseType) courseType.get(c)).ordinal()] += 1;
            courseTypeCredit[((CourseType) courseType.get(c)).ordinal()] += (int) courseCredit.get(c);
        }
        assertArrayEquals(courseTypeNum, new int[]{3, 4, 7}, "The record of the course type is wrong");
        assertArrayEquals(courseTypeCredit, new int[]{8, 12, 23}, "The record of the credit is wrong");
    }


    @Test
    void testSelectCourse1() throws NoSuchFieldException, IllegalAccessException {
        Field studentNum = Student.class.getDeclaredField("number");
        studentNum.setAccessible(true);
        Student student = SUSTC.getAllStudents().get(0);
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "B"), "Don't satisfy course selection conditions");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "A"), "Course selection conditions are been satisfied");
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "B"), "Don't satisfy course selection conditions");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "D"),"Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "B"),"Course selection conditions are been satisfied");
        Field studentCourses = Student.class.getDeclaredField("courses");
        Field courseNum = Course.class.getDeclaredField("courseNumber");
        studentCourses.setAccessible(true);
        courseNum.setAccessible(true);
        List<Course> courses = (List<Course>) studentCourses.get(student);
        List<String> courseStrings = new ArrayList<String>();
        for (Course c : courses)
            courseStrings.add((String) courseNum.get(c));
        for (String s : new String[]{"A", "D", "B"}) {
            assertTrue(courseStrings.contains(s), "The course which is selected but not appear in the course list");
        }
        for (String s : courseStrings) {
            assertTrue(Arrays.asList(new String[]{"A", "D", "B"}).contains(s), "The course which isn't selected but appear in the course list");
        }
    }

    @Test
    void testSelectCourse2() throws NoSuchFieldException, IllegalAccessException {
        Field studentNum = Student.class.getDeclaredField("number");
        studentNum.setAccessible(true);
        Student student = SUSTC.getAllStudents().get(0);
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "J"), "Don't satisfy course selection conditions");
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "G"), "Don't satisfy course selection conditions");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "A"), "Course selection conditions are been satisfied");
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "A"), "Don't satisfy course selection conditions");
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "B"), "Don't satisfy course selection conditions");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "C"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "B"), "Course selection conditions are been satisfied");
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "G"), "Don't satisfy course selection conditions");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "E"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "G"), "Course selection conditions are been satisfied");
        assertFalse(SUSTC.selectCourse((int) studentNum.get(student), "J"), "Don't satisfy course selection conditions");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "I"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "J"), "Course selection conditions are been satisfied");
        Field studentCourses = Student.class.getDeclaredField("courses");
        Field courseNum = Course.class.getDeclaredField("courseNumber");
        studentCourses.setAccessible(true);
        courseNum.setAccessible(true);
        List<Course> courses = (List<Course>) studentCourses.get(student);
        List<String> courseStrings = new ArrayList<String>();
        for (Course c : courses)
            courseStrings.add((String) courseNum.get(c));
        for (String s : new String[]{"A", "B", "C", "E", "G", "J", "I"}) {
            assertTrue(courseStrings.contains(s), "The course which is selected but not appear in the course list");
        }
        for (String s : courseStrings) {
            assertTrue(Arrays.asList(new String[]{"A", "B", "C", "E", "G", "J", "I"}).contains(s), "The course which isn't selected but appear in the course list");
        }
    }


    @Test
    void testSelectCollageCourse() throws NoSuchFieldException, IllegalAccessException {
        Field studentCourses = Student.class.getDeclaredField("courses");
        Field courseNum = Course.class.getDeclaredField("courseNumber");
        Field studentCollege = Student.class.getDeclaredField("college");
        studentCourses.setAccessible(true);
        courseNum.setAccessible(true);
        studentCollege.setAccessible(true);
        for (Student s : SUSTC.getAllStudents()) {
            assertEquals(((List<Course>) studentCourses.get(s)).size(), 0, "The students haven't selected course but there are course in there course list");
        }
        SUSTC.selectCourseByCollege(3, "A");
        for (Student s : SUSTC.getAllStudents()) {
            List<Course> courses = (List<Course>) studentCourses.get(s);
            List<String> courseStrings = new ArrayList<String>();
            for (Course c : courses)
                courseStrings.add((String) courseNum.get(c));
            if ((int) studentCollege.get(s) != 3) {
                assertFalse(courseStrings.contains("A"), "Haven't chosen course for the student in this collage but the course is in their course list");
            } else {
                assertTrue(courseStrings.contains("A"), "Have chosen course for the student in this collage but that course isn't in their course list");
            }
        }
    }


    @Test
    void testGetCourseOfOneStudentOrderByCourseNumber() throws IllegalAccessException, NoSuchFieldException {
        Field studentNum = Student.class.getDeclaredField("number");
        Field courseNum = Course.class.getDeclaredField("courseNumber");
        studentNum.setAccessible(true);
        courseNum.setAccessible(true);
        Student student = SUSTC.getAllStudents().get(0);
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "A"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "C"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "B"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "E"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "G"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "I"), "Course selection conditions are been satisfied");
        assertTrue(SUSTC.selectCourse((int) studentNum.get(student), "J"), "Course selection conditions are been satisfied");
        List<Course> courses = SUSTC.getCoursesOfOneStudentOrderByCourseNumber((int) studentNum.get(student));
        List<String> courseStrings = new ArrayList<String>();
        for (Course c : courses)
            courseStrings.add((String) courseNum.get(c));
        assertArrayEquals(courseStrings.toArray(), new String[]{"A", "B", "C", "E", "G", "I", "J"}, "The course list you returned isn't match the courses we selected, or you return the list in incorrect order");
    }


    @Test
    void testCheckGraduateForOneStudent1() {
        University SUSTech = new ConcreteUniversity();
        String[] courseList = new String[]{
                "A 1 4",
                "B 1 3",
                "C 1 3",
                "D 0 3",
                "E 0 8",
                "F 0 2",
                "G 2 2",
                "H 2 3",
                "I 2 4",
                "J 2 3",
                "K 2 3",
                "L 2 0"
        };
        String[] relationList = new String[]{
                "A",
                "A 1 B",
                "C D 1 B",
                "C",
                "A 1 D",
                "E",
                "F",
                "A E F 2 G",
                "E 1 H",
                "I",
                "A B C D E F G 4 J",
                "I 1 J",
                "J 1 K",
                "L I 1 K",
                "L",
        };
        for (String s : courseList) {
            SUSTech.addOneCourse(s);
        }
        SUSTech.addCourseRelations(new ArrayList<>(Arrays.asList(relationList)));
        SUSTech.addOneStudent("1 1");
        assertFalse(SUSTech.checkGraduateForOneStudent(1), "Student have no credit");
        assertTrue(SUSTech.selectCourse(1, "A"), "Course selection conditions are been satisfied");
        assertFalse(SUSTech.checkGraduateForOneStudent(1), "Student don't have enough art credit and general credit");
        assertTrue(SUSTech.selectCourse(1, "E"), "Course selection conditions are been satisfied");
        assertFalse(SUSTech.checkGraduateForOneStudent(1), "Student don't have enough general credit");
        assertTrue(SUSTech.selectCourse(1, "I"), "Course selection conditions are been satisfied");
        assertTrue(SUSTech.checkGraduateForOneStudent(1), "Student have enough credit");
    }

    @Test
    void testCheckGraduateForOneStudent2() {
        University SUSTech = new ConcreteUniversity();
        String[] courseList = new String[]{
                "A 1 4",
                "B 1 3",
                "C 1 3",
                "D 0 3",
                "E 0 8",
                "F 0 2",
                "G 2 2",
                "H 2 3",
                "I 2 8",
                "J 2 3",
                "K 2 3",
                "L 2 0"
        };
        String[] relationList = new String[]{
                "A",
                "A 1 B",
                "C D 1 B",
                "C",
                "A 1 D",
                "E",
                "F",
                "A E F 2 G",
                "E 1 H",
                "I",
                "A B C D E F G 4 J",
                "I 1 J",
                "J 1 K",
                "L I 1 K",
                "L",
        };
        for (String s : courseList) {
            SUSTech.addOneCourse(s);
        }
        SUSTech.addCourseRelations(new ArrayList<>(Arrays.asList(relationList)));
        SUSTech.addOneStudent("1 1 0.6 0.3");
        assertFalse(SUSTech.checkGraduateForOneStudent(1), "Student have no credit");
        assertTrue(SUSTech.selectCourse(1, "A"), "Course selection conditions are been satisfied");
        assertFalse(SUSTech.checkGraduateForOneStudent(1), "Student don't have enough weighted credit");
        assertTrue(SUSTech.selectCourse(1, "E"), "Course selection conditions are been satisfied");
        assertFalse(SUSTech.checkGraduateForOneStudent(1), "Student don't have enough weighted credit");
        assertTrue(SUSTech.selectCourse(1, "I"), "Course selection conditions are been satisfied");
        assertFalse(SUSTech.checkGraduateForOneStudent(1), "Student have enough weighted credit, but don't select enough course");
        assertTrue(SUSTech.selectCourse(1, "L"), "Course selection conditions are been satisfied");
        assertTrue(SUSTech.checkGraduateForOneStudent(1), "Student have enough weighted credit and select enough course");
    }


}
