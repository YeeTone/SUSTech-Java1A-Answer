import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Timeout(3)
public class TestGrade {
    private static Class<?> gradeClass;

    @BeforeAll
    static void beforeAll() {
        try {
            gradeClass = Class.forName("Grade");
        } catch (ClassNotFoundException e) {
            fail(e.toString());
        }
    }

    @Order(1)
    @Test
    void test01() throws Exception {
        String[] fieldsExpected = {"private Course Grade.course"};
        String[] methodsExpected = {"public Course Grade.getCourse()"};
        String[] methodsUnexpected = {"public void Grade.setCourse(Course)"};
        Utils.checkClass(gradeClass, fieldsExpected, methodsExpected, methodsUnexpected);
    }

    @Order(2)
    @Test
    void test02() throws Exception {
        String[] fieldsExpected = {"private Student Grade.student"};
        String[] methodsExpected = {"public Student Grade.getStudent()"};
        String[] methodsUnexpected = {"public void Grade.setStudent(Student)"};
        Utils.checkClass(gradeClass, fieldsExpected, methodsExpected, methodsUnexpected);
    }

    @Order(3)
    @Test
    void test03() throws Exception {
        String[] fieldsExpected = {"private float Grade.grade"};
        String[] methodsExpected = {
                "public float Grade.getGrade()",
                "public void Grade.setGrade(float)"
        };
        Utils.checkClass(gradeClass, fieldsExpected, methodsExpected);
    }

    @Order(4)
    @Test
    void test04() throws Exception {
        String[] fieldsExpected = {"private float Grade.gpa"};
        String[] methodsExpected = {"public float Grade.getGpa()"};
        String[] methodsUnexpected = {"public void Grade.setGpa(float)"};
        Utils.checkClass(gradeClass, fieldsExpected, methodsExpected, methodsUnexpected);
    }

    @Order(5)
    @Test
    void test05() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {"public java.lang.String Grade.toString()"};
        Utils.checkClass(gradeClass, fieldsExpected, methodsExpected);

        try {
            Class<?> studentClass = Class.forName("Student");
            Class<?> courseClass = Class.forName("Course");
            Object s = studentClass.getConstructor(String.class).newInstance("Zhangsan");
            Object c = courseClass.getConstructor(String.class).newInstance("JavaA");
            Object g = gradeClass.getConstructor(courseClass, studentClass, float.class).newInstance(c, s, 98f);
            assertEquals("sid: 1, cid: 1, grade: 98.0, gpa: 4.00", g.toString(), "toString() is incorrect!");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception unexpectedly!");
        } catch (ReflectiveOperationException e) {
            fail("Instantiation failed");
        }
    }

    @Order(6)
    @Test
    void test06() throws Exception {
        String[] fieldsExpected = {};
        String[] methodsExpected = {"public static float Grade.calGpa(float)"};
        Utils.checkClass(gradeClass, fieldsExpected, methodsExpected);
    }

    @Order(7)
    @Test
    void test07() throws Exception {
        final String errMsg = "calGpa() is incorrect!";
        try {
            Method calGpa = gradeClass.getMethod("calGpa", float.class);
            assertEquals(0f, calGpa.invoke(null, 59f), errMsg);
            assertEquals(1.15f, calGpa.invoke(null, 60f), errMsg);
            assertEquals(1.15f, calGpa.invoke(null, 62f), errMsg);
            assertEquals(1.63f, calGpa.invoke(null, 63f), errMsg);
            assertEquals(1.63f, calGpa.invoke(null, 66f), errMsg);
            assertEquals(2.08f, calGpa.invoke(null, 67f), errMsg);
            assertEquals(2.08f, calGpa.invoke(null, 69f), errMsg);
            assertEquals(2.42f, calGpa.invoke(null, 70f), errMsg);
            assertEquals(2.42f, calGpa.invoke(null, 72f), errMsg);
            assertEquals(2.78f, calGpa.invoke(null, 73f), errMsg);
            assertEquals(2.78f, calGpa.invoke(null, 76f), errMsg);
            assertEquals(3.09f, calGpa.invoke(null, 77f), errMsg);
            assertEquals(3.09f, calGpa.invoke(null, 79f), errMsg);
            assertEquals(3.32f, calGpa.invoke(null, 80f), errMsg);
            assertEquals(3.32f, calGpa.invoke(null, 82f), errMsg);
            assertEquals(3.55f, calGpa.invoke(null, 83f), errMsg);
            assertEquals(3.55f, calGpa.invoke(null, 86f), errMsg);
            assertEquals(3.73f, calGpa.invoke(null, 87f), errMsg);
            assertEquals(3.73f, calGpa.invoke(null, 89f), errMsg);
            assertEquals(3.85f, calGpa.invoke(null, 90f), errMsg);
            assertEquals(3.85f, calGpa.invoke(null, 92f), errMsg);
            assertEquals(3.94f, calGpa.invoke(null, 93f), errMsg);
            assertEquals(3.94f, calGpa.invoke(null, 96f), errMsg);
            assertEquals(4.00f, calGpa.invoke(null, 97f), errMsg);
            assertEquals(4.00f, calGpa.invoke(null, 100f), errMsg);
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception unexpectedly!");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke Grade.calGpa(float)!");
        }
    }

    @Order(8)
    @Test
    void test08() throws Exception {
        try {
            Class<?> studentClass = Class.forName("Student");
            Class<?> courseClass = Class.forName("Course");
            Field fieldStudent = gradeClass.getDeclaredField("student");
            Field fieldCourse = gradeClass.getDeclaredField("course");
            Field fieldGrade = gradeClass.getDeclaredField("grade");
            Field fieldGpa = gradeClass.getDeclaredField("gpa");

            Object s = studentClass.getConstructor(String.class).newInstance("Zhangsan");
            Object c = courseClass.getConstructor(String.class).newInstance("JavaA");
            Object g = gradeClass.getConstructor(courseClass, studentClass, float.class).newInstance(c, s, 98f);

            fieldGpa.setAccessible(true);
            fieldStudent.setAccessible(true);
            fieldGrade.setAccessible(true);
            fieldCourse.setAccessible(true);

            assertSame(s, fieldStudent.get(g), "Student is incorrect!");
            assertSame(c, fieldCourse.get(g), "Course is incorrect!");

            assertEquals(98f, fieldGrade.get(g), "Grade is incorrect!");
            assertEquals(4f, fieldGpa.get(g), "GPA is incorrect!");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException");
        } catch (ReflectiveOperationException e) {
            fail("Fail to invoke your code");
        }
    }

    @Order(9)
    @Test
    void test09() throws Exception {
        try {
            Class<?> studentClass = Class.forName("Student");
            Class<?> courseClass = Class.forName("Course");
            Field fieldStudent = gradeClass.getDeclaredField("student");
            Field fieldCourse = gradeClass.getDeclaredField("course");
            Field fieldGrade = gradeClass.getDeclaredField("grade");
            Field fieldGpa = gradeClass.getDeclaredField("gpa");
            Method methodGetStudent = gradeClass.getMethod("getStudent");
            Method methodGetCourse = gradeClass.getMethod("getCourse");
            Method methodGetGrade = gradeClass.getMethod("getGrade");
            Method methodGetGpa = gradeClass.getMethod("getGpa");

            Object s = studentClass.getConstructor(String.class).newInstance("Zhangsan");
            Object c = courseClass.getConstructor(String.class).newInstance("JavaA");
            Object g = gradeClass.getConstructor(courseClass, studentClass, float.class).newInstance(c, s, 98f);

            assertSame(s, methodGetStudent.invoke(g), "Student is incorrect!");
            assertSame(c, methodGetCourse.invoke(g), "Course is incorrect!");
            assertEquals(98f, methodGetGrade.invoke(g), "Grade is incorrect!");
            assertEquals(4f, methodGetGpa.invoke(g), "GPA is incorrect!");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code");
        }
    }

    @Order(10)
    @Test
    void test10() throws Exception {
        try {
            Class<?> studentClass = Class.forName("Student");
            Class<?> courseClass = Class.forName("Course");
            Field fieldStudent = gradeClass.getDeclaredField("student");
            Field fieldCourse = gradeClass.getDeclaredField("course");
            Field fieldGrade = gradeClass.getDeclaredField("grade");
            Field fieldGpa = gradeClass.getDeclaredField("gpa");
            Method methodGetGrade = gradeClass.getMethod("getGrade");
            Method methodSetGrade = gradeClass.getMethod("setGrade", float.class);
            Method methodGetGpa = gradeClass.getMethod("getGpa");

            Object s = studentClass.getConstructor(String.class).newInstance("Zhangsan");
            Object c = courseClass.getConstructor(String.class).newInstance("JavaA");
            Object g = gradeClass.getConstructor(courseClass, studentClass, float.class).newInstance(c, s, 98f);

            assertEquals(98f, methodGetGrade.invoke(g), "Grade is incorrect!");
            assertEquals(4f, methodGetGpa.invoke(g), "GPA is incorrect!");

            methodSetGrade.invoke(g, 66f);

            assertEquals(66f, methodGetGrade.invoke(g), "Grade is incorrect!");
            assertEquals(1.63f, methodGetGpa.invoke(g), "GPA is incorrect!");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (ReflectiveOperationException e) {
            fail("Failed to invoke your code");
        }
    }
}
