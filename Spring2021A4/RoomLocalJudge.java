package Spring2021A4;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RoomLocalJudge {
    @Test
    public void test01_class () {
        findRoom();
    }

    @Test
    public void test02_attributes () {
        findHasWhiteboard();
        findApplicants();
        findCapacity();
        findLocation();
        findRid();
        findHasDisplay();
    }

    @Test
    public void test_03_constructor () {
        findConstructor(findRoom(), Modifier.PUBLIC, String.class);
        findConstructor(findRoom(), Modifier.PUBLIC, String.class, Library.class, int.class);
        findConstructor(findRoom(), Modifier.PUBLIC, String.class, Library.class, int.class, boolean.class, boolean.class);
    }

    @Test
    public void test_04_method () {
        findToString1();
        findToString2();
        findSetApplicant();
        findRemoveApplicant();
    }

    /**
     * This test can simply help you check if your program is correct, but note that this test does not include test for method {@code removeApplicant}.
     * <p>
     * Passing the local test does not guarantee that you will pass the OnlineJudge test
     */
    @Test
    public void test_05 () {
        Room room = new Room("R101");
        String an1 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an2 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11912345         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an3 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11912345         |11912346|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an4 = "Lynn R101, capacity=3, hasDisplay=true, hasWhiteboard=true\n" +
                "|10:00   |11:00   |\n" +
                "|11912346|EMPTY   |\n";

        assertEquals(an1, callToString1(room));
        assertFalse(callSetApplicant(room, 8, 11, "11912345", 3));
        assertFalse(callSetApplicant(room, 7, 9, "11912345", 3));
        assertFalse(callSetApplicant(room, 21, 23, "11912345", 3));
        assertFalse(callSetApplicant(room, 0, 24, "11912345", 3));
        assertFalse(callSetApplicant(room, 8, 10, "11912345", 4));
        assertTrue(callSetApplicant(room, 8, 10, "11912345", 2));
        assertEquals(an2, callToString1(room));
        assertEquals(an2, callToString2(room, 8, 22));
        assertFalse(callSetApplicant(room, 10, 11, "11912345", 3));
        assertFalse(callSetApplicant(room, 9, 11, "11912346", 3));
        assertTrue(callSetApplicant(room, 10, 11, "11912346", 2));
        assertEquals(an3, callToString1(room));
        assertEquals(an4, callToString2(room, 10,12));
    }

    /**
     * This is a slightly more complicated test, but please note that passing this test does not mean you will pass the OnlineJudge test.
     */
    @Test
    public void test_06 () {
        Room room1 = new Room("R101", Library.Yidan, 3, true, false);
        Room room2 = new Room("R101", Library.LearningNexus, 5, false, false);

        String an1 = "Yidan R101, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an2 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an3 = "Yidan R101, capacity=3, hasDisplay=true, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11912345         |EMPTY   |11912346|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an4 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|11912345         |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |\n";
        String an5 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11912345|\n";
        String an6 = "LearningNexus R101, capacity=5, hasDisplay=false, hasWhiteboard=false\n" +
                "|08:00   |09:00   |10:00   |11:00   |12:00   |13:00   |14:00   |15:00   |16:00   |17:00   |18:00   |19:00   |20:00   |21:00   |\n" +
                "|EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |EMPTY   |11912346         |11912345|\n";

        assertEquals(an1, callToString1(room1));
        assertEquals(an2, callToString1(room2));
        assertTrue(callSetApplicant(room1, 8, 10, "11912345", 2));
        assertFalse(callSetApplicant(room1, 10, 12, "11912345", 3));
        assertFalse(callSetApplicant(room1, 12, 14, "11912345", 3));
        assertTrue(callSetApplicant(room1, 11, 12, "11912346", 2));
        assertEquals(an3, callToString1(room1));
        assertFalse(callSetApplicant(room2, 8, 10, "11912345", -1));
        assertTrue(callSetApplicant(room2, 8, 10, "11912345", 3));
        assertEquals(an4, callToString1(room2));
        assertFalse(callRemoveApplicant(room2, "11912346"));
        assertTrue(callRemoveApplicant(room2, "11912345"));
        assertEquals(an2, callToString1(room2));
        assertTrue(callSetApplicant(room2, 21, 22, "11912345", 2));
        assertEquals(an5, callToString1(room2));
        assertTrue(callSetApplicant(room2, 19, 21, "11912346", 2));
        assertEquals(an6, callToString1(room2));
    }


    private Class<?> findRoom() {
        try {
            return Class.forName("Spring2021A4.Room");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'Room'. Please check the class name. Class 'Room' should not in a package");
            return null;
        }
    }
    private Field findRid () {
        return findField(findRoom(), "rid", String.class, Modifier.PRIVATE);
    }
    private Field findLocation () {
        return findField(findRoom(), "location", Library.class, Modifier.PRIVATE);
    }
    private Field findCapacity () {
        return findField(findRoom(), "capacity", int.class, Modifier.PRIVATE);
    }
    private Field findHasDisplay () {
        return findField(findRoom(), "hasDisplay", boolean.class, Modifier.PRIVATE);
    }
    private Field findHasWhiteboard () {
        return findField(findRoom(), "hasWhiteboard", boolean.class, Modifier.PRIVATE);
    }
    private Field findApplicants () {
        return findField(findRoom(), "applicants", String[].class, Modifier.PRIVATE);
    }

    private Method findToString1 () {
        return findMethod(findRoom(), "toString", Modifier.PUBLIC, String.class);
    }
    private Method findToString2 () {
        return findMethod(findRoom(), "toString", Modifier.PUBLIC, String.class, int.class, int.class);
    }
    private Method findSetApplicant () {
        return findMethod(findRoom(), "setApplicant", Modifier.PUBLIC, boolean.class, int.class, int.class,
                String.class, int.class);
    }
    private Method findRemoveApplicant () {
        return findMethod(findRoom(), "removeApplicant", Modifier.PUBLIC, boolean.class, String.class);
    }

    private String callToString1 (Object room) {
        try {
            return (String) findToString1().invoke(room);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }
    private String callToString2 (Object room, int start, int end) {
        try {
            return (String) findToString2().invoke(room, start, end);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }
    private Boolean callSetApplicant (Object room, int start, int end, String SID, int numberOfTeammates) {
        try {
            return (Boolean) findSetApplicant().invoke(room, start, end, SID, numberOfTeammates);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }
    private Boolean callRemoveApplicant (Object room, String SID) {
        try {
            return (Boolean) findRemoveApplicant().invoke(room, SID);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Field findField(Class<?> clazz, String name, Class<?> type, int modifier) {
        try {
            Field field = clazz.getDeclaredField(name);
            if (!field.getType().equals(type)) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong type, it should be '%s'",
                        name, clazz.getCanonicalName(), type.toGenericString()
                ));
            }
            if (field.getModifiers() != modifier) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong modifier, it should be '%s'",
                        name, clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return field;
        } catch (NoSuchFieldException e) {
            fail(String.format("Cannot find attribute '%s' in class '%s'", name, clazz.getCanonicalName()));
            return null;
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz, int modifier, Class<?>... parameters) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameters);
            if (constructor.getModifiers() != modifier) {
                fail(String.format(
                        "Constructor '(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return constructor;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find constructor '(%s)' in class '%s'",
                    getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    private Method findMethod(Class<?> clazz, String name, int modifier, Class<?> returnType, Class<?>... parameters) {
        try {
            Method method = clazz.getDeclaredMethod(name, parameters);
            if (method.getReturnType() != returnType) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong return type, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), returnType.toGenericString()
                ));
            }
            if (method.getModifiers() != modifier) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return method;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find method '%s(%s)' in class '%s'",
                    name, getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    private String getParameterTypeString(Class<?>[] parameters) {
        return Arrays.stream(parameters)
                .map(Class::getCanonicalName)
                .collect(Collectors.joining(", "));
    }
}
