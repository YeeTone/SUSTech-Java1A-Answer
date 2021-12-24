import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class Test6UnlimitedSearchJudge {
    private static final int FEE = 80;
    private static final int TIME = 30;

    private static Class<?> flightClazz;
    private static Class<?> searchPlanClazz;
    private static Class<?> searchClazz;
    private static Class<?> unlimitedSearchClazz;

    private static Constructor<?> sustechAirlineConstructor;
    private static Constructor<?> flightConstructor;

    private static Field AIRPORT_FEE_field;
    private static Field TRANSIT_TIME_field;

    private static Method addFlightMethod;
    private static Method searchRoutesMethod_Search;
    private static Method searchAllRoutesMethod_Airline;
    private static Method searchBestRouteMethod_Search;
    private static Method searchBestRouteMethod_Airline;

    private static final Map<String, Object> searchPlanMap = new HashMap<>();

    private Object sustechAirlineObject;
    private Object unlimitedSearchObject;

    @BeforeAll
    public static void checkClassDefinition() throws Throwable {
        checkExists();
        checkType();
        checkModifier();
        checkEnum();
    }

    private static void checkExists() throws Throwable {
        searchPlanClazz = Class.forName("SearchPlan");
        searchClazz = Class.forName("Search");
        flightClazz = Class.forName("Flight");
        Class<?> airlineClazz = Class.forName("Airline");
        Class<?> sustechAirlineClazz = Class.forName("SustechAirline");
        unlimitedSearchClazz = Class.forName("UnlimitedSearch");

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
        assertFalse(Modifier.isAbstract(unlimitedSearchClazz.getModifiers()));

        Method[] methods = {searchRoutesMethod_Search, searchBestRouteMethod_Search};
        for (Method m : methods) {
            assertTrue(Modifier.isAbstract(m.getModifiers()));
            assertTrue(Modifier.isPublic(m.getModifiers()));
            assertFalse(Modifier.isStatic(m.getModifiers()));
            assertFalse(Modifier.isFinal(m.getModifiers()));
        }

        assertEquals(searchClazz, unlimitedSearchClazz.getSuperclass());
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

    private void checkMethodUsing() throws Throwable {
        Search mySearch = new MySearch();
        assertEquals("[NO.0 A->B, NO.1 A->B]",
                (Arrays.toString(((List<?>) (searchAllRoutesMethod_Airline.invoke(sustechAirlineObject, "A", "B", mySearch))).toArray())));
        assertEquals("A@B@null", searchBestRouteMethod_Airline.invoke(sustechAirlineObject, "A", "B", mySearch, null));
    }

    private void checkValue() throws Throwable {
        int[] values = {FEE, TIME};
        Field[] fields = {AIRPORT_FEE_field, TRANSIT_TIME_field};
        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], fields[i].get(unlimitedSearchObject));
        }
    }

    @BeforeEach
    public void resetObject() throws Throwable {
        sustechAirlineObject = sustechAirlineConstructor.newInstance();
        unlimitedSearchObject = unlimitedSearchClazz.newInstance();
        checkMethodUsing();
        checkValue();
    }

    @Test
    public void combination01() throws Throwable {
        int nodes = 15;
        int cases = 188;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination02() throws Throwable {
        int nodes = 16;
        int cases = 201;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination03() throws Throwable {
        int nodes = 17;
        int cases = 221;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination04() throws Throwable {
        int nodes = 18;
        int cases = 244;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination05() throws Throwable {
        int nodes = 19;
        int cases = 275;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination06() throws Throwable {
        int nodes = 20;
        int cases = 288;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination07() throws Throwable {
        int nodes = 21;
        int cases = 307;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination08() throws Throwable {
        int nodes = 22;
        int cases = 342;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination09() throws Throwable {
        int nodes = 23;
        int cases = 386;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination10() throws Throwable {
        int nodes = 24;
        int cases = 432;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination11() throws Throwable {
        int nodes = 25;
        int cases = 480;
        randomJudge(nodes, cases);
    }

    @Test
    public void combination12() throws Throwable {
        int nodes = 26;
        int cases = 530;
        randomJudge(nodes, cases);
    }

    private List<String> result;
    private Map<String, List<String[]>> neighbors;
    //private Map<String,Boolean> visited;
    private Stack<String> dfsAirportStack;
    private Stack<String> flightNoStack;
    private Stack<String> startTimeStack;
    private Stack<String> endTimeStack;
    private Stack<Integer> feeStack;
    private int leastTime = 999999999;
    private String bestResult_LESS_TIME;
    private int leastPrice = 999999999;
    private String bestResult_LESS_PRICE;

    private List<String> buildAllRoutes(String[][] testcases, Set<String> nodes, String start, String end) {
        leastPrice = 999999999;
        leastTime = 999999999;
        bestResult_LESS_PRICE = null;
        bestResult_LESS_TIME = null;
        result = new ArrayList<>();
        neighbors = buildNeighbors(testcases, nodes);
        //visited = new HashMap<>();
        //visited.put(start,true);
        dfsAirportStack = new Stack<>();
        flightNoStack = new Stack<>();
        startTimeStack = new Stack<>();
        endTimeStack = new Stack<>();
        feeStack = new Stack<>();
        dfs(start, end);

        return result;
    }

    private void dfs(String start, String end) {
        if (start.equals(end)) {
            dfsAirportStack.push(start);

            StringBuilder b = new StringBuilder();
            String[] startTime_first = startTimeStack.get(0).split(":");
            String[] endTime_final = endTimeStack.peek().split(":");

            int startTime = Integer.parseInt(startTime_first[0]) * 60 + Integer.parseInt(startTime_first[1]);
            int endTime = Integer.parseInt(endTime_final[0]) * 60 + Integer.parseInt(endTime_final[1]);
            int timeDifference = endTime - startTime;

            int totalPrice = 0;

            for (int i = 0; i < dfsAirportStack.size() - 1; i++) {
                b.append(String.format("%s [%s -> %s] %s -> %s (%d)",
                        flightNoStack.get(i), dfsAirportStack.get(i),
                        dfsAirportStack.get(i + 1), startTimeStack.get(i),
                        endTimeStack.get(i), feeStack.get(i))).append("\t");
                totalPrice += feeStack.get(i);
            }
            totalPrice += (dfsAirportStack.size() - 2) * FEE;
            result.add(b.toString().trim());
            //visited.put(start, false);
            dfsAirportStack.pop();

            if (timeDifference < leastTime) {
                leastTime = timeDifference;
                bestResult_LESS_TIME = b.toString().trim();
            } else if (timeDifference == leastTime && bestResult_LESS_TIME != null) {
                if (bestResult_LESS_TIME.compareTo(b.toString().trim()) > 0) {
                    bestResult_LESS_TIME = b.toString().trim();
                }
            }

            if (totalPrice < leastPrice) {
                leastPrice = totalPrice;
                bestResult_LESS_PRICE = b.toString().trim();
            } else if (totalPrice == leastPrice && bestResult_LESS_PRICE != null) {
                if (bestResult_LESS_PRICE.compareTo(b.toString().trim()) > 0) {
                    bestResult_LESS_PRICE = b.toString().trim();
                }
            }

            return;
        }

        //visited.put(start, true);
        dfsAirportStack.push(start);

        List<String[]> neigh = neighbors.get(start);
        for (String[] fl : neigh) {
            String arrive = fl[2];
            if (!endTimeStack.isEmpty()) {
                if (timeInvalid(endTimeStack.peek(), fl[3] + ":" + fl[4])) {
                    continue;
                }
            }

            /*if (visited.containsKey(arrive) && visited.get(arrive)) {
                continue;
            }*/

            flightNoStack.push(fl[0]);
            startTimeStack.push(String.format("%02d:%02d", Integer.parseInt(fl[3]), Integer.parseInt(fl[4])));
            endTimeStack.push(String.format("%02d:%02d", Integer.parseInt(fl[5]), Integer.parseInt(fl[6])));
            feeStack.push(Integer.parseInt(fl[7]));
            dfs(arrive, end);

            flightNoStack.pop();
            startTimeStack.pop();
            endTimeStack.pop();
            feeStack.pop();
        }

        //visited.put(start, false);
        dfsAirportStack.pop();
    }

    private static boolean timeInvalid(String time1, String time2) {
        String[] hm1 = time1.split(":");
        String[] hm2 = time2.split(":");
        return Integer.parseInt(hm1[0]) * 60 + Integer.parseInt(hm1[1]) + TIME >=
                Integer.parseInt(hm2[0]) * 60 + Integer.parseInt(hm2[1]);
    }

    private Map<String, List<String[]>> buildNeighbors(String[][] testcases, Set<String> nodes) {
        Map<String, List<String[]>> result = new HashMap<>();
        for (String s : nodes) {
            result.put(s, new ArrayList<>());
        }

        for (String[] fn : testcases) {
            result.get(fn[1]).add(fn);
        }
        return result;
    }

    private void randomJudge(int ns, int cases) {
        assertTimeoutPreemptively(Duration.ofMillis(3000), () -> {
            Set<String> nodes = generateNodes(ns);
            String[][] testcases = generateFlights(cases, nodes);
            addFlights(testcases);
            judge(testcases, nodes);
        });

    }

    private void judge(String[][] testcases, Set<String> nodes) throws Throwable {
        String[] noArray = String.join("", nodes).split("");

        checkUnlimitedSearchRoutes(testcases, nodes, "A", "B");

        for (int i = 0; i < noArray.length; i++) {
            for (int j = 0; j < i; j++) {
                String depart = noArray[i];
                String arrive = noArray[j];

                checkUnlimitedSearchRoutes(testcases, nodes, depart, arrive);
                checkUnlimitedBestSearchRoutes(depart, arrive, "LESS_TIME");
                checkUnlimitedBestSearchRoutes(depart, arrive, "LESS_PRICE");

                checkUnlimitedSearchRoutes(testcases, nodes, arrive, depart);
                checkUnlimitedBestSearchRoutes(arrive, depart, "LESS_TIME");
                checkUnlimitedBestSearchRoutes(arrive, depart, "LESS_PRICE");
            }
        }
    }

    private void checkUnlimitedSearchRoutes(String[][] testcases, Set<String> nodes, String depart, String arrive) throws Throwable {
        List<String> built = buildAllRoutes(testcases, nodes, depart, arrive);


        //System.out.println("Built is finished!");

        List<String> actual_airline = AirlineUtil.toModifiableList((List<String>) searchAllRoutesMethod_Airline.invoke(sustechAirlineObject, depart, arrive, unlimitedSearchObject));
        List<String> actual_search = AirlineUtil.toModifiableList((List<String>) searchRoutesMethod_Search.invoke(unlimitedSearchObject, depart, arrive));

        //System.out.println("Actual is finished!");
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

    private void checkUnlimitedBestSearchRoutes(String depart, String arrive, String searchPlanName) throws Throwable {
        String best = buildBestUnlimitedBestRoute(searchPlanName);
        Object searchPlan = searchPlanMap.get(searchPlanName);

        String actual_airline = (String)
                searchBestRouteMethod_Airline.invoke(sustechAirlineObject, depart, arrive, unlimitedSearchObject, searchPlan);
        String actual_search = (String)
                searchBestRouteMethod_Search.invoke(unlimitedSearchObject, depart, arrive, searchPlan);

        if (actual_airline != null) {
            actual_airline = actual_airline.trim();
        }

        if (actual_search != null) {
            actual_search = actual_search.trim();
        }

        assertEquals(best, actual_search);
        assertEquals(best, actual_airline);
        assertEquals(actual_search, actual_airline);

    }

    private String buildBestUnlimitedBestRoute(String searchPlanName) {
        if (searchPlanName.equals("LESS_TIME")) {
            return bestResult_LESS_TIME;
        } else {
            return bestResult_LESS_PRICE;
        }
    }

    private void trimStringList(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            strings.set(i, strings.get(i).trim());
        }
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
