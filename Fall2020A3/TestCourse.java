package Fall2020A3;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCourse {
    private static Class<?> courseClass;

    @BeforeAll
    static void beforeAll() {
        try {
            courseClass = Class.forName("Fall2020A3.Course");
        } catch (ClassNotFoundException e) {
            fail(e.toString());
        }
    }

    @Order(1)
    @Test
    void test1() throws Exception {
        String[] fieldsExpected = new String[]{"private java.lang.String Fall2020A3.Course.name"};
        String[] methodsExpected = new String[]{
                "public java.lang.String Fall2020A3.Course.getName()",
                "public void Fall2020A3.Course.setName(java.lang.String)"
        };
        Utils.checkClass(courseClass, fieldsExpected, methodsExpected);
    }

    @Order(2)
    @Test
    void test2() throws Exception {
        String[] fieldsExpected = new String[]{"private int Fall2020A3.Course.cid"};
        String[] methodsExpected = new String[]{"public int Fall2020A3.Course.getCid()"};
        String[] methodsUnexpected = {"public void Fall2020A3.Course.setCid(int)"};
        Utils.checkClass(courseClass, fieldsExpected, methodsExpected, methodsUnexpected);
    }

    @Order(3)
    @Test
    void test3() throws Exception {
        String[] fieldsExpected = {"private static int Fall2020A3.Course.courseCnt"};
        String[] methodsExpected = {"public static int Fall2020A3.Course.getCourseCnt()"};
        Utils.checkClass(courseClass, fieldsExpected, methodsExpected);
    }

    @Order(4)
    @Test
    void test4() throws Exception {
        String[] fieldsExpected = {
                "private java.lang.String Fall2020A3.Course.name",
                "private int Fall2020A3.Course.cid",
                "private static int Fall2020A3.Course.courseCnt"
        };
        String[] methodsExpected = {
                "public java.lang.String Fall2020A3.Course.getName()",
                "public java.lang.String Fall2020A3.Course.toString()",
                "public void Fall2020A3.Course.setName(java.lang.String)",
                "public static int Fall2020A3.Course.getCourseCnt()",
                "public int Fall2020A3.Course.getCid()"
        };
        Utils.checkClass(courseClass, fieldsExpected, methodsExpected);

        try {
            Object c1 = courseClass.getConstructor(String.class).newInstance("JavaA");
            Field fieldName = courseClass.getDeclaredField("name");
            Field fieldCid = courseClass.getDeclaredField("cid");

            fieldName.setAccessible(true);
            fieldCid.setAccessible(true);

            assertEquals("JavaA", fieldName.get(c1), "Course name is incorrect!");
            assertEquals(1, fieldCid.get(c1), "Course ID is incorrect!");
            assertEquals("Course: JavaA, cid: 1", c1.toString(), "toString() is incorrect!");
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
        String[] fieldsExpected = new String[]{
                "private java.lang.String Fall2020A3.Course.name",
                "private int Fall2020A3.Course.cid",
                "private static int Fall2020A3.Course.courseCnt"
        };
        String[] methodsExpected = new String[]{
                "public java.lang.String Fall2020A3.Course.getName()",
                "public java.lang.String Fall2020A3.Course.toString()",
                "public void Fall2020A3.Course.setName(java.lang.String)",
                "public static int Fall2020A3.Course.getCourseCnt()",
                "public int Fall2020A3.Course.getCid()"
        };
        Utils.checkClass(courseClass, fieldsExpected, methodsExpected);

        try {
            Constructor<?> constructor = courseClass.getConstructor(String.class);
            Object c1 = constructor.newInstance("JavaA");
            Object c2 = constructor.newInstance("JavaB");
            Field fieldName = courseClass.getDeclaredField("name");
            Field fieldCid = courseClass.getDeclaredField("cid");
            Method methodGetName = courseClass.getMethod("getName");
            Method methodSetName = courseClass.getMethod("setName", String.class);
            Method methodGetCid = courseClass.getMethod("getCid");
            Method staticMethodGetCourseCnt = courseClass.getMethod("getCourseCnt");

            fieldName.setAccessible(true);
            fieldCid.setAccessible(true);

            assertEquals("JavaA", fieldName.get(c1), "Course name is incorrect!");
            assertEquals(2, fieldCid.get(c1), "Course ID is incorrect!");

            assertEquals("JavaB", fieldName.get(c2), "Course name is incorrect!");
            assertEquals(3, fieldCid.get(c2), "Course ID is incorrect!");

            methodSetName.invoke(c2, "DSAA");
            assertEquals("DSAA", fieldName.get(c2), "Course name is incorrect!");
            assertEquals("DSAA", methodGetName.invoke(c2), "Course name is incorrect!");

            assertEquals(4, staticMethodGetCourseCnt.invoke(null), "cnt is incorrect!");
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
