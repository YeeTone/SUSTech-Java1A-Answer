import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.reflect.*;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Test1SustechTimeJudge {

    private static Class<?> stClazz;
    private static Field hourField;
    private static Field minuteField;
    private static Constructor<?> stConstructor;
    private static Method timeDifferenceMethod;
    private static Method toStringMethod;

    @BeforeAll
    public static void checkClassDefinition() throws Throwable{
        checkExists();
        checkType();
        checkModifier();
    }

    private static void checkExists() throws Throwable{
        stClazz = Class.forName("SustechTime");
        hourField = stClazz.getDeclaredField("hour");
        minuteField = stClazz.getDeclaredField("minute");
        timeDifferenceMethod = stClazz.getDeclaredMethod("timeDifference", stClazz);
        toStringMethod= stClazz.getDeclaredMethod("toString");
        stConstructor = stClazz.getDeclaredConstructor(String.class);
    }

    private static void checkType() throws Throwable{
        assertTrue(int.class==hourField.getType(),"The type of hour field is wrong!");
        assertTrue(int.class==minuteField.getType(),"The type of minute field is wrong!");
        assertTrue(int.class==timeDifferenceMethod.getReturnType(),
                "The return type of timeDifference method is wrong!");
        assertTrue(String.class==toStringMethod.getReturnType(),
                "The return type of toString method is wrong!");
    }

    private static void checkModifier(){
        Field[] fields = {hourField,minuteField};

        for (Field f:fields){
            assertTrue(Modifier.isPrivate(f.getModifiers()),"The field should be private!");
            assertFalse(Modifier.isStatic(f.getModifiers()),"The field cannot be static!");
            assertFalse(Modifier.isFinal(f.getModifiers()),"The field cannot be final!");
            f.setAccessible(true);
        }

        assertTrue(Modifier.isPublic(stConstructor.getModifiers()),"The constructor should be public!");

        Method[] methods = {timeDifferenceMethod,toStringMethod};

        for (Method m:methods){
            assertTrue(Modifier.isPublic(m.getModifiers()),"The method should be public!");
            assertFalse(Modifier.isAbstract(m.getModifiers()),"The method cannot be abstract!");
            assertFalse(Modifier.isFinal(m.getModifiers()),"The method cannot be final!");
            assertFalse(Modifier.isStatic(m.getModifiers()),"The method cannot be static!");
            m.setAccessible(true);
        }
     }

    @Test
    public void constructor01() {
        int cases = 50;
        constructorTest(cases);
    }

    private void constructorTest(int cases) {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = generateTestcases(cases);

            for (int i = 0; i < cases; i++) {
                Object time = stConstructor.newInstance(testcases[i][0]+":"+testcases[i][1]);

                int hour = Integer.parseInt(testcases[i][0]), minute = Integer.parseInt(testcases[i][1]);

                Object h = hourField.get(time);
                assertTrue(h.equals(hour), "Your answer is wrong!");
                Object m = minuteField.get(time);
                assertTrue(m.equals(minute), "Your answer is wrong!");
            }
        });

    }

    @Test
    public void constructor02() {
        int cases = 150;
        constructorTest(cases);
    }

    @Test
    public void constructor03() {
        int cases = 250;
        constructorTest(cases);
    }

    @Test
    public void toString01() {
        int cases = 100;
        toStringTest(cases);
    }

    private void toStringTest(int cases) {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = generateTestcases(cases);

            for (int i = 0; i < cases; i++) {
                Object time1 = stConstructor.newInstance(testcases[i][0]+":"+testcases[i][1]);

                String answer = String.format("%02d:%02d",Integer.parseInt(testcases[i][0]),Integer.parseInt(testcases[i][1]));

                assertTrue(toStringMethod.invoke(time1).equals(answer),"Your answer is wrong!");
            }
        });

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

    @Test
    public void timeDifference01() {
        int cases = 66;
        timeDifferenceTest(cases);
    }

    @Test
    public void timeDifference02() {
        int cases = 166;
        timeDifferenceTest(cases);
    }

    @Test
    public void timeDifference03() {
        int cases = 266;
        timeDifferenceTest(cases);
    }

    private void timeDifferenceTest(int cases) {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = generateTestcases(cases);

            for (int i = 0; i < cases; i+=2) {
                Object time1 = stConstructor.newInstance(testcases[i][0]+":"+testcases[i][1]);
                Object time2 = stConstructor.newInstance(testcases[i+1][0]+":"+testcases[i+1][1]);

                int difference = Math.abs(Integer.parseInt(testcases[i][0])*60 - Integer.parseInt(testcases[i+1][0])*60
                        + Integer.parseInt(testcases[i][1]) - Integer.parseInt(testcases[i+1][1]));

                assertTrue(timeDifferenceMethod.invoke(time1,time2).equals(difference),"Your answer is wrong!");
            }
        });

    }


    private String[][] generateTestcases(int cases){
        String[][] testcases = new String[cases][];
        Random r = new Random();

        for (int i = 0; i < cases; i++) {
            boolean isH0 = r.nextBoolean();
            boolean isM0 = r.nextBoolean();

            int h = r.nextInt(24);
            int m = r.nextInt(60);

            String hour = isH0?String.format("%02d",h):Integer.toString(h);
            String minute = isM0?String.format("%02d",m):Integer.toString(m);

            testcases[i] = new String[]{hour, minute};
        }

        return testcases;
    }

}
