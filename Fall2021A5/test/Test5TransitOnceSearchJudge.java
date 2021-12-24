import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class Test5TransitOnceSearchJudge {
    private static final int FEE = 80;
    private static final int TIME = 30;

    private static Class<?> flightClazz;
    private static Class<?> searchPlanClazz;
    private static Class<?> searchClazz;
    private static Class<?> transitOnceSearchClazz;

    private static Constructor<?> sustechAirlineConstructor;
    private static Constructor<?> flightConstructor;

    private static Field AIRPORT_FEE_field;
    private static Field TRANSIT_TIME_field;

    private static Method addFlightMethod;

    private static Method searchRoutesMethod_Search;
    private static Method searchAllRoutesMethod_Airline;
    private static Method searchBestRouteMethod_Search;
    private static Method searchBestRouteMethod_Airline;

    private Object sustechAirlineObject;
    private Object transitOnceSearchObject;

    private static final Map<String, Object> searchPlanMap = new HashMap<>();

    @BeforeAll
    public static void checkClassDefinition() throws Throwable {
        checkExists();
        checkType();
        checkModifier();
        checkEnum();
    }

    @BeforeEach
    public void resetObject() throws Throwable {
        sustechAirlineObject = sustechAirlineConstructor.newInstance();
        transitOnceSearchObject = transitOnceSearchClazz.newInstance();
        checkMethodUsing();
        checkValue();
    }

    private static void checkExists() throws Throwable {
        searchPlanClazz = Class.forName("SearchPlan");
        searchClazz = Class.forName("Search");
        flightClazz = Class.forName("Flight");
        Class<?> airlineClazz = Class.forName("Airline");
        Class<?> sustechAirlineClazz = Class.forName("SustechAirline");
        transitOnceSearchClazz = Class.forName("TransitOnceSearch");

        sustechAirlineConstructor = sustechAirlineClazz.getDeclaredConstructor();
        flightConstructor = flightClazz.getDeclaredConstructor(String.class);

        AIRPORT_FEE_field = searchClazz.getDeclaredField("AIRPORT_FEE");
        TRANSIT_TIME_field = searchClazz.getDeclaredField("TRANSIT_TIME");

        addFlightMethod = airlineClazz.getDeclaredMethod("addFlight", String.class);

        searchAllRoutesMethod_Airline = sustechAirlineClazz.getDeclaredMethod("searchAllRoutes", String.class,
                String.class, searchClazz);
        searchRoutesMethod_Search = searchClazz.getDeclaredMethod("searchRoutes", String.class, String.class);
        searchBestRouteMethod_Airline = sustechAirlineClazz.getDeclaredMethod("searchBestRoute", String.class,
                String.class, searchClazz, searchPlanClazz);
        searchBestRouteMethod_Search = searchClazz.getDeclaredMethod("searchBestRoute", String.class, String.class, searchPlanClazz);
    }

    private static void checkType() throws Throwable {
        Class<?>[] expectedFieldTypes = {int.class, int.class};
        Field[] fields = {AIRPORT_FEE_field, TRANSIT_TIME_field};
        for (int i = 0; i < expectedFieldTypes.length; i++) {
            assertSame(fields[i].getType(), expectedFieldTypes[i]);
        }

        Class<?>[] expectedMethodTypes = {List.class, String.class};
        Method[] methods = {searchRoutesMethod_Search, searchBestRouteMethod_Search};
        for (int i = 0; i < expectedMethodTypes.length; i++) {
            assertSame(methods[i].getReturnType(), expectedMethodTypes[i]);
        }
    }

    private static void checkModifier() throws Throwable {
        Field[] fields = {AIRPORT_FEE_field, TRANSIT_TIME_field};
        for (Field f : fields) {
            assertTrue(Modifier.isProtected(f.getModifiers()));
            assertTrue(Modifier.isFinal(f.getModifiers()));
            assertFalse(Modifier.isStatic(f.getModifiers()));
            f.setAccessible(true);
        }

        assertTrue(Modifier.isAbstract(searchClazz.getModifiers()));
        assertFalse(Modifier.isFinal(searchClazz.getModifiers()));
        assertFalse(Modifier.isAbstract(transitOnceSearchClazz.getModifiers()));

        Method[] methods = {searchRoutesMethod_Search, searchBestRouteMethod_Search};
        for (Method m : methods) {
            assertTrue(Modifier.isAbstract(m.getModifiers()));
            assertTrue(Modifier.isPublic(m.getModifiers()));
            assertFalse(Modifier.isStatic(m.getModifiers()));
            assertFalse(Modifier.isFinal(m.getModifiers()));
        }

        assertEquals(searchClazz, transitOnceSearchClazz.getSuperclass());
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

    private void checkValue() throws Throwable{
        int[] values = {FEE, TIME};
        Field[] fields = {AIRPORT_FEE_field,TRANSIT_TIME_field};
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i],fields[i].get(transitOnceSearchObject));
        }
    }

    private void checkMethodUsing() throws Throwable{
        Search mySearch = new MySearch();
        assertEquals("[NO.0 A->B, NO.1 A->B]",
                (Arrays.toString(((List<?>)(searchAllRoutesMethod_Airline.invoke(sustechAirlineObject,"A","B",mySearch))).toArray())));
        assertEquals("A@B@null",searchBestRouteMethod_Airline.invoke(sustechAirlineObject,"A","B",mySearch,null));
    }


    @Test
    public void combination01() throws Throwable {
        int nodes = 15;
        int cases = 188;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination02() throws Throwable {
        int nodes = 16;
        int cases = 201;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination03() throws Throwable {
        int nodes = 17;
        int cases = 221;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination04() throws Throwable {
        int nodes = 18;
        int cases = 244;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination05() throws Throwable {
        int nodes = 19;
        int cases = 275;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination06() throws Throwable {
        int nodes = 20;
        int cases = 288;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination07() throws Throwable {
        int nodes = 21;
        int cases = 307;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination08() throws Throwable {
        int nodes = 22;
        int cases = 342;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination09() throws Throwable {
        int nodes = 23;
        int cases = 386;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination10() throws Throwable {
        int nodes = 24;
        int cases = 432;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination11() throws Throwable {
        int nodes = 25;
        int cases = 480;
        randomJudge(nodes,cases);
    }

    @Test
    public void combination12() throws Throwable {
        int nodes = 26;
        int cases = 530;
        randomJudge(nodes,cases);
    }

    private void randomJudge(int nodes,int cases) throws Throwable {
        assertTimeoutPreemptively(Duration.ofMillis(3000),()->{
            Set<String> nodeA2Z = generateNodes(nodes);
            String[][] testcases = generateFlights(cases, nodeA2Z);
            addFlights(testcases);
            judge(testcases, nodeA2Z);
        });

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

    private Set<String> generateNodes(int ns) {
        Set<String> nodes = new HashSet<>();
        for (char c = 'A'; c < 'A' + ns; c++) {
            nodes.add(String.valueOf(c));
        }

        return nodes;
    }

    private String[][] generateFlights(int cases, Set<String> nodes) {
        Random r = new Random();

        String[][] flights = new String[cases][];
        int satisfied = 0;
        Map<String, Set<String>> node_toNodes_Map = new HashMap<>();
        Set<String> flightNos = new HashSet<>();
        Set<Integer> priceUsed = new HashSet<>();
        List<String> a2z = new ArrayList<>(nodes);

        for (String n : a2z) {
            node_toNodes_Map.put(n, new HashSet<>());
        }

        while (satisfied < cases) {
            int r1 = r.nextInt(nodes.size());
            int r2 = r1;
            while (r2 == r1) {
                r2 = r.nextInt(nodes.size());
            }

            String from = a2z.get(r1);
            String to = a2z.get(r2);

            boolean duplicate = node_toNodes_Map.get(from).contains(to);

            /*for (String s : node_toNodes_Map.get(from)) {
                if (s.equals(to)) {
                    duplicate = true;
                    break;
                }
            }*/
            if (duplicate) {
                continue;
            }

            String flightNo = from + (from.charAt(0) - 'A' + 1) + "0" + (to.charAt(0) - 'A' + 1);
            if (flightNos.contains(flightNo)) {
                continue;
            }

            int h1 = r.nextInt(24), m1 = r.nextInt(60);
            int h2 = r.nextInt(24), m2 = r.nextInt(60);

            if (h2 * 60 + m2 <= h1 * 60 + m2) {
                continue;
            }

            int price = r.nextInt(10000);

            while (priceUsed.contains(price)) {
                price = r.nextInt(10000);
            }

            flights[satisfied] = new String[]{flightNo, from, to, String.valueOf(h1), String.valueOf(m1),
                    String.valueOf(h2), String.valueOf(m2), String.valueOf(price)};
            satisfied++;
            flightNos.add(flightNo);
            node_toNodes_Map.get(from).add(to);
            priceUsed.add(price);
        }

        return flights;
    }

    private void judge(String[][] testcases, Set<String> nodes) throws Throwable {
        String[] noArray = String.join("", nodes).split("");
        for (int i = 0; i < noArray.length; i++) {
            for (int j = 0; j < i; j++) {
                String depart = noArray[i];
                String arrive = noArray[j];

                checkTransitOnceSearchRoutes(testcases, depart, arrive);
                checkTransitOnceSearchRoutes(testcases, arrive, depart);

                checkTransitOnceBestSearchRoutes(testcases, depart, arrive, "LESS_TIME");
                checkTransitOnceBestSearchRoutes(testcases, arrive, depart, "LESS_TIME");

                checkTransitOnceBestSearchRoutes(testcases, depart, arrive, "LESS_PRICE");
                checkTransitOnceBestSearchRoutes(testcases, arrive, depart, "LESS_PRICE");
            }
        }
    }

    private void checkTransitOnceSearchRoutes(String[][] testcases, String depart, String arrive) throws Throwable {
        List<String> built = buildTransitOnceSearchRoutes(testcases, depart, arrive);

        List<String> actual_airline = AirlineUtil.toModifiableList(
                (List<String>) searchAllRoutesMethod_Airline.invoke(sustechAirlineObject,
                        depart, arrive, transitOnceSearchObject));
        List<String> actual_search = AirlineUtil.toModifiableList(
                (List<String>) searchRoutesMethod_Search.invoke(transitOnceSearchObject, depart, arrive));

        built.sort(Comparator.naturalOrder());
        actual_airline.sort(Comparator.comparing(Object::toString));
        actual_search.sort(Comparator.comparing(Object::toString));

        trimStringList(built);
        trimStringList(actual_airline);
        trimStringList(actual_search);

        assertNotNull(actual_search);
        assertNotNull(actual_airline);

        assertEquals(built, actual_airline);
        assertEquals(built, actual_search);
        assertEquals(actual_airline, actual_search);
    }

    private void trimStringList(List<String> strings){
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i,strings.get(i).trim());
        }
    }

    private void checkTransitOnceBestSearchRoutes(String[][] testcases, String depart, String arrive, String searchPlanName) throws Throwable {
        String bestRoute = buildTransitOnceBestSearchRoutes(testcases, depart, arrive, searchPlanName);
        Object searchPlan = searchPlanMap.get(searchPlanName);

        String actual_airline = (String) searchBestRouteMethod_Airline.
                invoke(sustechAirlineObject, depart, arrive, transitOnceSearchObject, searchPlan);
        String actual_search = (String) searchBestRouteMethod_Search.
                invoke(transitOnceSearchObject, depart, arrive, searchPlan);

        if(actual_airline!=null){
            actual_airline = actual_airline.trim();
        }
        if(actual_search!=null){
            actual_search = actual_search.trim();
        }

        assertEquals(bestRoute, actual_airline);
        assertEquals(bestRoute, actual_search);
        assertEquals(actual_airline, actual_search);
    }

    private List<String> buildTransitOnceSearchRoutes(String[][] testcases, String depart, String arrive) throws Throwable {
        List<String> result = new ArrayList<>();
        Map<String, Object> firstNeighborFlights = new HashMap<>();
        Map<String, Integer> firstNeigtborArriveTime = new HashMap<>();
        for (String[] fn : testcases) {
            if (!fn[1].equals(depart)) {
                continue;
            }

            String format = String.format("%s %s %s %02d:%02d %02d:%02d %d",
                    fn[0], fn[1], fn[2], Integer.parseInt(fn[3]), Integer.parseInt(fn[4]), Integer.parseInt(fn[5])
                    , Integer.parseInt(fn[6]), Integer.parseInt(fn[7]));
            Object flight = flightConstructor.newInstance(format);
            firstNeighborFlights.put(fn[2], flight);

            firstNeigtborArriveTime.put(fn[2], Integer.parseInt(fn[5]) * 60 + Integer.parseInt(fn[6]));
        }

        for (String[] fn : testcases) {
            if (!fn[2].equals(arrive)) {
                continue;
            }

            if (!firstNeighborFlights.containsKey(fn[1])) {
                continue;
            }

            String format = String.format("%s %s %s %02d:%02d %02d:%02d %d",
                    fn[0], fn[1], fn[2], Integer.parseInt(fn[3]), Integer.parseInt(fn[4]), Integer.parseInt(fn[5])
                    , Integer.parseInt(fn[6]), Integer.parseInt(fn[7]));
            Object flight2 = flightConstructor.newInstance(format);
            Object flight1 = firstNeighborFlights.get(fn[1]);

            int startTime = Integer.parseInt(fn[3]) * 60 + Integer.parseInt(fn[4]);
            int endTime = Integer.parseInt(fn[5]) * 60 + Integer.parseInt(fn[6]);

            if (startTime <= firstNeigtborArriveTime.get(fn[1]) + TIME) {
                continue;
            }

            result.add(flight1 + "\t" + flight2);
        }
        return result;
    }

    private String buildTransitOnceBestSearchRoutes(String[][] testcases, String depart, String arrive, String searchPlanName)
            throws Throwable {
        int leastValue = 999999999;
        String bestOne = null;

        Map<String, Object> firstNeighborFlights = new HashMap<>();
        Map<String, Integer> firstNeighborStartTime = new HashMap<>();
        Map<String, Integer> firstNeigtborArriveTime = new HashMap<>();
        Map<String, Integer> firstNeighborPrice = new HashMap<>();
        for (String[] fn : testcases) {
            if (!fn[1].equals(depart)) {
                continue;
            }

            String format = String.format("%s %s %s %02d:%02d %02d:%02d %d",
                    fn[0], fn[1], fn[2], Integer.parseInt(fn[3]), Integer.parseInt(fn[4]), Integer.parseInt(fn[5])
                    , Integer.parseInt(fn[6]), Integer.parseInt(fn[7]));
            Object flight = flightConstructor.newInstance(format);
            firstNeighborFlights.put(fn[2], flight);

            firstNeighborStartTime.put(fn[2], Integer.parseInt(fn[3]) * 60 + Integer.parseInt(fn[4]));
            firstNeigtborArriveTime.put(fn[2], Integer.parseInt(fn[5]) * 60 + Integer.parseInt(fn[6]));
            firstNeighborPrice.put(fn[2], Integer.parseInt(fn[7]));
        }

        for (String[] fn : testcases) {
            if (!fn[2].equals(arrive)) {
                continue;
            }

            if (!firstNeighborFlights.containsKey(fn[1])) {
                continue;
            }

            String format = String.format("%s %s %s %02d:%02d %02d:%02d %d",
                    fn[0], fn[1], fn[2], Integer.parseInt(fn[3]), Integer.parseInt(fn[4]), Integer.parseInt(fn[5])
                    , Integer.parseInt(fn[6]), Integer.parseInt(fn[7]));

            Object flight1 = firstNeighborFlights.get(fn[1]);
            Object flight2 = flightConstructor.newInstance(format);

            int startTime = Integer.parseInt(fn[3]) * 60 + Integer.parseInt(fn[4]);
            int endTime = Integer.parseInt(fn[5]) * 60 + Integer.parseInt(fn[6]);
            int totalTime = endTime - firstNeighborStartTime.get(fn[1]);

            if (startTime <= firstNeigtborArriveTime.get(fn[1]) + TIME) {
                continue;
            }

            int totalPrice = firstNeighborPrice.get(fn[1]) + Integer.parseInt(fn[7]) +
                    FEE;

            if (searchPlanName.equals("LESS_PRICE")) {
                if (totalPrice < leastValue) {
                    bestOne = flight1 + "\t" + flight2;
                    leastValue = totalPrice;
                } else if (totalPrice == leastValue && bestOne != null) {
                    if (bestOne.compareTo(flight1 + "\t" + flight2) > 0) {
                        bestOne = flight1 + "\t" + flight2;
                    }
                }
            } else {
                if (totalTime < leastValue) {
                    bestOne = flight1 + "\t" + flight2;
                    leastValue = totalTime;
                } else if (totalTime == leastValue && bestOne != null) {
                    if (bestOne.compareTo(flight1 + "\t" + flight2) > 0) {
                        bestOne = flight1 + "\t" + flight2;
                    }
                }
            }

        }
        return bestOne;
    }


    private static class MySearch extends Search {
        @Override
        public List<String> searchRoutes(String departPort, String arrivePort) {
            List<String> routes = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                routes.add(String.format("NO.%d %s->%s", i, departPort, arrivePort));
            }
            return routes;
        }

        @Override
        public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan) {
            return String.join("@", departPort, arrivePort, String.valueOf(searchPlan));
        }
    }
}