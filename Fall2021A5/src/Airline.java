import java.util.List;

public interface Airline {

    public void addFlight(String flightInfo);

    public String getFlightInfo(String flightNo);

    public List<String> getFlightRoute();

    public boolean removeFlight(String flightNo);

    public List<Flight> getAllFlightsAboutDepartPort(String departPort);

    public boolean isContainsFlight(String deportPort, String arrivePort);

    public boolean isRoundTrip(String port1, String port2);

    public List<String> searchAllRoutes(String departPort, String arrivePort, Search search);

    public String searchBestRoute(String departPort, String arrivePort, Search search, SearchPlan searchPlan);
}