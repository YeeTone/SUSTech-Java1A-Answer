import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Test2FlightJudge {

    private static Class<?> fClazz;
    private static Field flightNoField;
    private static Field departTimeField;
    private static Field arriveTimeField;
    private static Field priceField;
    private static Constructor<?> fConstructor;
    private static Method toStringMethod;

    @BeforeAll
    public static void checkClassDefinition()throws Throwable {
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

        Field[] fields = {flightNoField,departTimeField,arriveTimeField,priceField};
        Class<?>[] classes = {String.class,stClass,stClass,int.class,String.class};

        for (int i = 0;i < fields.length;i++){
            assertTrue(fields[i].getType()==classes[i],"The field type is Wrong!");
        }

        assertTrue(toStringMethod.getReturnType() == String.class,"The method return type is Wrong!");
    }

    private static void checkModifier() {
        Field[] fields = {flightNoField, departTimeField, arriveTimeField, priceField};

        for (Field f : fields) {
            assertTrue(Modifier.isPrivate(f.getModifiers()),"The field should be private!");
            assertFalse(Modifier.isStatic(f.getModifiers()),"The field cannot be static!");
            assertFalse(Modifier.isFinal(f.getModifiers()),"The field cannot be final!");
            f.setAccessible(true);
        }

        assertTrue(Modifier.isPublic(fConstructor.getModifiers()),"The constructor should be public!");

        assertTrue(Modifier.isPublic(toStringMethod.getModifiers()),"The method should be public!");
        assertFalse(Modifier.isStatic(toStringMethod.getModifiers()),"The method cannot be static!");
        assertFalse(Modifier.isAbstract(toStringMethod.getModifiers()),"The method cannot be abstract!");
        assertFalse(Modifier.isFinal(toStringMethod.getModifiers()),"The method cannot be final!");

    }

    @Test
    public void constructor01() {
        int cases = 33;
        constructorTest(cases);
    }

    @Test
    public void constructor02() {
        int cases = 66;
        constructorTest(cases);
    }

    @Test
    public void constructor03() {
        int cases = 99;
        constructorTest(cases);
    }

    private void constructorTest(int cases) {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = generateTestcases(cases);

            for (int i = 0; i < cases; i++) {
                String input = String.format("%s %s %s %s:%s %s:%s %s",
                        testcases[i][0], testcases[i][1],
                        testcases[i][2], testcases[i][3],
                        testcases[i][4], testcases[i][5],
                        testcases[i][6], testcases[i][7]);

                Object flight = fConstructor.newInstance(input);

                assertTrue(flightNoField.get(flight).equals(testcases[i][0]),"Your answer is Wrong!");
                assertTrue(departTimeField.get(flight).toString().equals(
                        String.format("%02d:%02d",Integer.parseInt(testcases[i][3]),Integer.parseInt(testcases[i][4]))),"Your answer is Wrong!");
                assertTrue(arriveTimeField.get(flight).toString().equals(
                        String.format("%02d:%02d",Integer.parseInt(testcases[i][5]),Integer.parseInt(testcases[i][6]))),"Your answer is Wrong!");
                assertTrue(priceField.get(flight).equals(Integer.parseInt(testcases[i][7])),"Your answer is Wrong!");
            }
        });

    }

    @Test
    public void toString01() {
        int cases = 100;
        toStringTest(cases);
    }

    @Test
    public void toString02() {
        int cases = 200;
        toStringTest(cases);
    }

    @Test
    public void toString03() {
        int cases = 300;
        toStringTest(cases);
    }

    private void toStringTest(int cases) {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = generateTestcases(cases);

            for (int i = 0; i < cases; i++) {
                String input = String.format("%s %s %s %s:%s %s:%s %s",
                        testcases[i][0], testcases[i][1],
                        testcases[i][2], testcases[i][3],
                        testcases[i][4], testcases[i][5],
                        testcases[i][6], testcases[i][7]);

                Object flight = fConstructor.newInstance(input);

                String expected = String.format("%s [%s -> %s] %02d:%02d -> %02d:%02d (%d)",
                        testcases[i][0], testcases[i][1], testcases[i][2],
                        Integer.parseInt(testcases[i][3]), Integer.parseInt(testcases[i][4])
                        , Integer.parseInt(testcases[i][5]), Integer.parseInt(testcases[i][6]),
                        Integer.parseInt(testcases[i][7]));

                assertTrue(expected.equals(toStringMethod.invoke(flight)),"Your answer is Wrong!");
            }
        });

    }

    private String[][] generateTestcases(int cases) {
        String[][] testcases = new String[cases][];

        Random r = new Random();
        for (int i = 0; i < cases; i++) {
            String flightNo = randomChar(r) + "" + randomInt(r);
            String depart = Character.toString(randomChar(r));
            String arrive = Character.toString(randomChar(r));
            int[] departTimes = randomTime(r, 0);
            String dH = randomToString(r, departTimes[0]);
            String dM = randomToString(r, departTimes[1]);

            int[] arriveTimes = randomTime(r, departTimes[0] * 60 + departTimes[1]);
            String aH = randomToString(r, arriveTimes[0]);
            String aM = randomToString(r, arriveTimes[1]);

            String price = Integer.toString(r.nextInt(123456) + 100);

            testcases[i] = new String[]{flightNo, depart, arrive, dH, dM, aH, aM, price};

        }

        return testcases;
    }

    private char randomChar(Random r){
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String abcABC =abc+abc.toUpperCase();

        return abcABC.charAt(r.nextInt(abcABC.length()));
    }

    private int randomInt(Random r){
        return 100 + r.nextInt(900);
    }

    private int[] randomTime(Random r,int lowerBound){
        int time =  r.nextInt(23*60+59-lowerBound)+lowerBound;

        return new int[]{time/60, time%60};
    }

    private String randomToString(Random r,int time) {
        return r.nextBoolean() ? String.format("%02d", time) : Integer.toString(time);
    }
}
