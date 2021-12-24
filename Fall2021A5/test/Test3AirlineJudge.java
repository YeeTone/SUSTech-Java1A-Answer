import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Test3AirlineJudge {
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

    @BeforeAll
    public static void checkClassDefinition() throws Throwable {
        searchPlanMap.clear();
        checkExist();
        checkType();
        checkModifier();
        checkEnum();
    }

    @BeforeEach
    public void resetObject() throws Throwable {
        sustechAirlineObject = sustechAirlineConstructor.newInstance();
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
        //setSearchResourceMethod = airlineClazz.getDeclaredMethod("setSearchResource", Search.class);
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

    @Test
    public void addFlight01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"B101", "B", "A", "2", "0", "3", "35", "600"},
                    {"A103", "A", "C", "0", "30", "2", "1", "900"},
                    {"B103", "B", "C", "4", "30", "5", "03", "1250"},
                    {"C101", "C", "A", "3", "01", "3", "31", "1500"},
                    {"C102", "C", "B", "6", "2", "7", "5", "200"}
            };
            addFlights(testcases);
            checkFlights(testcases);

            String[][] duplicateCases = {
                    {"A102", "A", "C", "11", "45", "14", "0", "1000"},
                    {"B101", "B", "C", "19", "19", "8", "10", "600"},
                    {"A103", "A", "B", "11", "45", "14", "0", "900"},
                    {"B103", "B", "A", "19", "19", "8", "10", "1250"},
                    {"C101", "C", "B", "11", "45", "14", "0", "1500"},
                    {"C102", "C", "A", "19", "19", "8", "10", "200"}
            };
            addFlights(duplicateCases);
            checkFlights(testcases);

            checkFlightRoutes(testcases);
        });

    }

    private void checkFlightRoutes(String[][] testcases) throws Throwable {
        String[] result = buildFlightRoutes(testcases);
        List<String> expected = Arrays.asList(result);

        assertEquals(expected, getFlightRouteMethod.invoke(sustechAirlineObject));
    }

    private String[] buildFlightRoutes(String[][] testcases) {
        Map<String, List<String>> map = new HashMap<>();

        for (String[] fn : testcases) {
            String depart = fn[1];
            String arrive = fn[2];

            if (!map.containsKey(depart)) {
                map.put(depart, new ArrayList<>());
            }

            map.get(depart).add(arrive);
        }

        List<Map.Entry<String, List<String>>> list = new ArrayList<>(map.entrySet());

        list.sort(Map.Entry.comparingByKey());

        for (Map.Entry<String, List<String>> entry : list) {
            entry.getValue().sort(Comparator.naturalOrder());
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : list) {
            if (entry.getValue().isEmpty()) {
                continue;
            }

            String b = entry.getKey() + "->" +
                    String.join(" ", entry.getValue());

            result.add(b);
        }
        String[] answer = new String[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private void addFlights(String[][] testcases) throws Throwable {
        Random r = new Random();
        testcasesModify(testcases, r);
        for (String[] fn : testcases) {
            addFlightMethod.invoke(sustechAirlineObject, String.format("%s %s %s %s:%s %s:%s %s",
                    fn[0], fn[1],
                    fn[2], fn[3],
                    fn[4], fn[5],
                    fn[6], fn[7]));
        }
    }

    private void testcasesModify(String[][] testcases, Random r) {
        for (String[] fn : testcases) {
            for (int j = 3; j <= 6; j++) {
                if (r.nextBoolean()) {
                    fn[j] = String.format("%02d", Integer.parseInt(fn[j]));
                }
            }
        }
    }

    @Test
    public void getFlightInfo01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"B103", "B", "C", "1", "30", "2", "30", "1000"},
                    {"C104", "C", "D", "3", "0", "4", "0", "1000"},
                    {"D103", "D", "C", "4", "30", "5", "30", "1000"},
                    {"C102", "C", "B", "6", "0", "7", "0", "1000"},
                    {"B101", "B", "A", "7", "30", "8", "30", "1000"}
            };

            addFlights(testcases);
            checkFlights(testcases);

            String[][] duplicated = {
                    {"A102", "B", "C", "0", "0", "1", "0", "1000"},
                    {"B103", "C", "D", "1", "30", "2", "30", "1000"},
                    {"C104", "D", "A", "3", "0", "4", "0", "1000"},
                    {"D103", "A", "D", "4", "30", "5", "30", "1000"},
                    {"C102", "D", "C", "6", "0", "7", "0", "1000"},
                    {"B101", "C", "B", "7", "30", "8", "30", "1000"}
            };

            addFlights(duplicated);
            checkFlights(testcases);

            String[] invalids = {"A101", "B102", "C103", "D104"};
            checkFlightInfo_invalid(invalids);
        });


    }

    private void checkFlights(String[][] testcases) throws IllegalAccessException, InvocationTargetException {
        for (String[] fn : testcases) {
            Object o = getFlightInfoMethod.invoke(sustechAirlineObject, fn[0]);
            String expected = String.format("%s [%s -> %s] %02d:%02d -> %02d:%02d (%d)",
                    fn[0], fn[1], fn[2], Integer.parseInt(fn[3]), Integer.parseInt(fn[4]), Integer.parseInt(fn[5])
                    , Integer.parseInt(fn[6]), Integer.parseInt(fn[7]));
            assertEquals(expected, o.toString(), "Your Answer is Wrong!");
        }
    }

    private void checkFlightInfo_invalid(String[] invalids) throws Throwable {
        for (String inv : invalids) {
            assertNull(getFlightInfoMethod.invoke(sustechAirlineObject, inv), "Your Answer is Wrong!");
        }
    }

    @Test
    public void getFlightRoute01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"C304", "C", "D", "3", "0", "4", "0", "1000"},
                    {"B204", "B", "D", "2", "0", "3", "0", "1000"},
                    {"B203", "B", "C", "1", "30", "2", "30", "1000"},
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"A104", "A", "D", "1", "0", "2", "0", "1000"},
                    {"A103", "A", "C", "0", "30", "1", "30", "1000"},
            };

            addFlights(testcases);
            checkFlights(testcases);

            String[][] duplicate = {{"C304", "A", "B", "3", "0", "4", "0", "1000"},
                    {"B204", "A", "C", "2", "0", "3", "0", "1000"},
                    {"B203", "D", "A", "1", "30", "2", "30", "1000"},
                    {"A102", "C", "D", "0", "0", "1", "0", "1000"},
                    {"A104", "C", "B", "1", "0", "2", "0", "1000"},
                    {"A103", "C", "A", "0", "30", "1", "30", "1000"},
            };

            addFlights(duplicate);
            checkFlights(testcases);

            checkFlightRoutes(testcases);
        });



    }

    @Test
    public void getFlightRoute02() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"B203", "B", "C", "1", "30", "2", "30", "1000"},
                    {"C304", "C", "D", "3", "0", "4", "0", "1000"},
                    {"D405", "D", "E", "4", "30", "5", "30", "1000"},
                    {"E501", "E", "A", "6", "0", "7", "0", "1000"}
            };
            addFlights(testcases);
            checkFlights(testcases);

            String[][] duplicate = {
                    {"A102", "W", "X", "0", "0", "1", "0", "1000"},
                    {"B203", "X", "Y", "1", "30", "2", "30", "1000"},
                    {"C304", "Y", "Z", "3", "0", "4", "0", "1000"},
                    {"D405", "Z", "M", "4", "30", "5", "30", "1000"},
                    {"E501", "M", "W", "6", "0", "7", "0", "1000"}
            };
            addFlights(testcases);
            checkFlights(testcases);

            checkFlightRoutes(testcases);
        });


    }

    @Test
    public void removeFlight01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"A103", "A", "C", "0", "30", "1", "30", "1000"},
                    {"A104", "A", "D", "1", "0", "2", "0", "1000"},
                    {"B203", "B", "C", "1", "30", "2", "30", "1000"},
                    {"B204", "B", "D", "1", "0", "2", "0", "1000"},
                    {"C305", "C", "E", "12", "0", "13", "30", "3000"},
                    {"D403", "D", "C", "2", "30", "3", "30", "1000"},
            };

            String[][] afterRemoved = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    //{"A103","A","C","0","30","1","30","1000"},
                    //{"A104","A","D","1","0","2","0","1000"},
                    {"B203", "B", "C", "1", "30", "2", "30", "1000"},
                    //{"B204","B","D","1","0","2","0","1000"},
                    {"C305", "C", "E", "12", "0", "13", "30", "3000"},
                    {"D403", "D", "C", "2", "30", "3", "30", "1000"},
            };

            addFlights(testcases);
            checkFlights(testcases);

            String[][] duplicate = {
                    {"A102", "A1", "B2", "0", "0", "1", "0", "1000"},
                    {"A103", "A1", "C3", "0", "30", "1", "30", "1000"},
                    {"A104", "A1", "D4", "1", "0", "2", "0", "1000"},
                    {"B203", "B2", "C3", "1", "30", "2", "30", "1000"},
                    {"B204", "B2", "D4", "1", "0", "2", "0", "1000"},
                    {"C305", "C3", "E5", "12", "0", "13", "30", "3000"},
                    {"D403", "D4", "C3", "2", "30", "3", "30", "1000"},
            };

            addFlights(duplicate);
            checkFlights(testcases);

            String[] removed = {"B204", "A103", "A104"};
            checkRemove_valid(removed);

            checkFlights(afterRemoved);

            String[] invalids = {"A101", "B102", "C103", "D104", "B204", "A103", "A104"};
            checkFlightInfo_invalid(invalids);
            checkRemove_invalid(invalids);
        });



    }

    private void checkRemove_valid(String[] removed) throws Throwable {
        for (String r : removed) {
            assertTrue((boolean) removeFlightMethod.invoke(sustechAirlineObject, r));
            assertFalse((boolean) removeFlightMethod.invoke(sustechAirlineObject, r));
        }
    }

    private void checkRemove_invalid(String[] invalids) throws Throwable {
        for (String r : invalids) {
            assertFalse((boolean) removeFlightMethod.invoke(sustechAirlineObject, r));
        }
    }

    @Test
    public void getAllFlightsAboutDepartPort01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"A103", "A", "C", "0", "30", "1", "30", "1000"},
                    {"A104", "A", "D", "1", "0", "2", "0", "1000"},
                    {"A105", "A", "E", "1", "30", "2", "30", "1000"},
                    {"A106", "A", "F", "2", "0", "3", "0", "1000"},
                    {"A107", "A", "G", "2", "30", "3", "30", "1000"},
                    {"B203", "B", "C", "1", "30", "2", "30", "1000"},
                    {"C304", "C", "D", "2", "0", "3", "0", "1000"},
                    {"D405", "D", "E", "2", "30", "3", "30", "1000"},
                    {"E506", "E", "F", "3", "0", "4", "0", "1000"},
                    {"F607", "F", "G", "3", "30", "4", "30", "1000"},
                    {"G702", "G", "B", "4", "0", "5", "0", "1000"}
            };

            addFlights(testcases);
            checkDepartPorts(testcases);
        });

    }

    private void checkDepartPorts(String[][] testcases) throws Throwable {
        Map<String, List<Object>> allFights = buildAllFights(testcases);
        Set<String> departs = new HashSet<>();
        for (String[] fn : testcases) {
            departs.add(fn[1]);
        }

        for (String d : departs) {
            List<Object> expected = allFights.get(d);
            assertNotNull(expected);
            List<?> actually = (List<?>) getAllFlightsAboutDepartPortMethod.invoke(sustechAirlineObject, d);
            assertEquals(expected.toString(), actually.toString());

        }
    }

    private Map<String, List<Object>> buildAllFights(String[][] testcases) throws Throwable {
        Map<String, List<Object>> result = new HashMap<>();

        for (String[] fn : testcases) {
            String depart = fn[1];

            if (!result.containsKey(depart)) {
                result.put(depart, new ArrayList<>());
            }

            String flightString = String.format("%s %s %s %02d:%02d %02d:%02d %d",
                    fn[0], fn[1], fn[2], Integer.parseInt(fn[3]), Integer.parseInt(fn[4]), Integer.parseInt(fn[5])
                    , Integer.parseInt(fn[6]), Integer.parseInt(fn[7]));

            Object o = flightConstructor.newInstance(flightString);
            result.get(depart).add(o);
        }

        Field flightNoField = flightClazz.getDeclaredField("flightNo");
        flightNoField.setAccessible(true);

        for (List<Object> os : result.values()){
            os.sort((o1, o2) -> {
                try {
                    return flightNoField.get(o1).toString().compareTo(flightNoField.get(o2).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return 0;
                }
            });
        }

        return result;
    }

    @Test
    public void isContainsLine01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A103", "A", "C", "0", "0", "1", "0", "1000"},
                    {"B201", "B", "A", "4", "30", "5", "30", "1000"},
                    {"C304", "C", "D", "1", "30", "2", "30", "1000"},
                    {"D402", "D", "B", "3", "0", "4", "0", "1000"},
                    {"C305", "C", "E", "12", "0", "13", "0", "2000"},
                    {"E506", "E", "F", "13", "30", "14", "30", "2000"},
                    {"F604", "F", "D", "15", "0", "16", "0", "2000"},
                    {"D403", "D", "C", "16", "30", "17", "30", "2000"},
            };
            addFlights(testcases);
            checkFlights(testcases);

            String[][] duplicate = {
                    {"D403", "C", "A", "0", "0", "1", "0", "1000"},
                    {"F604", "A", "B", "4", "30", "5", "30", "1000"},
                    //{"E506", "D", "C", "1", "30", "2", "30", "1000"},
                    {"C305", "B", "D", "3", "0", "4", "0", "1000"},
                    {"D402", "E", "C", "12", "0", "13", "0", "2000"},
                    {"C304", "F", "E", "13", "30", "14", "30", "2000"},
                    {"B201", "D", "F", "15", "0", "16", "0", "2000"},
                    //{"A103", "C", "D", "16", "30", "17", "30", "2000"},
            };

            addFlights(duplicate);
            checkFlights(testcases);
            checkContains(testcases, true);
            checkContains(duplicate, false);

            assertTrue((boolean) isContainsLineMethod.invoke(sustechAirlineObject, "C", "D"));
            assertTrue((boolean) isContainsLineMethod.invoke(sustechAirlineObject, "D", "C"));

            removeAllFlights(testcases);

            checkContains(testcases, false);

            checkDepartPortEmpty(new String[]{"A","B","C","D","E","F"});
        });


    }

    private void checkDepartPortEmpty(String[] emptyPorts) throws Throwable{
        for (String ep:emptyPorts){
            assertTrue(((List<?>)getAllFlightsAboutDepartPortMethod.invoke(sustechAirlineObject,ep)).isEmpty());
        }
    }

    @Test
    public void isRoundTrip01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"B201", "B", "A", "12", "0", "13", "0", "1000"},
                    {"B203", "B", "C", "0", "30", "1", "30", "1000"},
                    {"C302", "C", "B", "12", "30", "13", "30", "1000"},
                    {"C304", "C", "D", "1", "0", "2", "0", "1000"},
                    {"D403", "D", "C", "13", "0", "14", "0", "1000"},
                    {"D405", "D", "E", "1", "30", "2", "30", "1000"},
                    {"E504", "E", "D", "13", "30", "14", "30", "1000"},
                    {"E506", "E", "F", "2", "0", "3", "0", "1000"},
                    {"F605", "F", "E", "14", "0", "15", "0", "1000"},
                    {"F601", "F", "A", "2", "30", "3", "30", "1000"},
                    {"A106", "A", "F", "14", "30", "15", "30", "1000"},
            };

            addFlights(testcases);
            checkFlights(testcases);
            checkContains(testcases, true);

            String[] allPorts = "ABCDEF".split("");
            checkRoundTrip(allPorts, true);

            String[][] removedEven = selectEvenElements(testcases);

            removeAllFlights(removedEven);
            checkRoundTrip(allPorts, false);

            //Rollback
            addFlights(testcases);
            checkFlights(testcases);
            checkContains(testcases, true);

            String[][] removedBiggerHalf = selectHalfElements(testcases);

            removeAllFlights(removedBiggerHalf);
            String[][] rounded = {
                    {"A", "B"},
                    {"B", "C"},
                    {"C", "D"},
            };
            for (String[] r : rounded) {
                checkRoundTrip(r, true);
            }

            String[] noRoundedPort = "DEAF".split("");
            checkRoundTrip(noRoundedPort, false);

            String[][] singleAdded = {
                    {"D405", "D", "E", "1", "30", "2", "30", "1000"},
                    {"F605", "F", "E", "14", "0", "15", "0", "1000"},
                    {"F601", "F", "A", "2", "30", "3", "30", "1000"},
            };

            addFlights(singleAdded);
            checkContains(singleAdded, true);
            checkRoundTrip(noRoundedPort, false);
        });

    }

    private void checkContains(String[][] testcases, boolean expected) throws Throwable {
        for (String[] fn : testcases) {
            assertEquals(expected, isContainsLineMethod.invoke(sustechAirlineObject, fn[1], fn[2]));
            //assertFalse(expected==(boolean) isContainsLineMethod.invoke(sustechAirlineObject,fn[1],fn[2]));
        }
    }

    private void removeAllFlights(String[][] testcases) throws Throwable {
        for (String[] fn : testcases) {
            assertTrue((boolean) removeFlightMethod.invoke(sustechAirlineObject, fn[0]));
        }
    }

    private void checkRoundTrip(String[] allPorts, boolean expected) throws Throwable {
        for (int i = 0; i < allPorts.length; i++) {
            int j = (i + 1) % allPorts.length;
            assertEquals(expected, isRoundTripMethod.invoke(sustechAirlineObject, allPorts[i], allPorts[j]));
        }
    }

    private String[][] selectEvenElements(String[][] testcases) {
        String[][] removedEven = new String[testcases.length / 2][];
        for (int i = 0, j = 0; i < testcases.length; i += 2, j += 1) {
            removedEven[j] = testcases[i];
        }

        return removedEven;
    }

    private String[][] selectHalfElements(String[][] testcases) {
        String[][] removedHalf = new String[testcases.length / 2][];
        int j = 6;
        for (int i = 0; j < testcases.length; i++, j++) {
            removedHalf[i] = testcases[j];
        }

        return removedHalf;
    }

    @Test
    public void combination01() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102", "A", "B", "0", "0", "1", "0", "1000"},
                    {"A103", "A", "C", "0", "0", "1", "0", "1000"},
                    {"A104", "A", "D", "0", "0", "1", "0", "1000"},
                    {"B205", "B", "E", "1", "30", "2", "30", "1000"},
                    {"B206", "B", "F", "1", "30", "2", "30", "1000"},
                    {"C307", "C", "G", "1", "30", "2", "30", "1000"},
                    {"C308", "C", "H", "1", "30", "2", "30", "1000"},
                    {"D409", "D", "I", "3", "0", "4", "0", "1000"},
                    {"D4010", "D", "J", "3", "0", "4", "0", "1000"},
            };

            addFlights(testcases);
            checkFlights(testcases);
            checkContains(testcases, true);

            String[][] duplicate = {
                    {"A102", "B", "A", "0", "0", "1", "0", "1000"},
                    {"A103", "C", "A", "0", "0", "1", "0", "1000"},
                    {"A104", "D", "A", "0", "0", "1", "0", "1000"},
                    {"B205", "E", "B", "1", "30", "2", "30", "1000"},
                    {"B206", "F", "B", "1", "30", "2", "30", "1000"},
                    {"C307", "G", "C", "1", "30", "2", "30", "1000"},
                    {"C308", "H", "C", "1", "30", "2", "30", "1000"},
                    {"D409", "I", "D", "3", "0", "4", "0", "1000"},
                    {"D4010", "J", "D", "3", "0", "4", "0", "1000"},
            };

            addFlights(duplicate);
            checkFlights(testcases);
            checkContains(duplicate, false);

            String[][] noRounded = {
                    {"A", "B"},
                    {"A", "C"},
                    {"A", "D"},
                    {"B", "E"},
                    {"B", "F"},
                    {"C", "G"},
                    {"C", "H"},
                    {"D", "I"},
                    {"D", "J"},
            };
            for (String[] nr : noRounded) {
                checkRoundTrip(nr, false);
            }

            checkFlightRoutes(testcases);

            String[][] toRemove = {
                    //{"A102", "B", "A", "0", "0", "1", "0", "1000"},
                    //{"A103", "C", "A", "0", "0", "1", "0", "1000"},
                    //{"A104", "D", "A", "0", "0", "1", "0", "1000"},
                    {"B205", "B", "E", "1", "30", "2", "30", "1000"},
                    {"B206", "B", "F", "1", "30", "2", "30", "1000"},
                    {"C307", "C", "G", "1", "30", "2", "30", "1000"},
                    {"C308", "C", "H", "1", "30", "2", "30", "1000"},
                    {"D409", "D", "I", "3", "0", "4", "0", "1000"},
                    {"D4010", "D", "J", "3", "0", "4", "0", "1000"},
            };
            String[] invalids = selectFlightNo(toRemove);

            checkDepartPorts(testcases);
            checkContains(toRemove, true);
            checkRemove_valid(invalids);
            checkRemove_invalid(invalids);
            checkContains(toRemove, false);

            checkFlightInfo_invalid(invalids);
        });

    }

    @Test
    public void combination02() {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            String[][] testcases = {
                    {"A102","A","B","0","0","1","0","2000"},
                    {"A104","A","D","0","0","1","0","2000"},
                    {"A106","A","F","0","0","1","0","2000"},
                    {"A108","A","H","0","0","1","0","2000"},

                    {"C301","C","A","3","0","4","0","2000"},
                    {"E501","E","A","3","0","4","0","2000"},
                    {"G701","G","A","3","0","4","0","2000"},
                    {"I901","I","A","3","0","4","0","2000"},

                    {"B203","B","C","1","30","2","30","1000"},
                    {"C304","C","D","3","0","4","0","1000"},
                    {"D405","D","E","4","30","5","30","1000"},
                    {"E506","E","F","6","0","7","0","1000"},
                    {"F607","F","G","7","30","8","30","1000"},
                    {"G708","G","H","9","0","10","0","1000"},
                    {"H809","H","I","10","30","11","30","1000"},
                    {"I902","I","B","12","0","13","0","1000"},
            };

            addFlights(testcases);
            checkFlights(testcases);
            checkContains(testcases,true);

            String[][] reverse = reverseEdge(testcases);

            addFlights(reverse);
            checkFlights(testcases);
            checkContains(reverse,false);

            checkRoundTrip("ABCDEFGHI".split(""),false);

            changeReverseName(reverse);
            addFlights(reverse);
            checkFlights(reverse);
            checkContains(reverse,true);
            checkRoundTrip("ABCDEFGHI".split(""),true);

            String[] selectFlightNo = selectFlightNo(reverse);
            checkRemove_valid(selectFlightNo);

            checkFlightRoutes(testcases);
            checkRemove_invalid(selectFlightNo);

            checkDepartPorts(testcases);
        });
    }

    private String[][] reverseEdge(String[][] cases){
        String[][] reverse = new String[cases.length][cases[0].length];
        for (int i = 0; i < cases.length; i++) {
            System.arraycopy(cases[i], 0, reverse[i], 0, cases[0].length);
            String temp = reverse[i][1];
            reverse[i][1]= reverse[i][2];
            reverse[i][2] = temp;
        }
        return reverse;
    }

    private void changeReverseName(String[][] reverse){
        for (String[] fn: reverse){
            char depart = fn[1].charAt(0), arrive = fn[2].charAt(0);
            fn[0]= String.format("%c%d0%d",fn[0].charAt(0),depart-'A',arrive-'A');
        }
    }

    private String[] selectFlightNo(String[][] testcases){
        String[] result = new String[testcases.length];
        for (int i = 0; i < testcases.length; i++) {
            result[i] = testcases[i][0];
        }

        return result;
    }
}