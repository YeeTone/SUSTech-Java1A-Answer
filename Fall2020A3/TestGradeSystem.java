package Fall2020A3;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestGradeSystem {

    private static Class<?> gradeSystemClass;

    @BeforeAll
    static void beforeAll() {
        try {
            gradeSystemClass = Class.forName("Fall2020A3.GradeSystem");
        } catch (ClassNotFoundException e) {
            fail(e.toString());
        }
    }

    @Order(0)
    @Test
    void test00() throws Exception {
        try {
            Class<?> courseClass = Class.forName("Fall2020A3.Course");
            Class<?> studentClass = Class.forName("Fall2020A3.Student");
            Class<?> gradeClass = Class.forName("Fall2020A3.Grade");
            Method methodAddCourse = gradeSystemClass.getMethod("addCourse", courseClass);
            Method methodAddStudent = gradeSystemClass.getMethod("addStudent", studentClass);
            Method methodAddGrade = gradeSystemClass.getMethod("addGrade", gradeClass);
            Method methodGetGradeList = gradeSystemClass.getMethod("getGradeList");
            Constructor<?> gradeInit = gradeClass.getConstructor(courseClass, studentClass, float.class);

            Object gs = gradeSystemClass.newInstance();
            Object c1 = courseClass.getConstructor(String.class).newInstance("JavaA");
            Object s1 = studentClass.getConstructor(String.class).newInstance("Zhangyi");
            methodAddCourse.invoke(gs, c1);
            methodAddStudent.invoke(gs, s1);
            methodAddGrade.invoke(gs, gradeInit.newInstance(c1, s1, 30f));
            methodAddGrade.invoke(gs, gradeInit.newInstance(c1, s1, 70f));

            assertEquals("Student: Zhangyi, sid: 1", s1.toString(), "Student.toString() is incorrect!");
            assertEquals("Course: JavaA, cid: 1", c1.toString(), "Course.toString() is incorrect!");
            assertEquals("sid: 1, cid: 1, grade: 70.0, gpa: 2.42", ((ArrayList<?>) methodGetGradeList.invoke(gs)).get(0).toString(), "Grade.toString() is incorrect!");
        } catch (ClassCastException e) {
            fail("ClassCastException! Return type of GradeSystem.getGradeList() may be incorrect.");
        } catch (ClassNotFoundException e) {
            fail("Missing class! Please check whether you have submitted all required files.");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check the existence, signature, and accessibility!");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code!");
        }
    }

    @Order(1)
    @Test
    void test01() throws Exception {
        String[] fieldsExpected = {
                "private java.util.ArrayList Fall2020A3.GradeSystem.studentList",
                "private java.util.ArrayList Fall2020A3.GradeSystem.courseList",
                "private java.util.ArrayList Fall2020A3.GradeSystem.gradeList"
        };
        String[] methodsExpected = {};
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);
    }

    @Order(2)
    @Test
    void test02() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {
                "public java.util.ArrayList Fall2020A3.GradeSystem.getStudentList()",
                "public java.util.ArrayList Fall2020A3.GradeSystem.getGradeList()",
                "public java.util.ArrayList Fall2020A3.GradeSystem.getCourseList()"
        };
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);
    }

    @Order(3)
    @Test
    void test03() throws Exception {
        try {
            Field fieldStudentList = gradeSystemClass.getDeclaredField("studentList");
            Field fieldCourseList = gradeSystemClass.getDeclaredField("courseList");
            Field fieldGradeList = gradeSystemClass.getDeclaredField("gradeList");

            fieldStudentList.setAccessible(true);
            fieldCourseList.setAccessible(true);
            fieldGradeList.setAccessible(true);

            Object g = gradeSystemClass.newInstance();

            assertEquals(0, ((ArrayList<?>) fieldStudentList.get(g)).size(), "studentList's size is incorrect!");
            assertEquals(0, ((ArrayList<?>) fieldGradeList.get(g)).size(), "gradeList's size is incorrect!");
            assertEquals(0, ((ArrayList<?>) fieldCourseList.get(g)).size(), "courseList's size is incorrect!");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (ClassCastException e) {
            fail("ClassCastException! Type of GradeSystem.studentList/gradeList/courseList may be incorrect!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code");
        }
    }

    @Order(4)
    @Test
    void test04() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {
                "public boolean Fall2020A3.GradeSystem.checkStudent(int)",
                "public boolean Fall2020A3.GradeSystem.addStudent(Fall2020A3.Student)"
        };
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);

        try {
            Class<?> studentClass = Class.forName("Fall2020A3.Student");
            Constructor<?> studentInit = studentClass.getConstructor(String.class);
            Method methodAddStudent = gradeSystemClass.getMethod("addStudent", studentClass);
            Method methodCheckStudent = gradeSystemClass.getMethod("checkStudent", int.class);
            Method methodGetStudentList = gradeSystemClass.getMethod("getStudentList");
            Method methodGetSid = studentClass.getMethod("getSid");
            Object g = gradeSystemClass.newInstance();
            Object s1 = studentInit.newInstance("Zhangyi");
            Object s2 = studentInit.newInstance("Zhanger");
            Object s3 = studentInit.newInstance("Zhangsan");

            assertTrue((boolean) methodAddStudent.invoke(g, s1), "addStudent is incorrect!");
            assertFalse((boolean) methodCheckStudent.invoke(g, methodGetSid.invoke(s2)), "checkStudent is incorrect!");
            assertTrue((boolean) methodAddStudent.invoke(g, s2), "addStudent is incorrect!");
            assertTrue((boolean) methodAddStudent.invoke(g, s3), "addStudent is incorrect!");
            assertFalse((boolean) methodAddStudent.invoke(g, s2), "addStudent is incorrect!");

            boolean hasStudent1 = (boolean) methodCheckStudent.invoke(g, methodGetSid.invoke(s1));
            boolean hasStudent2 = (boolean) methodCheckStudent.invoke(g, methodGetSid.invoke(s2));
            boolean hasStudent3 = (boolean) methodCheckStudent.invoke(g, methodGetSid.invoke(s3));

            assertTrue(hasStudent1 && hasStudent2 && hasStudent3, "Missing students!");
            assertEquals(3, ((ArrayList<?>) methodGetStudentList.invoke(g)).size(), "StudentList is incorrect!");
        } catch (ClassCastException e) {
            fail("ClassCastException! Please check the fields' types and methods' return types");
        } catch (ClassNotFoundException e) {
            fail("Missing class Student! Please check whether your have submitted Student.java.");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check the existence, signature, and accessibility.");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code");
        }
    }

    @Order(5)
    @Test
    void test05() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {
                "public boolean Fall2020A3.GradeSystem.checkCourse(int)",
                "public boolean Fall2020A3.GradeSystem.addCourse(Fall2020A3.Course)"
        };
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);

        try {
            Class<?> courseClass = Class.forName("Fall2020A3.Course");
            Constructor<?> courseInit = courseClass.getConstructor(String.class);
            Method methodAddCourse = gradeSystemClass.getMethod("addCourse", courseClass);
            Method methodCheckCourse = gradeSystemClass.getMethod("checkCourse", int.class);
            Method methodGetCourseList = gradeSystemClass.getMethod("getCourseList");
            Method methodGetCid = courseClass.getMethod("getCid");

            Object gs = gradeSystemClass.newInstance();
            Object c1 = courseInit.newInstance("JavaA");
            Object c2 = courseInit.newInstance("JavaB");
            Object c3 = courseInit.newInstance("JavaH");

            assertTrue((boolean) methodAddCourse.invoke(gs, c1), "addCourse is incorrect!");
            assertFalse((boolean) methodCheckCourse.invoke(gs, methodGetCid.invoke(c2)), "checkCourse is incorrect!");
            assertTrue((boolean) methodAddCourse.invoke(gs, c2), "addCourse is incorrect!");
            assertTrue((boolean) methodAddCourse.invoke(gs, c3), "addCourse is incorrect!");
            assertFalse((boolean) methodAddCourse.invoke(gs, c2), "addCourse is incorrect!");

            boolean hasCourse1 = (boolean) methodCheckCourse.invoke(gs, methodGetCid.invoke(c1));
            boolean hasCourse2 = (boolean) methodCheckCourse.invoke(gs, methodGetCid.invoke(c2));
            boolean hasCourse3 = (boolean) methodCheckCourse.invoke(gs, methodGetCid.invoke(c3));
            assertTrue(hasCourse1 && hasCourse2 && hasCourse3, "Missing courses!");
            assertEquals(3, ((ArrayList<?>) methodGetCourseList.invoke(gs)).size(), "CourseList is incorrect!");
        } catch (ClassCastException e) {
            fail("ClassCastException! Please check the fields' types and methods' return types");
        } catch (ClassNotFoundException e) {
            fail("Missing class Course! Please check whether you have submitted the Course.java.");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check their existence and accessibility.");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code!");
        }
    }

    @Order(6)
    @Test
    void test06() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {"public boolean Fall2020A3.GradeSystem.addGrade(Fall2020A3.Grade)"};
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);

        try {
            Class<?> courseClass = Class.forName("Fall2020A3.Course");
            Class<?> studentClass = Class.forName("Fall2020A3.Student");
            Class<?> gradeClass = Class.forName("Fall2020A3.Grade");
            Constructor<?> courseInit = courseClass.getConstructor(String.class);
            Constructor<?> studentInit = studentClass.getConstructor(String.class);
            Constructor<?> gradeInit = gradeClass.getConstructor(courseClass, studentClass, float.class);
            Method methodAddGrade = gradeSystemClass.getMethod("addGrade", gradeClass);
            Method methodAddCourse = gradeSystemClass.getMethod("addCourse", courseClass);
            Method methodAddStudent = gradeSystemClass.getMethod("addStudent", studentClass);
            Method methodGetGradeList = gradeSystemClass.getMethod("getGradeList");
            Method methodGetGrade = gradeClass.getMethod("getGrade");
            Method methodGetGpa = gradeClass.getMethod("getGpa");

            Object gs = gradeSystemClass.newInstance();
            Object c1 = courseInit.newInstance("JavaA");
            Object c2 = courseInit.newInstance("JavaB");
            Object c3 = courseInit.newInstance("JavaH");
            Object s1 = studentInit.newInstance("Zhangyi");
            Object s2 = studentInit.newInstance("Zhanger");
            Object s3 = studentInit.newInstance("Zhangsan");
            Object g11 = gradeInit.newInstance(c1, s1, 80f);
            Object g12 = gradeInit.newInstance(c1, s2, 30f);
            Object g13 = gradeInit.newInstance(c1, s2, 40f);
            Object g14 = gradeInit.newInstance(c1, s2, 60f);
            Object g15 = gradeInit.newInstance(c1, s2, 70f);
            Object g22 = gradeInit.newInstance(c2, s2, 60f);
            Object g33 = gradeInit.newInstance(c3, s3, 77f);

            assertFalse((boolean) methodAddGrade.invoke(gs, g11), "addGrade is incorrect!");
            methodAddCourse.invoke(gs, c1);
            assertFalse((boolean) methodAddGrade.invoke(gs, g11), "addGrade is incorrect!");
            methodAddStudent.invoke(gs, s1);
            methodAddStudent.invoke(gs, s2);
            assertTrue((boolean) methodAddGrade.invoke(gs, g11), "addGrade is incorrect!");
            assertTrue((boolean) methodAddGrade.invoke(gs, g12), "addGrade is incorrect!");
            assertFalse((boolean) methodAddGrade.invoke(gs, g13), "addGrade is incorrect!");
            Object grade1 = ((ArrayList<?>) methodGetGradeList.invoke(gs)).get(1);
            assertEquals(30f, methodGetGrade.invoke(grade1), "addGrade is incorrect!");
            assertEquals(0f, methodGetGpa.invoke(grade1), "addGrade is incorrect!");

            assertFalse((boolean) methodAddGrade.invoke(gs, g22), "addGrade is incorrect!");
            methodAddCourse.invoke(gs, c2);
            assertTrue((boolean) methodAddGrade.invoke(gs, g22), "addGrade is incorrect!");
            methodAddCourse.invoke(gs, c3);
            methodAddStudent.invoke(gs, s3);
            assertTrue((boolean) methodAddGrade.invoke(gs, g33), "addGrade is incorrect!");
            assertTrue((boolean) methodAddGrade.invoke(gs, g14), "addGrade is incorrect!");
            assertFalse((boolean) methodAddGrade.invoke(gs, g15), "addGrade is incorrect!");
            Object grade2 = ((ArrayList<?>) methodGetGradeList.invoke(gs)).get(1);
            assertEquals(60f, methodGetGrade.invoke(grade2), "addGrade is incorrect!");
            assertEquals(1.15f, methodGetGpa.invoke(grade2), "addGrade is incorrect!");
            ArrayList<?> grades = (ArrayList<?>) methodGetGradeList.invoke(gs);
            assertTrue(grades.contains(g11) && grades.contains(g12) && grades.contains(g22) && grades.contains(g33), "Missing grades!");
            assertEquals(4, grades.size(), "gradeList is incorrect!");
        } catch (ClassNotFoundException e) {
            fail("Missing class! Please check whether you have submitted all required files.");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check their existence and accessibility.");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception");
        } catch (ClassCastException e) {
            fail("ClassCastException! Please check the fields' types and methods' return types");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code!");
        }
    }

    @Order(7)
    @Test
    void test07() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {"public java.util.ArrayList Fall2020A3.GradeSystem.listCouGrade(int)"};
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);

        try {
            Class<?> courseClass = Class.forName("Fall2020A3.Course");
            Class<?> studentClass = Class.forName("Fall2020A3.Student");
            Class<?> gradeClass = Class.forName("Fall2020A3.Grade");
            Constructor<?> courseInit = courseClass.getConstructor(String.class);
            Constructor<?> studentInit = studentClass.getConstructor(String.class);
            Constructor<?> gradeInit = gradeClass.getConstructor(courseClass, studentClass, float.class);
            Method methodAddCourse = gradeSystemClass.getMethod("addCourse", courseClass);
            Method methodAddStudent = gradeSystemClass.getMethod("addStudent", studentClass);
            Method methodAddGrade = gradeSystemClass.getMethod("addGrade", gradeClass);
            Method methodListStuGrade = gradeSystemClass.getMethod("listStuGrade", int.class);
            Method methodGetSid = studentClass.getMethod("getSid");
            Field fieldGradeList = gradeSystemClass.getDeclaredField("gradeList");
            fieldGradeList.setAccessible(true);

            Object gs = gradeSystemClass.newInstance();
            Object c1 = courseInit.newInstance("JavaA");
            Object c2 = courseInit.newInstance("JavaB");
            Object c3 = courseInit.newInstance("JavaH");
            Object s1 = studentInit.newInstance("Zhangyi");
            Object s2 = studentInit.newInstance("Zhanger");
            Object s3 = studentInit.newInstance("Zhangsan");
            Object g11 = gradeInit.newInstance(c3, s1, 80f);
            Object g12 = gradeInit.newInstance(c1, s2, 30f);
            Object g22 = gradeInit.newInstance(c2, s2, 60f);
            Object g33 = gradeInit.newInstance(c3, s2, 77f);

            methodAddCourse.invoke(gs, c2);
            methodAddCourse.invoke(gs, c1);
            methodAddStudent.invoke(gs, s1);
            methodAddStudent.invoke(gs, s2);
            methodAddCourse.invoke(gs, c3);
            methodAddStudent.invoke(gs, s3);
            methodAddGrade.invoke(gs, g33);
            methodAddGrade.invoke(gs, g11);
            methodAddGrade.invoke(gs, g22);
            methodAddGrade.invoke(gs, g12);

            ArrayList<?> test = (ArrayList<?>) methodListStuGrade.invoke(gs, methodGetSid.invoke(s2));
            ArrayList<?> gradeList = (ArrayList<?>) fieldGradeList.get(gs);
            assertTrue(test.get(0) == gradeList.get(2) && test.get(1) == gradeList.get(3) && test.get(2) == gradeList.get(0), "listStuGrade is incorrect!");
            assertEquals(3, test.size(), "listStuGrade is incorrect!");
            test = (ArrayList<?>) methodListStuGrade.invoke(gs, methodGetSid.invoke(s1));
            assertSame(test.get(0), gradeList.get(1), "listStuGrade is incorrect!");
            assertEquals(1, test.size(), "listStuGrade is incorrect!");
            test = (ArrayList<?>) methodListStuGrade.invoke(gs, methodGetSid.invoke(s3));
            assertEquals(0, test.size(), "listStuGrade is incorrect!");
        } catch (ClassNotFoundException e) {
            fail("Missing class! Please check whether you have submitted all required files.");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check their existence and accessibility.");
        } catch (NoSuchFieldException e) {
            fail("Missing attributes!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code!");
        } catch (ClassCastException e) {
            fail("ClassCastException! Please check the fields' types and methods' return types.");
        }
    }

    @Order(8)
    @Test
    void test08() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {"public java.util.ArrayList Fall2020A3.GradeSystem.listStuGrade(int)"};
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);

        try {
            Class<?> courseClass = Class.forName("Fall2020A3.Course");
            Class<?> studentClass = Class.forName("Fall2020A3.Student");
            Class<?> gradeClass = Class.forName("Fall2020A3.Grade");
            Constructor<?> courseInit = courseClass.getConstructor(String.class);
            Constructor<?> studentInit = studentClass.getConstructor(String.class);
            Constructor<?> gradeInit = gradeClass.getConstructor(courseClass, studentClass, float.class);
            Method methodAddGrade = gradeSystemClass.getMethod("addGrade", gradeClass);
            Method methodAddCourse = gradeSystemClass.getMethod("addCourse", courseClass);
            Method methodAddStudent = gradeSystemClass.getMethod("addStudent", studentClass);
            Method methodGetGradeList = gradeSystemClass.getMethod("getGradeList");
            Method methodListCouGrade = gradeSystemClass.getMethod("listCouGrade", int.class);
            Method methodGetGrade = gradeClass.getMethod("getGrade");
            Method methodGetGpa = gradeClass.getMethod("getGpa");
            Method methodGetCid = courseClass.getMethod("getCid");
            Field fieldGradeList = gradeSystemClass.getDeclaredField("gradeList");
            fieldGradeList.setAccessible(true);

            Object gs = gradeSystemClass.newInstance();
            Object c1 = courseInit.newInstance("JavaA");
            Object c2 = courseInit.newInstance("JavaB");
            Object c3 = courseInit.newInstance("JavaH");
            Object s1 = studentInit.newInstance("Zhangyi");
            Object s2 = studentInit.newInstance("Zhanger");
            Object s3 = studentInit.newInstance("Zhangsan");
            Object g11 = gradeInit.newInstance(c1, s1, 80f);
            Object g12 = gradeInit.newInstance(c1, s2, 30f);
            Object g13 = gradeInit.newInstance(c1, s2, 40f);
            Object g14 = gradeInit.newInstance(c1, s2, 60f);
            Object g15 = gradeInit.newInstance(c1, s2, 70f);
            Object g22 = gradeInit.newInstance(c2, s2, 60f);
            Object g33 = gradeInit.newInstance(c1, s3, 77f);

            assertFalse((boolean) methodAddGrade.invoke(gs, g11), "addGrade is incorrect!");
            methodAddCourse.invoke(gs, c1);
            assertFalse((boolean) methodAddGrade.invoke(gs, g11), "addGrade is incorrect!");
            methodAddStudent.invoke(gs, s1);
            methodAddStudent.invoke(gs, s2);
            assertTrue((boolean) methodAddGrade.invoke(gs, g11), "addGrade is incorrect!");
            assertTrue((boolean) methodAddGrade.invoke(gs, g12), "addGrade is incorrect!");
            assertFalse((boolean) methodAddGrade.invoke(gs, g13), "addGrade is incorrect!");
            ArrayList<?> grades1 = (ArrayList<?>) methodGetGradeList.invoke(gs);
            assertEquals(30f, methodGetGrade.invoke(grades1.get(1)), "addGrade is incorrect!");
            assertEquals(0f, methodGetGpa.invoke(grades1.get(1)), "addGrade is incorrect!");

            assertFalse((boolean) methodAddGrade.invoke(gs, g22), "addGrade is incorrect!");
            methodAddCourse.invoke(gs, c2);
            assertTrue((boolean) methodAddGrade.invoke(gs, g22), "addGrade is incorrect!");
            methodAddCourse.invoke(gs, c3);
            methodAddStudent.invoke(gs, s3);
            assertTrue((boolean) methodAddGrade.invoke(gs, g33), "addGrade is incorrect!");
            assertTrue((boolean) methodAddGrade.invoke(gs, g14), "addGrade is incorrect!");
            assertFalse((boolean) methodAddGrade.invoke(gs, g15), "addGrade is incorrect!");
            ArrayList<?> grades2 = (ArrayList<?>) methodGetGradeList.invoke(gs);
            assertEquals(60f, methodGetGrade.invoke(grades2.get(1)), "addGrade is incorrect!");
            assertEquals(1.15f, methodGetGpa.invoke(grades2.get(1)), "addGrade is incorrect!");

            assertTrue(grades2.contains(g11) && grades2.contains(g12) && grades2.contains(g22) && grades2.contains(g33), "Missing courses!");
            assertEquals(4, grades2.size(), "CourseList is incorrect!");

            ArrayList<?> test = (ArrayList<?>) methodListCouGrade.invoke(gs, methodGetCid.invoke(c1));

            assertTrue(test.get(0) == grades2.get(0) && test.get(1) == grades2.get(1), "listCouGrade is incorrect!");
            assertEquals(3, test.size(), "listCouGrade is incorrect!");
            test = (ArrayList<?>) methodListCouGrade.invoke(gs, methodGetCid.invoke(c2));
            assertSame(test.get(0), ((ArrayList<?>) fieldGradeList.get(gs)).get(2), "listCouGrade is incorrect!");
            assertEquals(1, test.size(), "listCouGrade is incorrect!");
            test = (ArrayList<?>) methodListCouGrade.invoke(gs, methodGetCid.invoke(c3));
            assertEquals(0, test.size(), "listCouGrade is incorrect!");
        } catch (ClassCastException e) {
            fail("ClassCastException!");
        } catch (NoSuchFieldException e) {
            fail("Missing attributes!");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check the existence, signature, and accessibility.");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code!");
        }
    }

    @Order(9)
    @Test
    void test09() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {
                "public float Fall2020A3.GradeSystem.average(int)",
                "public float Fall2020A3.GradeSystem.gpa(int)"
        };
        Utils.checkClass(gradeSystemClass, fieldsExpected, methodsExpected);

        try {
            Class<?> courseClass = Class.forName("Fall2020A3.Course");
            Class<?> studentClass = Class.forName("Fall2020A3.Student");
            Class<?> gradeClass = Class.forName("Fall2020A3.Grade");
            Constructor<?> courseInit = courseClass.getConstructor(String.class);
            Constructor<?> studentInit = studentClass.getConstructor(String.class);
            Constructor<?> gradeInit = gradeClass.getConstructor(courseClass, studentClass, float.class);
            Method methodAddStudent = gradeSystemClass.getMethod("addStudent", studentClass);
            Method methodAddCourse = gradeSystemClass.getMethod("addCourse", courseClass);
            Method methodAddGrade = gradeSystemClass.getMethod("addGrade", gradeClass);
            Method methodAverage = gradeSystemClass.getMethod("average", int.class);
            Method methodGetGradeList = gradeSystemClass.getMethod("getGradeList");
            Method methodGpa = gradeSystemClass.getMethod("gpa", int.class);
            Method methodGetCid = courseClass.getMethod("getCid");
            Method methodGetGpa = gradeClass.getMethod("getGpa");
            Method methodGetSid = studentClass.getMethod("getSid");

            Object g = gradeSystemClass.newInstance();
            Object c1 = courseInit.newInstance("JavaA");
            Object c2 = courseInit.newInstance("JavaB");
            Object c3 = courseInit.newInstance("JavaH");
            Object c4 = courseInit.newInstance("DSAA");
            Object s1 = studentInit.newInstance("Zhangyi");
            Object s2 = studentInit.newInstance("Zhanger");
            Object s3 = studentInit.newInstance("Zhangsan");
            Object s4 = studentInit.newInstance("Zhangsi");
            methodAddStudent.invoke(g, s1);
            methodAddStudent.invoke(g, s2);
            methodAddStudent.invoke(g, s3);
            methodAddCourse.invoke(g, c1);
            methodAddCourse.invoke(g, c2);
            methodAddCourse.invoke(g, c3);
            methodAddGrade.invoke(g, gradeInit.newInstance(c1, s1, 10f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c1, s2, 20f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c1, s3, 30f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c2, s1, 40f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c2, s2, 50f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c2, s3, 60f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c3, s1, 70f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c3, s2, 80f));
            methodAddGrade.invoke(g, gradeInit.newInstance(c3, s3, 90f));

            assertEquals(20f, methodAverage.invoke(g, methodGetCid.invoke(c1)), "average() is incorrect!");
            assertEquals(50f, methodAverage.invoke(g, methodGetCid.invoke(c2)), "average() is incorrect!");
            assertEquals(80f, methodAverage.invoke(g, methodGetCid.invoke(c3)), "average() is incorrect!");
            assertEquals(0f, methodAverage.invoke(g, methodGetCid.invoke(c4)), "average() is incorrect!");

            ArrayList<?> gradeList = (ArrayList<?>) methodGetGradeList.invoke(g);
            assertEquals(((float) methodGetGpa.invoke(gradeList.get(0)) + (float) methodGetGpa.invoke(gradeList.get(3)) + (float) methodGetGpa.invoke(gradeList.get(6))) / 3, methodGpa.invoke(g, methodGetSid.invoke(s1)), "gpa() is incorrect!");
            assertEquals(((float) methodGetGpa.invoke(gradeList.get(1)) + (float) methodGetGpa.invoke(gradeList.get(4)) + (float) methodGetGpa.invoke(gradeList.get(7))) / 3, methodGpa.invoke(g, methodGetSid.invoke(s2)), "gpa() is incorrect!");
            assertEquals(((float) methodGetGpa.invoke(gradeList.get(2)) + (float) methodGetGpa.invoke(gradeList.get(5)) + (float) methodGetGpa.invoke(gradeList.get(8))) / 3, methodGpa.invoke(g, methodGetSid.invoke(s3)), "gpa() is incorrect!");
            assertEquals(0f, methodGpa.invoke(g, methodGetSid.invoke(s4)), "gpa() is incorrect!");
        } catch (ClassCastException e) {
            fail("ClassCastException! Please check the fields' types and methods' return types.");
        } catch (ClassNotFoundException e) {
            fail("Missing class! Please check whether you have submitted all required files.");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method! Please check the existence, signature, and accessibility.");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code!");
        }
    }

}

