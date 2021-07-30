package Fall2020A3;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestStudent {
    private static Class<?> studentClass;

    @BeforeAll
    static void beforeAll() {
        try {
            studentClass = Class.forName("Fall2020A3.Student");
        } catch (ClassNotFoundException e) {
            fail(e.toString());
        }
    }

    @Order(1)
    @Test
    void test1() throws Exception {
        String[] fieldsExpected = {"private java.lang.String Fall2020A3.Student.name"};
        String[] methodsExpected = {
                "public java.lang.String Fall2020A3.Student.getName()",
                "public void Fall2020A3.Student.setName(java.lang.String)"
        };
        Utils.checkClass(studentClass, fieldsExpected, methodsExpected);
    }

    @Order(2)
    @Test
    void test2() throws Exception {
        String[] fieldsExpected = {"private int Fall2020A3.Student.sid"};
        String[] methodsExpected = {"public int Fall2020A3.Student.getSid()"};
        String[] methodsUnexpected = {"public void Fall2020A3.Student.setSid(int)"};
        Utils.checkClass(studentClass, fieldsExpected, methodsExpected, methodsUnexpected);
    }

    @Order(3)
    @Test
    void test3() throws Exception {
        String[] fieldsExpected = {"private static int Fall2020A3.Student.studentCnt"};
        String[] methodsExpected = {"public static int Fall2020A3.Student.getStudentCnt()"};
        Utils.checkClass(studentClass, fieldsExpected, methodsExpected);
    }

    @Order(4)
    @Test
    void test4() throws Exception {
        String[] fieldsExpected = {
                "private java.lang.String Fall2020A3.Student.name",
                "private int Fall2020A3.Student.sid",
                "private static int Fall2020A3.Student.studentCnt"
        };
        String[] methodsExpected = {
                "public java.lang.String Fall2020A3.Student.getName()",
                "public java.lang.String Fall2020A3.Student.toString()",
                "public void Fall2020A3.Student.setName(java.lang.String)",
                "public static int Fall2020A3.Student.getStudentCnt()",
                "public int Fall2020A3.Student.getSid()"
        };
        Utils.checkClass(studentClass, fieldsExpected, methodsExpected);

        try {
            Object s1 = studentClass.getConstructor(String.class).newInstance("Zhangsan");
            Field fieldName = studentClass.getDeclaredField("name");
            Field fieldSid = studentClass.getDeclaredField("sid");

            fieldName.setAccessible(true);
            fieldSid.setAccessible(true);

            assertEquals("Zhangsan", fieldName.get(s1), "Student name is incorrect!");
            assertEquals(1, fieldSid.get(s1), "Student ID is incorrect!");
            assertEquals("Student: Zhangsan, sid: 1", s1.toString(), "toString() is incorrect!");
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            fail("Instantiation failed");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException");
        }
    }

    @Order(5)
    @Test
    void test5() throws Exception {
        String[] fieldsExpected = {
                "private java.lang.String Fall2020A3.Student.name",
                "private int Fall2020A3.Student.sid",
                "private static int Fall2020A3.Student.studentCnt"
        };
        String[] methodsExpected = {
                "public java.lang.String Fall2020A3.Student.getName()",
                "public java.lang.String Fall2020A3.Student.toString()",
                "public void Fall2020A3.Student.setName(java.lang.String)",
                "public static int Fall2020A3.Student.getStudentCnt()",
                "public int Fall2020A3.Student.getSid()"
        };
        Utils.checkClass(studentClass, fieldsExpected, methodsExpected);

        try {
            Constructor<?> constructor = studentClass.getConstructor(String.class);
            Object s1 = constructor.newInstance("Zhangsan");
            Object s2 = constructor.newInstance("Lisi");
            Field fieldName = studentClass.getDeclaredField("name");
            Field fieldSid = studentClass.getDeclaredField("sid");
            Method methodGetSid = studentClass.getMethod("getSid");
            Method methodGetName = studentClass.getMethod("getName");
            Method methodSetName = studentClass.getMethod("setName", String.class);
            Method staticMethodGetStudentCnt = studentClass.getMethod("getStudentCnt");

            fieldName.setAccessible(true);
            fieldSid.setAccessible(true);

            assertEquals("Zhangsan", fieldName.get(s1), "Student name is incorrect!");
            assertEquals(2, fieldSid.get(s1), "Student ID is incorrect!");
            assertEquals(2, methodGetSid.invoke(s1), "Student ID is incorrect!");

            assertEquals("Lisi", fieldName.get(s2), "Student name is incorrect!");
            assertEquals(3, fieldSid.get(s2), "Student ID is incorrect!");
            assertEquals(3, methodGetSid.invoke(s2), "Student ID is incorrect!");

            methodSetName.invoke(s2, "Zhangsansan");

            assertEquals("Zhangsansan", fieldName.get(s2), "Student name is incorrect!");
            assertEquals("Zhangsansan", methodGetName.invoke(s2), "Student name is incorrect!");

            assertEquals(4, staticMethodGetStudentCnt.invoke(null), "cnt is incorrect!");
        } catch (NoSuchMethodException e) {
            fail("Missing constructor or method");
        } catch (InstantiationException e) {
            fail("Instantiation failed");
        } catch (IllegalArgumentException e) {
            fail("A mismatch between formal and actual parameters may exists");
        } catch (NoSuchFieldException e) {
            fail("Missing attribute");
        } catch (InvocationTargetException e) {
            fail("Your code throws an exception unexpectedly");
        } catch (IllegalAccessException e) {
            fail("IllegalAccessException");
        }
    }

}
