import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import java.lang.reflect.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TestLocalQ2Flight {

    private static Class<?> fClazz;
    private static Field flightNoField;
    private static Field departTimeField;
    private static Field arriveTimeField;
    private static Field priceField;
    private static Constructor<?> fConstructor;
    private static Method toStringMethod;

    private static final String[][] testcases =
            {
                    {"g973", "k", "d", "15", "24", "18", "37", "65394",},
                    {"Y854", "f", "p", "03", "51", "22", "24", "108155",},
                    {"B664", "I", "Y", "22", "53", "23", "29", "51023",},
                    {"I379", "c", "I", "04", "43", "4", "58", "121571",},
                    {"c496", "R", "J", "02", "17", "11", "55", "25314",},
                    {"m714", "V", "S", "03", "3", "10", "06", "100797",},
                    {"U611", "A", "f", "09", "21", "16", "2", "32939",},
                    {"S840", "m", "F", "13", "04", "23", "29", "59875",},
                    {"i844", "L", "P", "18", "52", "22", "40", "72572",},
                    {"m105", "I", "x", "13", "16", "15", "53", "11592",},
                    {"u528", "h", "M", "23", "27", "23", "27", "14841",},
                    {"P179", "F", "w", "11", "57", "21", "33", "50892",},
                    {"C595", "x", "W", "10", "44", "16", "10", "13269",},
                    {"s228", "u", "F", "4", "40", "09", "57", "105910",},
                    {"e804", "V", "z", "20", "23", "21", "56", "60360",},
                    {"h773", "E", "S", "02", "27", "8", "03", "6047",},
                    {"J629", "H", "O", "10", "8", "22", "30", "67414",},
                    {"B683", "J", "W", "11", "51", "21", "05", "64506",},
                    {"B506", "w", "c", "10", "33", "12", "45", "24363",},
                    {"y424", "t", "y", "22", "4", "22", "51", "57935",},
            };

    private static final String[] ans = {
            "g973 [k -> d] 15:24 -> 18:37 (65394)",
            "Y854 [f -> p] 03:51 -> 22:24 (108155)",
            "B664 [I -> Y] 22:53 -> 23:29 (51023)",
            "I379 [c -> I] 04:43 -> 04:58 (121571)",
            "c496 [R -> J] 02:17 -> 11:55 (25314)",
            "m714 [V -> S] 03:03 -> 10:06 (100797)",
            "U611 [A -> f] 09:21 -> 16:02 (32939)",
            "S840 [m -> F] 13:04 -> 23:29 (59875)",
            "i844 [L -> P] 18:52 -> 22:40 (72572)",
            "m105 [I -> x] 13:16 -> 15:53 (11592)",
            "u528 [h -> M] 23:27 -> 23:27 (14841)",
            "P179 [F -> w] 11:57 -> 21:33 (50892)",
            "C595 [x -> W] 10:44 -> 16:10 (13269)",
            "s228 [u -> F] 04:40 -> 09:57 (105910)",
            "e804 [V -> z] 20:23 -> 21:56 (60360)",
            "h773 [E -> S] 02:27 -> 08:03 (6047)",
            "J629 [H -> O] 10:08 -> 22:30 (67414)",
            "B683 [J -> W] 11:51 -> 21:05 (64506)",
            "B506 [w -> c] 10:33 -> 12:45 (24363)",
            "y424 [t -> y] 22:04 -> 22:51 (57935)",
    };

    @BeforeAll
    public static void checkClassDefinition() throws Throwable {
        checkExists();
        checkType();
        checkModifier();
    }

    private static void checkExists() throws Throwable {
        fClazz = Class.forName("Flight");
        flightNoField = fClazz.getDeclaredField("flightNo");
        departTimeField = fClazz.getDeclaredField("departTime");
        arriveTimeField = fClazz.getDeclaredField("arriveTime");
        priceField = fClazz.getDeclaredField("price");
        fConstructor = fClazz.getDeclaredConstructor(String.class);
        toStringMethod = fClazz.getDeclaredMethod("toString");
    }

    private static void checkType() throws Throwable {
        Class<?> stClass = Class.forName("SustechTime");

        Field[] fields = {flightNoField, departTimeField, arriveTimeField, priceField};
        Class<?>[] classes = {String.class, stClass, stClass, int.class, String.class};

        for (int i = 0; i < fields.length; i++) {
            assertSame(fields[i].getType(), classes[i], "The field type is Wrong!");
        }

        assertSame(toStringMethod.getReturnType(), String.class, "The method return type is Wrong!");
    }

    private static void checkModifier() throws Throwable {
        Field[] fields = {flightNoField, departTimeField, arriveTimeField, priceField};

        for (Field f : fields) {
            assertTrue(Modifier.isPrivate(f.getModifiers()), "The field should be private!");
            assertFalse(Modifier.isStatic(f.getModifiers()), "The field cannot be static!");
            assertFalse(Modifier.isFinal(f.getModifiers()), "The field cannot be final!");
            f.setAccessible(true);
        }

        assertTrue(Modifier.isPublic(fConstructor.getModifiers()), "The constructor should be public!");

        assertTrue(Modifier.isPublic(toStringMethod.getModifiers()), "The method should be public!");
        assertFalse(Modifier.isStatic(toStringMethod.getModifiers()), "The method cannot be static!");
        assertFalse(Modifier.isAbstract(toStringMethod.getModifiers()), "The method cannot be abstract!");
        assertFalse(Modifier.isFinal(toStringMethod.getModifiers()), "The method cannot be final!");

    }


    @Test
    @Timeout(5000)
    public void constructorTest() throws InstantiationException, IllegalAccessException, InvocationTargetException {

        for (String[] testcase : testcases) {
            String input = String.format("%s %s %s %s:%s %s:%s %s",
                    testcase[0], testcase[1],
                    testcase[2], testcase[3],
                    testcase[4], testcase[5],
                    testcase[6], testcase[7]);

            Object flight = fConstructor.newInstance(input);

            assertEquals(testcase[0], flightNoField.get(flight), "Your answer is Wrong!");
            assertEquals(String.format("%02d:%02d", Integer.parseInt(testcase[3]), Integer.parseInt(testcase[4])), departTimeField.get(flight).toString(), "Your answer is Wrong!");
            assertEquals(String.format("%02d:%02d", Integer.parseInt(testcase[5]), Integer.parseInt(testcase[6])), arriveTimeField.get(flight).toString(), "Your answer is Wrong!");
            assertEquals(Integer.parseInt(testcase[7]), priceField.get(flight), "Your answer is Wrong!");
        }
    }

    @Test
    @Timeout(5000)
    public void toStringTest() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        for (int i = 0; i < testcases.length; i++) {
            String[] testcase = testcases[i];
            String input = String.format("%s %s %s %s:%s %s:%s %s",
                    testcase[0], testcase[1],
                    testcase[2], testcase[3],
                    testcase[4], testcase[5],
                    testcase[6], testcase[7]);

            Object flight = fConstructor.newInstance(input);
            String expected = ans[i];
            assertEquals(toStringMethod.invoke(flight), expected, "Your answer is Wrong!");
        }
    }
}