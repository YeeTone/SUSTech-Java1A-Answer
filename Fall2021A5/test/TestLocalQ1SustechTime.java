import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class TestLocalQ1SustechTime {
    private static Class<?> stClazz;
    private static Field hourField;
    private static Field minuteField;
    private static Constructor<?> stConstructor;
    private static Method timeDifferenceMethod;
    private static Method toStringMethod;
    private static final String[] input =
            {
                    "05:27",
                    "02:06",
                    "05:18",
                    "20:25",
                    "22:11",
                    "12:52",
                    "17:12",
                    "19:01",
                    "05:38",
                    "10:09",
                    "16:31",
                    "07:09",
                    "12:31",
                    "23:41",
                    "21:22",
                    "11:56",
                    "00:43",
                    "05:38",
                    "03:31",
                    "09:00",
            };
    private static final int[][] values =
            {
                    {5, 27},
                    {2, 6},
                    {5, 18},
                    {20, 25},
                    {22, 11},
                    {12, 52},
                    {17, 12},
                    {19, 1},
                    {5, 38},
                    {10, 9},
                    {16, 31},
                    {7, 9},
                    {12, 31},
                    {23, 41},
                    {21, 22},
                    {11, 56},
                    {0, 43},
                    {5, 38},
                    {3, 31},
                    {9, 0},
            };

    private static final int[] differences = {
            201,
            192,
            907,
            106,
            559,
            260,
            109,
            803,
            271,
            382,
            562,
            322,
            670,
            139,
            566,
            673,
            295,
            127,
            329,
    };

    @BeforeAll
    public static void checkClassDefinition() throws Throwable {
        checkExists();
        checkType();
        checkModifier();
    }

    private static void checkExists() throws Throwable {
        stClazz = Class.forName("SustechTime");
        hourField = stClazz.getDeclaredField("hour");
        minuteField = stClazz.getDeclaredField("minute");
        timeDifferenceMethod = stClazz.getDeclaredMethod("timeDifference", stClazz);
        toStringMethod = stClazz.getDeclaredMethod("toString");
        stConstructor = stClazz.getDeclaredConstructor(String.class);
    }

    private static void checkType() throws Throwable {
        assertSame(int.class, hourField.getType(), "The type of hour field is wrong!");
        assertSame(int.class, minuteField.getType(), "The type of minute field is wrong!");
        assertSame(int.class, timeDifferenceMethod.getReturnType(), "The return type of timeDifference method is wrong!");
        assertSame(String.class, toStringMethod.getReturnType(), "The return type of toString method is wrong!");
    }

    private static void checkModifier() {
        Field[] fields = {hourField, minuteField};

        for (Field f : fields) {
            assertTrue(Modifier.isPrivate(f.getModifiers()), "The field should be private!");
            assertFalse(Modifier.isStatic(f.getModifiers()), "The field cannot be static!");
            assertFalse(Modifier.isFinal(f.getModifiers()), "The field cannot be final!");
            f.setAccessible(true);
        }

        assertTrue(Modifier.isPublic(stConstructor.getModifiers()), "The constructor should be public!");

        Method[] methods = {timeDifferenceMethod, toStringMethod};

        for (Method m : methods) {
            assertTrue(Modifier.isPublic(m.getModifiers()), "The method should be public!");
            assertFalse(Modifier.isAbstract(m.getModifiers()), "The method cannot be abstract!");
            assertFalse(Modifier.isFinal(m.getModifiers()), "The method cannot be final!");
            assertFalse(Modifier.isStatic(m.getModifiers()), "The method cannot be static!");
            m.setAccessible(true);
        }
    }

    @Test
    @Timeout(5000)
    public void constructorTest() throws Throwable {

        for (int i = 0; i < input.length; i++) {
            Object time = stConstructor.newInstance(input[i]);

            int hour = values[i][0], minute = values[i][1];

            Object h = hourField.get(time);
            assertEquals(hour, h, "Your answer is wrong!");
            Object m = minuteField.get(time);
            assertEquals(minute, m, "Your answer is wrong!");
        }
    }


    @Test
    @Timeout(5000)
    public void toStringTest() throws Exception {

        for (String testcase : input) {
            Object time1 = stConstructor.newInstance(testcase);
            assertEquals(testcase, toStringMethod.invoke(time1), "Your answer is wrong!");
        }
    }

    @Test
    @Timeout(5000)
    public void timeDifferenceTest() throws Exception {

        for (int i = 0; i < input.length - 1; i++) {
            Object time1 = stConstructor.newInstance(input[i]);
            Object time2 = stConstructor.newInstance(input[i + 1]);

            assertEquals(differences[i], timeDifferenceMethod.invoke(time1, time2), "Your answer is wrong!");
        }
    }
}

