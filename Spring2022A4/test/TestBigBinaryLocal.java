import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestBigBinaryLocal {
    private static Class<?> bigBinaryClazz;
    private static Constructor<?> onlyConstructor;

    private static Method toStringMethod;

    private static Method addMethod;
    private static Method minusMethod;
    private static Method multiplyMethod;

    private static Method addStaticMethod;
    private static Method minusStaticMethod;
    private static Method multiplyStaticMethod;

    private static final String definitionErrorMessage = "Your class definition is not correct!";

    private static final String prefix = "./testcases/";
    private static final int TESTCASES = 30;


    @BeforeAll
    static void checkBigBinaryDefinition() throws InterruptedException {
        try {
            checkExist();
            checkType();
            checkModifier();
        } catch (Throwable e) {
            e.printStackTrace();
            fail(definitionErrorMessage);
        }
    }

    private static void checkExist() throws Throwable {
        bigBinaryClazz = Class.forName("BigBinary");
        onlyConstructor = bigBinaryClazz.getDeclaredConstructor(int[].class, boolean.class);
        toStringMethod = bigBinaryClazz.getDeclaredMethod("toString");

        addMethod = bigBinaryClazz.getDeclaredMethod("add", bigBinaryClazz);
        minusMethod = bigBinaryClazz.getDeclaredMethod("minus", bigBinaryClazz);
        multiplyMethod = bigBinaryClazz.getDeclaredMethod("multiply", bigBinaryClazz);

        addStaticMethod = bigBinaryClazz.getDeclaredMethod("add", bigBinaryClazz, bigBinaryClazz);
        minusStaticMethod = bigBinaryClazz.getDeclaredMethod("minus", bigBinaryClazz, bigBinaryClazz);
        multiplyStaticMethod = bigBinaryClazz.getDeclaredMethod("multiply", bigBinaryClazz, bigBinaryClazz);
    }

    private static void checkType() {
        Method[] methods = {toStringMethod, addMethod, minusMethod, multiplyMethod, addStaticMethod, minusStaticMethod, multiplyStaticMethod};
        Class<?>[] returnTypes = {String.class, bigBinaryClazz, bigBinaryClazz, bigBinaryClazz, bigBinaryClazz, bigBinaryClazz, bigBinaryClazz};
        for (int i = 0; i < methods.length; i++) {
            assertEquals(returnTypes[i], methods[i].getReturnType());
        }
    }

    private static void checkModifier() {
        assertFalse(Modifier.isAbstract(bigBinaryClazz.getModifiers()));
        assertFalse(Modifier.isFinal(bigBinaryClazz.getModifiers()));
        assertFalse(Modifier.isInterface(bigBinaryClazz.getModifiers()));

        assertTrue(Modifier.isPublic(onlyConstructor.getModifiers()));
        assertFalse(Modifier.isAbstract(onlyConstructor.getModifiers()));
        onlyConstructor.setAccessible(true);

        Method[] nonStaticMethods = {toStringMethod, addMethod, minusMethod, multiplyMethod};

        for (Method m : nonStaticMethods) {
            assertTrue(Modifier.isPublic(m.getModifiers()));
            assertFalse(Modifier.isStatic(m.getModifiers()));
            assertFalse(Modifier.isAbstract(m.getModifiers()));
            assertFalse(Modifier.isFinal(m.getModifiers()));
            assertFalse(Modifier.isNative(m.getModifiers()));

            m.setAccessible(true);
        }

        Method[] staticMethods = {addStaticMethod, minusStaticMethod, multiplyStaticMethod};

        for (Method m : staticMethods) {
            assertTrue(Modifier.isPublic(m.getModifiers()));
            assertTrue(Modifier.isStatic(m.getModifiers()));
            assertFalse(Modifier.isAbstract(m.getModifiers()));
            assertFalse(Modifier.isFinal(m.getModifiers()));
            assertFalse(Modifier.isNative(m.getModifiers()));
            m.setAccessible(true);
        }
    }

    @Test
    public void addTest() {
        assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
            for (int i = 1; i <= TESTCASES; i++) {

                BufferedReader in = new BufferedReader(new FileReader(prefix + "in/" + i + ".in"));
                String line1 = in.readLine();
                String line2 = in.readLine();
                in.close();

                Object bi1 = transIntoBigBinary(line1), bi2 = transIntoBigBinary(line2);

                BufferedReader addReader = new BufferedReader(new FileReader(prefix + "add/" + i + ".out"));
                String addLine = addReader.readLine();
                addReader.close();

                Object addResult = transIntoBigBinary(addLine);

                try {
                    assertEquals(reformat(line1), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());
                    assertEquals(addResult.toString(), addStaticMethod.invoke(null, bi1, bi2).toString());
                    assertEquals(reformat(line1), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());

                    assertEquals(addResult.toString(), addMethod.invoke(bi1, bi2).toString());
                    assertEquals(addResult.toString(), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());
                } catch (AssertionError ae) {
                    fail("The case of " + prefix + "in/" + i + ".in" + " gets Wrong Answer!");
                } catch (RuntimeException re) {
                    fail("Your code causes runtime exception!");
                }

            }
        });

    }

    @Test
    public void minusTest() {
        assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
            for (int i = 1; i <= TESTCASES; i++) {

                BufferedReader in = new BufferedReader(new FileReader(prefix + "in/" + i + ".in"));
                String line1 = in.readLine();
                String line2 = in.readLine();
                in.close();

                Object bi1 = transIntoBigBinary(line1), bi2 = transIntoBigBinary(line2);

                BufferedReader minusReader = new BufferedReader(new FileReader(prefix + "minus/" + i + ".out"));
                String minusLine = minusReader.readLine();
                minusReader.close();

                Object minusResult = transIntoBigBinary(minusLine);

                try {
                    assertEquals(reformat(line1), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());
                    assertEquals(minusResult.toString(), minusStaticMethod.invoke(null, bi1, bi2).toString());
                    assertEquals(reformat(line1), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());

                    assertEquals(minusResult.toString(), minusMethod.invoke(bi1, bi2).toString());
                    assertEquals(minusResult.toString(), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());
                } catch (AssertionError ae) {
                    fail("The case of " + prefix + "in/" + i + ".in" + " gets Wrong Answer!");
                } catch (RuntimeException re) {
                    fail("Your code causes runtime exception!");
                }

            }
        });

    }

    @Test
    public void multiplyTest() {
        assertTimeoutPreemptively(Duration.ofMillis(2000), () -> {
            for (int i = 1; i <= TESTCASES; i++) {

                BufferedReader in = new BufferedReader(new FileReader(prefix + "in/" + i + ".in"));
                String line1 = in.readLine();
                String line2 = in.readLine();
                in.close();

                Object bi1 = transIntoBigBinary(line1), bi2 = transIntoBigBinary(line2);

                BufferedReader multiplyReader = new BufferedReader(new FileReader(prefix + "multiply/" + i + ".out"));
                String multiplyLine = multiplyReader.readLine();
                multiplyReader.close();

                Object multiplyResult = transIntoBigBinary(multiplyLine);

                try {
                    assertEquals(reformat(line1), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());
                    assertEquals(multiplyResult.toString(), multiplyStaticMethod.invoke(null, bi1, bi2).toString());
                    assertEquals(reformat(line1), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());

                    assertEquals(multiplyResult.toString(), multiplyMethod.invoke(bi1, bi2).toString());
                    assertEquals(multiplyResult.toString(), bi1.toString());
                    assertEquals(reformat(line2), bi2.toString());
                } catch (AssertionError ae) {
                    fail("The case of " + prefix + "in/" + i + ".in" + " gets Wrong Answer!");
                } catch (RuntimeException re) {
                    fail("Your code causes runtime exception!");
                }

            }
        });

    }

    @Test
    public void specialAddCases() throws Throwable {

        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            Object empty0 = transIntoBigBinary("");

            String binaryString = Integer.toBinaryString(0xfee1dead);
            Object binary = transIntoBigBinary("0000" + binaryString);

            assertEquals("0", empty0.toString());
            assertEquals(binaryString, binary.toString());
            assertEquals(binaryString, addStaticMethod.invoke(null, empty0, binary).toString());
            assertEquals("0", empty0.toString());
            assertEquals(binaryString, binary.toString());
            assertEquals(binaryString, addMethod.invoke(empty0, binary).toString());
            assertEquals(binaryString, empty0.toString());
            assertEquals(binaryString, binary.toString());
        });

        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int[] b1 = {1, 0, 0, 0 ,0};
            int[] b2 = {1, 1, 1, 1, 1};

            Object bigBinary1 = onlyConstructor.newInstance(b1, true);
            Object bigBinary2 = onlyConstructor.newInstance(b2, true);

            flip(b1);
            flip(b2);

            assertEquals("10000", bigBinary1.toString());
            assertEquals("11111", bigBinary2.toString());

            assertEquals("101111", addStaticMethod.invoke(null, bigBinary1, bigBinary2).toString());
            assertEquals("101111", addMethod.invoke(bigBinary1, bigBinary2).toString());
            assertEquals("101111", bigBinary1.toString());
            assertEquals("11111", bigBinary2.toString());

        });

    }

    private void flip(int[] b){
        for (int i = 0; i < b.length; i++) {
            b[i] = (b[i] == 1 ? 0: 1);
        }
    }

    @Test
    public void specialMinusCases() throws Throwable {
        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            String binaryString = Integer.toBinaryString(0xdeadbeef);
            Object binary_1 = transIntoBigBinary("00000" + binaryString);
            Object binary_2 = transIntoBigBinary("0000000000" + binaryString);

            assertEquals(binaryString, binary_1.toString());
            assertEquals(binaryString, binary_2.toString());
            assertEquals("0", minusStaticMethod.invoke(null, binary_1, binary_2).toString());
            assertEquals(binaryString, binary_1.toString());
            assertEquals(binaryString, binary_2.toString());
            assertEquals("0", minusMethod.invoke(binary_1, binary_2).toString());
            assertEquals("0", binary_1.toString());
            assertEquals(binaryString, binary_2.toString());
        });

        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int[] b1 = {1, 0, 0, 0 ,0};
            int[] b2 = {1, 1, 1, 1, 1};

            Object bigBinary1 = onlyConstructor.newInstance(b1, true);
            Object bigBinary2 = onlyConstructor.newInstance(b2, true);

            flip(b1);
            flip(b2);

            assertEquals("10000", bigBinary1.toString());
            assertEquals("11111", bigBinary2.toString());

            assertEquals("-1111", minusStaticMethod.invoke(null, bigBinary1, bigBinary2).toString());
            assertEquals("-1111", minusMethod.invoke(bigBinary1, bigBinary2).toString());
            assertEquals("-1111", bigBinary1.toString());
            assertEquals("11111", bigBinary2.toString());

        });

    }

    @Test
    public void specialMultiplyCases() throws Throwable {

        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            Object empty0 = transIntoBigBinary("");

            String binaryString = Integer.toBinaryString(0xfacef00d);
            Object binary = transIntoBigBinary("000000" + binaryString);

            assertEquals("0", empty0.toString());
            assertEquals(binaryString, binary.toString());
            assertEquals("0", multiplyStaticMethod.invoke(null, binary, empty0).toString());
            assertEquals("0", empty0.toString());
            assertEquals(binaryString, binary.toString());
            assertEquals("0", multiplyMethod.invoke(binary, empty0).toString());
            assertEquals("0", empty0.toString());
            assertEquals("0", binary.toString());
        });

        assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
            int[] b1 = {1, 0, 0, 0 ,0};
            int[] b2 = {1, 1, 1, 1, 1};

            Object bigBinary1 = onlyConstructor.newInstance(b1, true);
            Object bigBinary2 = onlyConstructor.newInstance(b2, true);

            flip(b1);
            flip(b2);

            assertEquals("10000", bigBinary1.toString());
            assertEquals("11111", bigBinary2.toString());

            assertEquals("111110000", multiplyStaticMethod.invoke(null, bigBinary1, bigBinary2).toString());
            assertEquals("111110000", multiplyMethod.invoke(bigBinary1, bigBinary2).toString());
            assertEquals("111110000", bigBinary1.toString());
            assertEquals("11111", bigBinary2.toString());

        });

    }

    private Object transIntoBigBinary(String line) throws Throwable {
        boolean positive = true;

        if (line.isEmpty()) {
            return onlyConstructor.newInstance(new int[]{}, true);
        }

        if (line.charAt(0) == '-') {
            line = line.substring(1);
            positive = false;
        }

        int[] bits = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            bits[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
        }

        return onlyConstructor.newInstance(bits, positive);
    }

    private String reformat(String line) {
        boolean positive = true;
        if (line.charAt(0) == '-') {
            positive = false;
            line = line.substring(1);
        }

        boolean nonZero = false;
        StringBuilder b = new StringBuilder();

        if (!positive) {
            b.append('-');
        }

        for (char c : line.toCharArray()) {
            if (c != '0') {
                nonZero = true;
            }

            if (nonZero) {
                b.append(c);
            }
        }

        if (!nonZero) {
            return "0";
        }

        return b.toString();
    }
}
