import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class LocalTestcasesGenerator {
    public static void main(String[] args) throws Throwable {
        int[] nodeNumbers = {3, 3, 4, 4, 5, 5, 6, 6, 7, 7};
        int[] casesNumbers = {4, 6, 8, 12, 16, 20, 25 , 30, 36, 42};

        for (int i = 1; i <= 10; i++) {
            Set<String> nodes = generateNodes(nodeNumbers[i - 1]);
            String[][] testcases = generateFlights(casesNumbers[i - 1], nodes);
            writeTestcases(testcases, i, nodes);
            writeDirectAnswers(testcases, i, nodes);
            writeTransitOnceAnswers(testcases, i, nodes);
            writeUnlimitedAnswers(testcases, i, nodes);
        }
    }
    private static void writeDirectAnswers(String[][] testcases, int fileNo,Set<String> nodes) throws Throwable {
        SustechAirline sa = new SustechAirline();
        for (String[] fn : testcases) {
            sa.addFlight(String.format("%s %s %s %s:%s %s:%s %s",
                    fn[0], fn[1], fn[2], fn[3], fn[4], fn[5]
                    , fn[6], fn[7]));
        }
        DirectSearch ds = new DirectSearch();

        String[] nos = new String[nodes.size()];
        nodes.toArray(nos);

        BufferedWriter writer = new BufferedWriter(new FileWriter("testcases\\directsearch\\"+"Direct"+fileNo+".out"));

        for (int i = 0; i < nos.length; i++) {
            for (int j = 0; j < nos.length; j++) {
                if (i == j) {
                    continue;
                }

                String depart = nos[i];
                String arrive = nos[j];

                List<String> allRoutes = sa.searchAllRoutes(depart,arrive,ds);
                allRoutes.sort(Comparator.naturalOrder());
                String best_LESS_TIME = sa.searchBestRoute(depart,arrive,ds,SearchPlan.LESS_TIME);
                String best_LESS_PRICE = sa.searchBestRoute(depart,arrive,ds,SearchPlan.LESS_PRICE);

                writer.write("Depart: " + depart);
                writer.write(System.lineSeparator());
                writer.write("Arrive: "+arrive);
                writer.write(System.lineSeparator());
                writer.write("AllRoutes: "+allRoutes);
                writer.write(System.lineSeparator());
                writer.write("BEST_LESS_TIME: "+best_LESS_TIME);
                writer.write(System.lineSeparator());
                writer.write("BEST_LESS_PRICE: "+best_LESS_PRICE);
                writer.write(System.lineSeparator());
                writer.write("=========");
                writer.write(System.lineSeparator());

            }
        }

        writer.flush();
        writer.close();

    }

    private static void writeTransitOnceAnswers(String[][] testcases, int fileNo,Set<String> nodes) throws Throwable {
        SustechAirline sa = new SustechAirline();
        for (String[] fn : testcases) {
            sa.addFlight(String.format("%s %s %s %s:%s %s:%s %s",
                    fn[0], fn[1], fn[2], fn[3], fn[4], fn[5]
                    , fn[6], fn[7]));
        }
        TransitOnceSearch tos = new TransitOnceSearch();

        String[] nos = new String[nodes.size()];
        nodes.toArray(nos);

        BufferedWriter writer = new BufferedWriter(new FileWriter("testcases\\transitoncesearch\\"+"TransitOnce"+fileNo+".out"));

        for (int i = 0; i < nos.length; i++) {
            for (int j = 0; j < nos.length; j++) {
                if (i == j) {
                    continue;
                }

                String depart = nos[i];
                String arrive = nos[j];

                List<String> allRoutes = sa.searchAllRoutes(depart,arrive,tos);
                allRoutes.sort(Comparator.naturalOrder());
                String best_LESS_TIME = sa.searchBestRoute(depart,arrive,tos,SearchPlan.LESS_TIME);
                String best_LESS_PRICE = sa.searchBestRoute(depart,arrive,tos,SearchPlan.LESS_PRICE);

                writer.write("Depart: " + depart);
                writer.write(System.lineSeparator());
                writer.write("Arrive: "+arrive);
                writer.write(System.lineSeparator());
                writer.write("AllRoutes: "+allRoutes);
                writer.write(System.lineSeparator());
                writer.write("BEST_LESS_TIME: "+best_LESS_TIME);
                writer.write(System.lineSeparator());
                writer.write("BEST_LESS_PRICE: "+best_LESS_PRICE);
                writer.write(System.lineSeparator());
                writer.write("=========");
                writer.write(System.lineSeparator());

            }
        }

        writer.flush();
        writer.close();

    }

    private static void writeUnlimitedAnswers(String[][] testcases, int fileNo,Set<String> nodes) throws Throwable {
        SustechAirline sa = new SustechAirline();
        for (String[] fn : testcases) {
            sa.addFlight(String.format("%s %s %s %s:%s %s:%s %s",
                    fn[0], fn[1], fn[2], fn[3], fn[4], fn[5]
                    , fn[6], fn[7]));
        }
        UnlimitedSearch us = new UnlimitedSearch();

        String[] nos = new String[nodes.size()];
        nodes.toArray(nos);

        BufferedWriter writer = new BufferedWriter(new FileWriter("testcases\\unlimitedsearch\\"+"Unlimited"+fileNo+".out"));

        for (int i = 0; i < nos.length; i++) {
            for (int j = 0; j < nos.length; j++) {
                if (i == j) {
                    continue;
                }

                String depart = nos[i];
                String arrive = nos[j];

                List<String> allRoutes = sa.searchAllRoutes(depart,arrive,us);
                allRoutes.sort(Comparator.naturalOrder());
                String best_LESS_TIME = sa.searchBestRoute(depart,arrive,us,SearchPlan.LESS_TIME);
                String best_LESS_PRICE = sa.searchBestRoute(depart,arrive,us,SearchPlan.LESS_PRICE);

                writer.write("Depart: " + depart);
                writer.write(System.lineSeparator());
                writer.write("Arrive: "+arrive);
                writer.write(System.lineSeparator());
                writer.write("AllRoutes: "+allRoutes);
                writer.write(System.lineSeparator());
                writer.write("BEST_LESS_TIME: "+best_LESS_TIME);
                writer.write(System.lineSeparator());
                writer.write("BEST_LESS_PRICE: "+best_LESS_PRICE);
                writer.write(System.lineSeparator());
                writer.write("=========");
                writer.write(System.lineSeparator());

            }
        }

        writer.flush();
        writer.close();

    }

    private static void writeTestcases(String[][] testcases,int fileNo,Set<String>nodes)throws Throwable{
        BufferedWriter writer = new BufferedWriter(new FileWriter("testcases\\in\\"+fileNo+".in"));
        writer.write("#Flights = "+ testcases.length+", #Airports = "+ nodes.size()+ System.lineSeparator());
        for (String[] fn:testcases){
            writer.write(String.format("%s %s %s %s:%s %s:%s %s",
                    fn[0], fn[1], fn[2], fn[3], fn[4], fn[5]
                    , fn[6], fn[7]));
            writer.write(System.lineSeparator());
        }

        writer.flush();
        writer.close();
    }

    private static Set<String> generateNodes(int nodeNumbers) {
        Set<String> nodes = new HashSet<>();
        for (char c = 'A'; c < 'A'+ nodeNumbers; c++) {
            nodes.add(String.valueOf(c));
        }

        return nodes;
    }

    private static String[][] generateFlights(int cases, Set<String> nodes) {
        Random r = new Random();

        String[][] flights = new String[cases][];
        int satisfied = 0;
        Map<String, List<String>> node_toNodes_Map = new HashMap<>();
        Set<String> flightNos = new HashSet<>();
        Set<Integer> priceUsed = new HashSet<>();
        List<String> a2z = new ArrayList<>(nodes);

        for (String n : a2z) {
            node_toNodes_Map.put(n, new ArrayList<>());
        }

        while (satisfied < cases) {
            int r1 = r.nextInt(nodes.size());
            int r2 = r1;
            while (r2 == r1) {
                r2 = r.nextInt(nodes.size());
            }

            String from = a2z.get(r1);
            String to = a2z.get(r2);

            boolean duplicate = false;
            for (String s : node_toNodes_Map.get(from)) {
                if (s.equals(to)) {
                    duplicate = true;
                    break;
                }
            }
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
        testcasesModify(flights,r);

        return flights;
    }

    private static void testcasesModify(String[][] testcases, Random r) {
        for (String[] fn : testcases) {
            for (int j = 3; j <= 6; j++) {
                if (r.nextBoolean()) {
                    fn[j] = String.format("%02d", Integer.parseInt(fn[j]));
                }
            }
        }
    }
}
