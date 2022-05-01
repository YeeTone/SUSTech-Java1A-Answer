import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.security.Permission;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class TestBigBinaryOfficial {
    private static final AtomicBoolean ableResetManager = new AtomicBoolean(false);

    private static final class BigIntegerBanner extends SecurityManager {

        @Override
        public void checkPackageAccess(String pkg) {
            super.checkPackageAccess(pkg);
            if (pkg.equals("java.math")) {
                throw new BigIntegerBannedException("You cannot use java.math.BigInteger or java.math.BigDecimal in this problem!");
            }

        }

        @Override
        public void checkPermission(Permission perm) {
            if (perm.getName().equals("setSecurityManager") && !ableResetManager.get()) {
                throw new ResetSecurityException("Do not reset SecurityManager!");
            }

        }

    }

    private static final class BigIntegerBannedException extends RuntimeException {
        public BigIntegerBannedException(String message) {
            super(message);
        }
    }

    private static final class ResetSecurityException extends RuntimeException {
        public ResetSecurityException(String message) {
            super(message);
        }
    }

    private static final class BigIntegerAllower extends SecurityManager {
        @Override
        public void checkPermission(Permission perm) {

        }
    }

    private static final SecurityManager bigIntegerBanner = new BigIntegerBanner();
    private static final SecurityManager bigIntegerAllower = new BigIntegerAllower();

    private static Class<?> bigBinaryClazz;
    private static Constructor<?> onlyConstructor;

    private static Method toStringMethod;

    private static Method addMethod;
    private static Method minusMethod;
    private static Method multiplyMethod;

    private static Method addStaticMethod;
    private static Method minusStaticMethod;
    private static Method multiplyStaticMethod;

    private static final Random generator = new Random();
    private static final String definitionErrorMessage = "Your class definition is not correct!";
    private static final String exceptionMessage = "Your code cause Exception!";
    private static final String assertionFailMessage = "Your answer is wrong!";
    private static final String selectRandom = "RANDOM";
    private static final String selectNotRandom = "NOT-RANDOM";

    private static final long DURATION_MILL = 3000;
    private static final int addMinusLength = 15000;
    private static final int multiplyLength = 15000;

    @BeforeAll
    static void checkBigBinaryDefinition() throws InterruptedException {
        try {
            checkExist();
            checkType();
            checkModifier();
        } catch (Throwable e) {
            fail(definitionErrorMessage);
        }
    }

    @BeforeEach
    public void initEach() {
        allowBigInteger();
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

    private int[] generateBits(int length) {
        length = generator.nextInt(length);
        int[] bits = new int[length];
        for (int i = 0; i < length; i++) {
            if (generator.nextBoolean()) {
                bits[i] = 1;
            } else {
                bits[i] = 0;
            }
        }
        return bits;
    }

    private boolean randomPositive() {
        return generator.nextBoolean();
    }

    private BigInteger buildBigInteger(int[] b1, boolean p1) {
        allowBigInteger();
        StringBuilder b = new StringBuilder();
        if (!p1) {
            b.append('-');
        }
        for (int i : b1) {
            b.append(i);
        }

        if (b.length() == 0 || (b.length() == 1 && b.charAt(0) == '-')) {
            b.setLength(0);
            b.append("0");
        }

        BigInteger result = new BigInteger(b.toString(), 2);

        banBigInteger();

        return result;
    }

    @Test
    public void add01() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 150, bitLength = addMinusLength;
            addJudgement(cases, bitLength, true, true, selectNotRandom);
        });
    }

    @Test
    public void add02() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 150, bitLength = addMinusLength;
            addJudgement(cases, bitLength, false, false, selectNotRandom);
        });

    }

    @Test
    public void add03() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 120, reverseCases = 30, bitLength = addMinusLength;
            addJudgement(cases, bitLength, true, false, selectNotRandom);
            reverseSignJudge(reverseCases, addMinusLength);
        });
    }

    @Test
    public void add04() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 120, reverseCases = 30, bitLength = addMinusLength;
            addJudgement(cases, bitLength, false, true, selectNotRandom);
            reverseSignJudge(reverseCases, bitLength);
        });

    }

    @Test
    public void add05() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 120, reverseCases = 30, bitLength = addMinusLength;
            addJudgement(cases, bitLength, randomPositive(), randomPositive(), selectRandom);
            reverseSignJudge(reverseCases, bitLength);
        });
    }

    private void addJudgement(int cases, int bitLength, boolean positive1, boolean positive2, String randomParameter) {
        try {
            boolean isRandom = randomParameter.equalsIgnoreCase(selectRandom);
            for (int i = 0; i < cases; i++) {
                int[] bits1 = generateBits(bitLength);
                int[] b1Clone = bits1.clone();
                boolean p1 = isRandom ? randomPositive() : positive1;
                int[] bits2 = generateBits(bitLength);
                int[] b2Clone = bits2.clone();
                boolean p2 = isRandom ? randomPositive() : positive2;

                Object bigBinary1 = onlyConstructor.newInstance(bits1, p1);
                Object bigBinary2 = onlyConstructor.newInstance(bits2, p2);

                banBigInteger();
                bitsRandomMutation(bits1);
                Object addStaticResult = addStaticMethod.invoke(null, bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals(buildAddAnswer(b1Clone, p1, b2Clone, p2), bigBinaryToString(addStaticResult));
                assertEquals(bigBinaryToString(bigBinary1), buildBigInteger(b1Clone, p1).toString(2));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(b2Clone, p2).toString(2));

                banBigInteger();
                Object addResult = addMethod.invoke(bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals(buildAddAnswer(b1Clone, p1, b2Clone, p2), bigBinaryToString(addResult));
                assertEquals(bigBinaryToString(bigBinary1), bigBinaryToString(addResult));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(b2Clone, p2).toString(2));

            }
        } catch (Exception e) {
            handleException(e);
        } catch (AssertionError ae) {
            //throw ae;
            fail(assertionFailMessage);
        }

    }

    private void handleException(Exception e){
        if(e instanceof InvocationTargetException){
            InvocationTargetException ite = (InvocationTargetException) e;
            Throwable targetException = ite.getTargetException();
            if(targetException instanceof ResetSecurityException || targetException instanceof BigIntegerBannedException){
                fail(targetException.getMessage());
            }else {
                e.printStackTrace();
                fail(exceptionMessage);
            }
        }else{
            fail(exceptionMessage);
        }
    }

    private void reverseSignJudge(int cases, int bitLength) {
        try {
            for (int i = 0; i < cases; i++) {
                int[] bits = generateBits(bitLength);
                int[] bitsClone = bits.clone();
                boolean positive1 = randomPositive();
                boolean positive2 = !positive1;

                Object bigBinary1 = onlyConstructor.newInstance(bits, positive1);
                Object bigBinary2 = onlyConstructor.newInstance(bitsClone, positive2);

                banBigInteger();
                Object addStaticResult = addStaticMethod.invoke(null, bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals("0", bigBinaryToString(addStaticResult));
                assertEquals(bigBinaryToString(bigBinary1), buildBigInteger(bits, positive1).toString(2));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(bitsClone, positive2).toString(2));

                banBigInteger();
                Object addResult = addMethod.invoke(bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals("0", bigBinaryToString(addResult));
                assertEquals("0", bigBinaryToString(bigBinary1));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(bitsClone, positive2).toString(2));
            }
        } catch (Exception e) {
            handleException(e);
        } catch (AssertionError e) {
            fail(assertionFailMessage);
        }
    }

    private String buildAddAnswer(int[] b1, boolean p1, int[] b2, boolean p2) {
        allowBigInteger();
        BigInteger bigInteger1 = buildBigInteger(b1, p1);
        BigInteger bigInteger2 = buildBigInteger(b2, p2);

        String result = bigInteger1.add(bigInteger2).toString(2);
        banBigInteger();

        return result;
    }

    @Test
    public void minus01() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 120, sameAbsCases = 30, bitLength = addMinusLength;
            minusJudgement(cases, bitLength, true, true, selectNotRandom);
            sameAbsoluteJudge(sameAbsCases, bitLength);
        });
    }

    @Test
    public void minus02() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 120, sameAbsCases = 30, bitLength = addMinusLength;
            minusJudgement(cases, bitLength, false, false, selectNotRandom);
            sameAbsoluteJudge(sameAbsCases, bitLength);
        });

    }

    @Test
    public void minus03() {
        allowBigInteger();
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 150, bitLength = addMinusLength;
            minusJudgement(cases, bitLength, true, false, selectNotRandom);
        });


    }

    @Test
    public void minus04() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 150, bitLength = addMinusLength;
            minusJudgement(cases, bitLength, false, true, selectNotRandom);
        });
    }

    @Test
    public void minus05() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 150, bitLength = addMinusLength;
            minusJudgement(cases, bitLength, randomPositive(), randomPositive(), selectRandom);
        });
    }

    private void minusJudgement(int cases, int bitLength, boolean positive1, boolean positive2, String randomParameter) {
        try {
            boolean isRandom = randomParameter.equalsIgnoreCase(selectRandom);
            for (int i = 0; i < cases; i++) {
                int[] bits1 = generateBits(bitLength);
                int[] b1Clone = bits1.clone();
                boolean p1 = isRandom ? randomPositive() : positive1;
                int[] bits2 = generateBits(bitLength);
                int[] b2Clone = bits2.clone();
                boolean p2 = isRandom ? randomPositive() : positive2;

                Object bigBinary1 = onlyConstructor.newInstance(bits1, p1);
                Object bigBinary2 = onlyConstructor.newInstance(bits2, p2);

                banBigInteger();
                bitsRandomMutation(bits1);
                Object minusStaticResult = minusStaticMethod.invoke(null, bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals(buildMinusAnswer(b1Clone, p1, b2Clone, p2), bigBinaryToString(minusStaticResult));
                assertEquals(bigBinaryToString(bigBinary1), buildBigInteger(b1Clone, p1).toString(2));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(b2Clone, p2).toString(2));

                banBigInteger();
                Object minusResult = minusMethod.invoke(bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals(buildMinusAnswer(b1Clone, p1, b2Clone, p2), bigBinaryToString(minusResult));
                assertEquals(bigBinaryToString(bigBinary1), bigBinaryToString(minusResult));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(b2Clone, p2).toString(2));

            }
        } catch (Exception e) {
            handleException(e);
        } catch (AssertionError ae) {
            fail(assertionFailMessage);
        }
    }

    private String buildMinusAnswer(int[] b1, boolean p1, int[] b2, boolean p2) {
        allowBigInteger();
        BigInteger bigInteger1 = buildBigInteger(b1, p1);
        BigInteger bigInteger2 = buildBigInteger(b2, p2);

        String result = bigInteger1.subtract(bigInteger2).toString(2);
        banBigInteger();

        return result;
    }

    private void sameAbsoluteJudge(int cases, int bitLength) {
        try {
            for (int i = 0; i < cases; i++) {
                int[] bits = generateBits(bitLength);
                int[] bitsClone = bits.clone();
                boolean sameSign = randomPositive();

                Object bigBinary1 = onlyConstructor.newInstance(bits, sameSign);
                Object bigBinary2 = onlyConstructor.newInstance(bitsClone, sameSign);

                banBigInteger();
                Object addStaticResult = minusStaticMethod.invoke(null, bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals("0", bigBinaryToString(addStaticResult));
                assertEquals(bigBinaryToString(bigBinary1), buildBigInteger(bits, sameSign).toString(2));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(bitsClone, sameSign).toString(2));

                banBigInteger();
                Object addResult = minusMethod.invoke(bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals("0", bigBinaryToString(addResult));
                assertEquals("0", bigBinaryToString(bigBinary1));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(bitsClone, sameSign).toString(2));
            }
        } catch (Exception e) {
            handleException(e);
        } catch (AssertionError e) {
            fail(assertionFailMessage);
        }
    }

    @Test
    public void bonusMultiply01() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 50, bitLength = multiplyLength >> 3;
            multiplyJudgement(cases, bitLength);
        });
    }

    @Test
    public void bonusMultiply02() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 50, bitLength = multiplyLength >> 2;
            multiplyJudgement(cases, bitLength);
        });
    }

    @Test
    public void bonusMultiply03() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 50, bitLength = multiplyLength >> 1;
            multiplyJudgement(cases, bitLength);
        });
    }

    @Test
    public void bonusMultiply04() {
        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 50, bitLength = multiplyLength;
            multiplyJudgement(cases, bitLength);
        });
    }

    @Test
    public void bonusMultiply05() {

        assertTimeoutPreemptively(Duration.ofMillis(DURATION_MILL), () -> {
            int cases = 40, mul0Cases = 10, bitLength = multiplyLength;
            multiplyJudgement(cases, bitLength);
            mul0Judgement(mul0Cases, bitLength);
        });
    }

    private void multiplyJudgement(int cases, int bitLength) {
        try {
            for (int i = 0; i < cases; i++) {
                int[] bits1 = generateBits(bitLength);
                int[] b1Clone = bits1.clone();
                boolean p1 = randomPositive();
                int[] bits2 = generateBits(bitLength);
                int[] b2Clone = bits2.clone();
                boolean p2 = randomPositive();

                Object bigBinary1 = onlyConstructor.newInstance(bits1, p1);
                Object bigBinary2 = onlyConstructor.newInstance(bits2, p2);

                banBigInteger();
                bitsRandomMutation(bits1);
                Object multiplyStaticResult = multiplyStaticMethod.invoke(null, bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals(buildMultiplyAnswer(b1Clone, p1, b2Clone, p2), bigBinaryToString(multiplyStaticResult));
                assertEquals(bigBinaryToString(bigBinary1), buildBigInteger(b1Clone, p1).toString(2));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(b2Clone, p2).toString(2));

                banBigInteger();
                Object multiplyResult = multiplyMethod.invoke(bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals(buildMultiplyAnswer(b1Clone, p1, b2Clone, p2), bigBinaryToString(multiplyResult));
                assertEquals(bigBinaryToString(bigBinary1), bigBinaryToString(multiplyResult));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(b2Clone, p2).toString(2));

            }
        } catch (Exception e) {
            handleException(e);
        } catch (AssertionError ae) {
            fail(assertionFailMessage);
        }
    }

    private String buildMultiplyAnswer(int[] b1, boolean p1, int[] b2, boolean p2) {
        allowBigInteger();
        BigInteger bigInteger1 = buildBigInteger(b1, p1);
        BigInteger bigInteger2 = buildBigInteger(b2, p2);

        String result = bigInteger1.multiply(bigInteger2).toString(2);
        banBigInteger();

        return result;
    }

    private void allowBigInteger() {
        synchronized (ableResetManager) {
            ableResetManager.set(true);
            System.setSecurityManager(bigIntegerAllower);
        }

    }

    private void banBigInteger() {
        synchronized (ableResetManager) {
            ableResetManager.set(true);
            System.setSecurityManager(bigIntegerBanner);
            ableResetManager.set(false);
        }

    }

    private void mul0Judgement(int cases, int bitLength) {
        try {
            for (int i = 0; i < cases; i++) {
                int[] bits1 = generateBits(bitLength);
                boolean p1 = randomPositive();
                int[] bits2 = {};
                boolean p2 = randomPositive();

                Object bigBinary1 = onlyConstructor.newInstance(bits1, p1);
                Object bigBinary2 = onlyConstructor.newInstance(bits2, p2);

                banBigInteger();
                Object multiplyStaticResult = multiplyStaticMethod.invoke(null, bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals("0", bigBinaryToString(multiplyStaticResult));
                assertEquals(bigBinaryToString(bigBinary1), buildBigInteger(bits1, p1).toString(2));
                assertEquals("0", bigBinaryToString(bigBinary2));

                banBigInteger();
                Object multiplyResult = multiplyMethod.invoke(bigBinary1, bigBinary2);
                allowBigInteger();
                assertEquals("0", bigBinaryToString(multiplyResult));
                assertEquals("0", bigBinaryToString(bigBinary1));
                assertEquals(bigBinaryToString(bigBinary2), buildBigInteger(bits2, p2).toString(2));

            }
        } catch (Exception e) {
            handleException(e);
        } catch (AssertionError ae) {
            fail(assertionFailMessage);
        }
    }

    private String bigBinaryToString(Object bigBinary) {
        banBigInteger();
        String result = bigBinary == null ? null : bigBinary.toString();
        allowBigInteger();

        return result;
    }

    private static void bitsRandomMutation(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (generator.nextBoolean()) {
                bits[i] = 1;
            } else {
                bits[i] = 0;
            }
        }
    }

    @AfterEach
    public void resetSecurityManager() {
        allowBigInteger();


    }
}