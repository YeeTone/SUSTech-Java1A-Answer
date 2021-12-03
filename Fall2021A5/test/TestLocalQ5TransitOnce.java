import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestLocalQ5TransitOnce {
    Airline sustechAirline;
    Search transitOnceSearch;
    String pathInPrefix = "testcases/in/";
    String pathOutPrefix = "testcases/transitoncesearch/TransitOnce";


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


    private static final Map<String, Object> searchPlanMap = new HashMap<>();

    @BeforeEach
    public void createResource() {
        transitOnceSearch = new TransitOnceSearch();
    }

    @Test
    public void checkClassDefinition() throws Throwable {
        checkExists();
        checkType();
        checkModifier();
        checkEnum();
    }

    @Test
    public void TestCases() {
        for (int i = 1; i <= 10; i++) {
            sustechAirline = new SustechAirline();
            try {
                Objects.requireNonNull(FileUtil.readTestInCase(pathInPrefix + i + ".in")).forEach(
                        flight -> {
                            sustechAirline.addFlight(flight);
                        }
                );
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<FileUtil.OutputComponent> outputs = null;
            try {
                outputs = FileUtil.readTestOutCase(pathOutPrefix + i + ".out");
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert outputs != null;
            for (FileUtil.OutputComponent output : outputs) {
                List<String> re = sustechAirline.searchAllRoutes(output.depart, output.arrive, transitOnceSearch);
                re.sort(Comparator.naturalOrder());
                assertEquals(output.allRoutes, Arrays.deepToString(re.stream().map(String::trim).toArray()), String.format("searchAllRoutes WA in no.%d testcase in TransitOnce%d.out", output.id, i));
                String lessPrice = sustechAirline.searchBestRoute(output.depart, output.arrive, transitOnceSearch, SearchPlan.LESS_PRICE);
                String lessTime = sustechAirline.searchBestRoute(output.depart, output.arrive, transitOnceSearch, SearchPlan.LESS_TIME);
                assertEquals(output.less_price, lessPrice == null ? null : lessPrice.trim(), String.format("searchBestRoute of Less price WA in no.%d testcase in TransitOnce%d.out", output.id, i));
                assertEquals(output.less_time, lessTime == null ? null : lessTime.trim(), String.format("searchBestRoute of Less time WA in no.%d testcase in TransitOnce%d.out", output.id, i));
            }
        }
    }

    @Test
    public void TestMultiBestRoutes() {
        sustechAirline = new SustechAirline();
        sustechAirline.addFlight("S102 B C 14:00 15:00 3000");
        sustechAirline.addFlight("S104 E C 19:00 21:20 30");
        sustechAirline.addFlight("S105 A F 12:00 13:00 3000");
        sustechAirline.addFlight("S106 F C 14:00 15:00 3000");
        sustechAirline.addFlight("S107 A G 12:00 18:00 30");
        sustechAirline.addFlight("S108 G C 19:00 21:20 30");
        sustechAirline.addFlight("S103 A E 12:00 18:00 30");
        sustechAirline.addFlight("S101 A B 12:00 13:00 3000");
        String expectLessPrice = "S103 [A -> E] 12:00 -> 18:00 (30)\tS104 [E -> C] 19:00 -> 21:20 (30)";
        String expectLessTime = "S101 [A -> B] 12:00 -> 13:00 (3000)\tS102 [B -> C] 14:00 -> 15:00 (3000)";

        assertEquals(expectLessPrice, sustechAirline.searchBestRoute("A","C",transitOnceSearch,SearchPlan.LESS_PRICE).trim());
        assertEquals(expectLessTime, sustechAirline.searchBestRoute("A","C",transitOnceSearch,SearchPlan.LESS_TIME).trim());
    }


    @Test
    public void TestSearchClassUse() {
        Search search = new OtherSearch();
        sustechAirline = new SustechAirline();
        assertEquals("[NO.0 A->B, NO.1 A->B]", Arrays.toString(sustechAirline.searchAllRoutes("A", "B", search).toArray()));
        assertEquals("C@D@LESS_TIME", sustechAirline.searchBestRoute("C", "D", search, SearchPlan.LESS_TIME));
        assertEquals("C@D@LESS_PRICE", sustechAirline.searchBestRoute("C", "D", search, SearchPlan.LESS_PRICE));
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

    private static class OtherSearch extends Search {
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