package Spring2021A4;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class MagicStringsLocalJudge {
    @Test
    public void test_01_Clazz() {
        findMagicStrings();
    }

    @Test
    public void test_02_attribute() {
        findPriority();
        findSs();
    }

    @Test
    public void test_03_Constructors() {
        findConstructor(findMagicStrings(), Modifier.PUBLIC, String.class);
        findConstructor(findMagicStrings(), Modifier.PUBLIC, String.class, String.class);
        findConstructor(findMagicStrings(), Modifier.PUBLIC, int[].class, String.class);
    }

    @Test
    public void test_04_exist_Methods() {
        findSetPriority1();
        findSetPriority2();
        findSetPriority3();
        findSortSs();
        findCompareString();
        findGetStrings();
        findSetSs();
        findStringNum();
    }

    @Test
    public void test_05_able_Methods () {
        // Check whether setPriority, compareString is well
        int[] priority = new int[]
                {9, 5, 23, 12, 16, 10, 7, 11, 21, 17, 22, 2, 25, 13, 8, 1, 18, 20, 3, 19, 15, 26, 4, 6, 24, 14};
        String[][] in = new String[][]{
                {"a", "b"},
                {"a", "a"},
                {"a", "abd"},
                {"abc", "abc"},
                {"za", "az"},
        };
        int[][] answer = new int[][]{
                {-1, 0, -1, 0, 1},
                {1, 0, -1, 0, 1}
        };
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 5; i++) {
            assertEquals(answer[0][i], callCompareString(magicStrings, in[i][0], in[i][1]));
        }
        callSetPriority1(magicStrings, priority);
        for (int i = 0; i < 5; i++) {
            assertEquals(answer[1][i], callCompareString(magicStrings, in[i][0], in[i][1]));
        }
    }

    @Test
    public void test_06_able_Methods () {
        // Check whether setPriority, compareString is well
        String priority = "12 4 17 14 13 2 9 21 7 24 19 6 26 22 18 25 20 23 15 16 8 3 11 5 1 10 ";
        String[][] in = new String[][]{
                {"a", "b"},
                {"a", "a"},
                {"a", "abd"},
                {"abc", "abc"},
                {"za", "az"},
        };
        int[][] answer = new int[][]{
                {-1, 0, -1, 0, 1},
                {1, 0, -1, 0, -1}
        };
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 5; i++) {
            assertEquals(answer[0][i], callCompareString(magicStrings, in[i][0], in[i][1]));
        }
        callSetPriority2(magicStrings, priority);
        for (int i = 0; i < 5; i++) {
            assertEquals(answer[1][i], callCompareString(magicStrings, in[i][0], in[i][1]));
        }
    }

    @Test
    public void test_07_able_Methods () {
        // Check whether setPriority, compareString is well
        int[] priority = new int[]
                {9, 5, 23, 12, 16, 10, 7, 11, 21, 17, 22, 2, 25, 13, 8, 1, 18, 20, 3, 19, 15, 26, 4, 6, 24, 14};
        String[][] in = new String[][]{
                {"a", "b"},
                {"a", "a"},
                {"a", "abd"},
                {"abc", "abc"},
                {"za", "az"},
        };
        int[][] answer = new int[][]{
                {-1, 0, -1, 0, 1},
                {1, 0, -1, 0, 1}
        };
        MagicStrings magicStrings = new MagicStrings("");
        for (int i = 0; i < 5; i++) {
            assertEquals(answer[0][i], callCompareString(magicStrings, in[i][0], in[i][1]));
        }
        for (int i = 0; i < 26; i++) {
            callSetPriority3(magicStrings, (char)('a' + i), priority[i]);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(answer[1][i], callCompareString(magicStrings, in[i][0], in[i][1]));
        }
    }

    @Test
    public void test_08_able_Methods () {
        // Check whether SortSs, setSs, stringNum and printS is well
        String str = "Xzw\\ w pky kA {xH FCXL nvj ylzU to K";
        MagicStrings magicStrings = new MagicStrings(str);
        assertEquals("Xzw w pky kA xH FCXL nvj ylzU to K", callGetStrings(magicStrings));
        callSortSs(magicStrings);
        assertEquals("ylzU Xzw xH w to pky nvj kA K FCXL", callGetStrings(magicStrings));
        assertEquals(10, callStringNum(magicStrings));
    }

    @Test
    public void test_09 () {
        MagicStrings magicStrings = new MagicStrings("");
        assertEquals(-1, callCompareString(magicStrings, "abcd", "aCaaa"));
        assertEquals(0, callCompareString(magicStrings, "abcd", "aBcD"));
        assertEquals(1, callCompareString(magicStrings, "abcdz", "aBcD"));
    }

    @Test
    public void test_10 () {
        String str = "AS Ata aR at";
        String an = "Ata at AS aR";
        MagicStrings magicStrings = new MagicStrings(str);
        callSortSs(magicStrings);
        assertEquals(an, callGetStrings(magicStrings));
    }


    private Class<?> findMagicStrings() {
        try {
            return Class.forName("Spring2021A4.MagicStrings");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'MagicStrings'. Please check the class name. Class 'MagicStrings' should not in a package");
            return null;
        }
    }
    private Field findPriority () {
        return findField(findMagicStrings(), "priority", int[].class, Modifier.PRIVATE);
    }
    private Field findSs () {
        return findField(findMagicStrings(), "ss", String[].class, Modifier.PRIVATE);
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

    private Method findSetPriority1 () {
        return findMethod(findMagicStrings(), "setPriority", Modifier.PUBLIC, void.class, int[].class);
    }
    private void callSetPriority1 (Object magicStrings, int[] priority) {
        try {
            findSetPriority1().invoke(magicStrings, priority);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findSetPriority2 () {
        return findMethod(findMagicStrings(), "setPriority", Modifier.PUBLIC, void.class, String.class);
    }
    private void callSetPriority2 (Object magicStrings, String priority) {
        try {
            findSetPriority2().invoke(magicStrings, priority);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findSetPriority3 () {
        return findMethod(findMagicStrings(), "setPriority", Modifier.PUBLIC, void.class, char.class, int.class);
    }
    private void callSetPriority3 (Object magicStrings, char c, int priority) {
        try {
            findSetPriority3().invoke(magicStrings, c, priority);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findSetSs() {
        return findMethod(findMagicStrings(), "setSs", Modifier.PUBLIC, void.class, String.class);
    }
    private void callSetSs (Object magicStrings, String input) {
        try {
            findSetSs().invoke(magicStrings, input);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findStringNum() {
        return findMethod(findMagicStrings(), "stringNum", Modifier.PUBLIC, int.class);
    }
    private int callStringNum(Object magicStrings) {
        try {
            return (Integer) findStringNum().invoke(magicStrings);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }
    private Method findCompareString() {
        return findMethod(findMagicStrings(), "compareString", Modifier.PUBLIC, int.class, String.class, String.class);
    }
    private int callCompareString (Object magicStrings, String a, String b) {
        try {
            return (Integer)findCompareString().invoke(magicStrings, a, b);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }
    private Method findSortSs() {
        return findMethod(findMagicStrings(), "sortSs", Modifier.PUBLIC, void.class);
    }
    private void callSortSs (Object magicStrings) {
        try {
            findSortSs().invoke(magicStrings);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
        }
    }
    private Method findGetStrings() {
        return findMethod(findMagicStrings(), "getStrings", Modifier.PUBLIC, String.class);
    }
    private String callGetStrings (Object magicStrings) {
        try {
            return (String) findGetStrings().invoke(magicStrings);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
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
