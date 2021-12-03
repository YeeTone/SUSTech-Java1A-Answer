import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestLocalQ3Airline {
    private static Class<?> searchClazz;
    private static Class<?> searchPlanClazz;
    private static Class<?> flightClazz;

    private static Class<?> airlineClazz;
    private static Class<?> sustechAirlineClazz;
    private static Constructor<?> sustechAirlineConstructor;
    private static Constructor<?> flightConstructor;
    private static Method addFlightMethod;
    private static Method getFlightInfoMethod;
    private static Method getFlightRouteMethod;
    private static Method removeFlightMethod;
    private static Method getAllFlightsAboutDepartPortMethod;
    private static Method isContainsLineMethod;
    private static Method isRoundTripMethod;
    //private Method setSearchResourceMethod;
    private static Method searchAllRoutesMethod;
    private static Method searchBestRouteMethod;

    private static final Map<String, Object> searchPlanMap = new HashMap<>();

    private Object sustechAirlineObject;

    private static final String[][] flights =
            {
                    {"g973", "a", "d", "15", "24", "18", "37", "65394",},
                    {"Y854", "b", "p", "03", "51", "22", "24", "108155",},
                    {"B664", "c", "Y", "22", "53", "23", "29", "51023",},
                    {"I379", "d", "a", "04", "43", "4", "58", "121571",},
                    {"c496", "a", "J", "02", "17", "11", "55", "25314",},
                    {"m714", "b", "S", "03", "3", "10", "06", "100797",},
                    {"U611", "c", "f", "09", "21", "16", "2", "32939",},
                    {"S840", "d", "F", "13", "04", "23", "29", "59875",},
                    {"i844", "a", "P", "18", "52", "22", "40", "72572",},
                    {"m105", "b", "x", "13", "16", "15", "53", "11592",},
                    {"u528", "c", "M", "23", "27", "23", "27", "14841",},
                    {"P179", "d", "w", "11", "57", "21", "33", "50892",},
                    {"C595", "a", "W", "10", "44", "16", "10", "13269",},
                    {"s228", "b", "F", "4", "40", "09", "57", "105910",},
                    {"e804", "c", "z", "20", "23", "21", "56", "60360",},
                    {"h773", "d", "S", "02", "27", "8", "03", "6047",},
                    {"J629", "a", "O", "10", "8", "22", "30", "67414",},
                    {"B683", "b", "W", "11", "51", "21", "05", "64506",},
                    {"B506", "c", "a", "10", "33", "12", "45", "24363",},
                    {"g973", "a", "d", "150", "240", "180", "370", "653940",},
                    {"Y854", "a", "d", "03", "51", "22", "24", "108155",},
                    {"B664", "b", "P", "22", "53", "23", "29", "51023",},
            };

    private static final String[] flightsString = {
            "g973 [a -> d] 15:24 -> 18:37 (65394)",
            "Y854 [b -> p] 03:51 -> 22:24 (108155)",
            "B664 [c -> Y] 22:53 -> 23:29 (51023)",
            "I379 [d -> a] 04:43 -> 04:58 (121571)",
            "c496 [a -> J] 02:17 -> 11:55 (25314)",
            "m714 [b -> S] 03:03 -> 10:06 (100797)",
            "U611 [c -> f] 09:21 -> 16:02 (32939)",
            "S840 [d -> F] 13:04 -> 23:29 (59875)",
            "i844 [a -> P] 18:52 -> 22:40 (72572)",
            "m105 [b -> x] 13:16 -> 15:53 (11592)",
            "u528 [c -> M] 23:27 -> 23:27 (14841)",
            "P179 [d -> w] 11:57 -> 21:33 (50892)",
            "C595 [a -> W] 10:44 -> 16:10 (13269)",
            "s228 [b -> F] 04:40 -> 09:57 (105910)",
            "e804 [c -> z] 20:23 -> 21:56 (60360)",
            "h773 [d -> S] 02:27 -> 08:03 (6047)",
            "J629 [a -> O] 10:08 -> 22:30 (67414)",
            "B683 [b -> W] 11:51 -> 21:05 (64506)",
            "B506 [c -> a] 10:33 -> 12:45 (24363)",
    };


    @BeforeAll
    public static void checkClassDefinition() throws Throwable {
        searchPlanMap.clear();
        checkExist();
        checkType();
        checkModifier();
        checkEnum();

    }

    private static void checkExist() throws Throwable {
        searchClazz = Class.forName("Search");
        searchPlanClazz = Class.forName("SearchPlan");
        airlineClazz = Class.forName("Airline");
        sustechAirlineClazz = Class.forName("SustechAirline");
        flightClazz = Class.forName("Flight");

        sustechAirlineConstructor = sustechAirlineClazz.getDeclaredConstructor();
        flightConstructor = flightClazz.getDeclaredConstructor(String.class);

        addFlightMethod = airlineClazz.getDeclaredMethod("addFlight", String.class);
        getFlightInfoMethod = airlineClazz.getDeclaredMethod("getFlightInfo", String.class);
        getFlightRouteMethod = airlineClazz.getDeclaredMethod("getFlightRoute");
        removeFlightMethod = airlineClazz.getDeclaredMethod("removeFlight", String.class);
        getAllFlightsAboutDepartPortMethod = airlineClazz.getDeclaredMethod("getAllFlightsAboutDepartPort", String.class);
        isContainsLineMethod = airlineClazz.getDeclaredMethod("isContainsFlight", String.class, String.class);
        isRoundTripMethod = airlineClazz.getDeclaredMethod("isRoundTrip", String.class, String.class);
        searchAllRoutesMethod = airlineClazz.getDeclaredMethod("searchAllRoutes", String.class, String.class, searchClazz);
        searchBestRouteMethod = airlineClazz.getDeclaredMethod("searchBestRoute", String.class, String.class, searchClazz, searchPlanClazz);

    }

    private static void checkType() {
        Method[] methods = {addFlightMethod, getFlightInfoMethod, getFlightRouteMethod, removeFlightMethod,
                getAllFlightsAboutDepartPortMethod, isContainsLineMethod, isRoundTripMethod,
                searchAllRoutesMethod, searchBestRouteMethod};

        Class<?>[] classes = {
                void.class, String.class, List.class, boolean.class,
                List.class, boolean.class, boolean.class,
                List.class, String.class
        };

        for (int i = 0; i < methods.length; i++) {
            assertSame(methods[i].getReturnType(), classes[i], "The return type is Wrong!");
        }
    }

    private static void checkModifier() {
        Method[] methods = {addFlightMethod, getFlightInfoMethod, getFlightRouteMethod,
                removeFlightMethod, getAllFlightsAboutDepartPortMethod, isContainsLineMethod, isRoundTripMethod,
                /* setSearchResourceMethod,*/ searchAllRoutesMethod, searchBestRouteMethod};

        for (Method m : methods) {
            assertTrue(Modifier.isPublic(m.getModifiers()), "The method should be public!");
            assertFalse(Modifier.isStatic(m.getModifiers()), "The method cannot be static!");
            assertFalse(Modifier.isFinal(m.getModifiers()), "The method cannot be final!");
            assertTrue(Modifier.isAbstract(m.getModifiers()), "The method cannot be abstract!");
            m.setAccessible(true);
        }

        assertTrue(airlineClazz.isInterface(), "Airline must be an interface!");
        assertFalse(Modifier.isAbstract(sustechAirlineClazz.getModifiers()), "Airline is not an abstract class!");
        assertTrue(Modifier.isAbstract(searchClazz.getModifiers()), "Search should be an abstract class!");
        assertTrue(searchPlanClazz.isEnum(), "SearchPlan should be an Enum!");

        assertSame(sustechAirlineClazz.getInterfaces()[0], airlineClazz, "SustechAirline Class must implement the interface Airline!");
    }

    private static void checkEnum() throws Throwable {
        Method valuesMethod = searchPlanClazz.getDeclaredMethod("values");

        Object[] enums = (Object[]) valuesMethod.invoke(null);

        Arrays.sort(enums, Comparator.comparing(Object::toString));

        assertEquals(2, enums.length, "There can only have 2 instances of enum SearchPlan");
        assertTrue(enums[0].toString().equals("LESS_PRICE")
                        &&
                        enums[1].toString().equals("LESS_TIME"),
                "The name should be LESS_TIME and LESS_PRICE!");

        for (Object e : enums) {
            searchPlanMap.put(e.toString(), e);
        }
    }


    @BeforeEach
    public void addAllFlights() throws Throwable {
        sustechAirlineObject = sustechAirlineConstructor.newInstance();
        for (String[] s : flights) {
            String input = String.format("%s %s %s %s:%s %s:%s %s", s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
            addFlightMethod.invoke(sustechAirlineObject, input);
        }
    }


    @Test
    @Timeout(5000)
    public void testAddFlight() throws Throwable {
        for (int i = 0; i < flightsString.length; i++) {
            String flightNo = flights[i][0];
            Object result = getFlightInfoMethod.invoke(sustechAirlineObject, flightNo);
            assertEquals(flightsString[i], result.toString());
        }
    }

    @Test
    @Timeout(5000)
    public void testGetFlight() throws Throwable {
        Object result = getFlightInfoMethod.invoke(sustechAirlineObject, "CS102A");
        assertEquals(null, result);
    }


    @Test
    @Timeout(5000)
    public void testGetFlightRoute() throws Throwable {
        Object list = getFlightRouteMethod.invoke(sustechAirlineObject);
        assertEquals("[a->J O P W d, b->F S W p x, c->M Y a f z, d->F S a w]", list.toString());
    }

    @Test
    @Timeout(5000)
    public void testRemoveFlight() throws Throwable {
        Object result = removeFlightMethod.invoke(sustechAirlineObject, "CS102A");
        assertEquals(false, result);
        result = removeFlightMethod.invoke(sustechAirlineObject, "g973");
        assertEquals(true, result);
        result = getFlightInfoMethod.invoke(sustechAirlineObject, "g973");
        assertEquals(null, result);
    }

    @Test
    @Timeout(5000)
    public void testGetAllFlightsAboutDepartPort() throws Throwable {
        Object result = getAllFlightsAboutDepartPortMethod.invoke(sustechAirlineObject, "a");
        assertEquals("[C595 [a -> W] 10:44 -> 16:10 (13269), " +
                        "J629 [a -> O] 10:08 -> 22:30 (67414), " +
                        "c496 [a -> J] 02:17 -> 11:55 (25314), " +
                        "g973 [a -> d] 15:24 -> 18:37 (65394), " +
                        "i844 [a -> P] 18:52 -> 22:40 (72572)]"
                , result.toString());
    }

    @Test
    @Timeout(5000)
    public void testIsContainsFlight() throws Throwable{
        Object result = isContainsLineMethod.invoke(sustechAirlineObject, "a", "d");
        assertEquals(true, result);
        result = isContainsLineMethod.invoke(sustechAirlineObject, "a", "Sustech");
        assertEquals(false, result);
    }

    @Test
    @Timeout(5000)
    public void testIsRoundTrip() throws Throwable{
        Object result = isRoundTripMethod.invoke(sustechAirlineObject, "a", "d");
        assertEquals(true, result);
        result = isRoundTripMethod.invoke(sustechAirlineObject, "d", "a");
        assertEquals(true, result);
        result = isRoundTripMethod.invoke(sustechAirlineObject, "d", "w");
        assertEquals(false, result);
    }

    @Test
    @Timeout(5000)
    public void testCombination() throws Throwable{
        final String[][] extra =
                {
                        {"ex1", "p", "b", "03", "51", "22", "24", "108155",},
                        {"ex2", "Y", "c", "22", "53", "23", "29", "51023",},
                };
        for (String[] s : extra) {
            String input = String.format("%s %s %s %s:%s %s:%s %s", s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
            addFlightMethod.invoke(sustechAirlineObject, input);
        }
        Object list = getFlightRouteMethod.invoke(sustechAirlineObject);
        assertEquals("[Y->c, a->J O P W d, b->F S W p x, c->M Y a f z, d->F S a w, p->b]", list.toString());
        Object result = isRoundTripMethod.invoke(sustechAirlineObject, "c", "Y");
        assertEquals(true, result);
        result = removeFlightMethod.invoke(sustechAirlineObject, "ex1");
        assertEquals(true, result);
        result = isRoundTripMethod.invoke(sustechAirlineObject, "c", "Y");
        assertEquals(true, result);
    }


}