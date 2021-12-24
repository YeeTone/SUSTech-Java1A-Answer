import java.util.*;

public class SustechAirline implements Airline{

    private Search searchSource;

    private final List<Flight> flights = new ArrayList<>();

    public SustechAirline(){
        Airport.airportInitiate();
        //重新构建SustechAirline的时候，需要初始化Airport清空内容
    }

    @Override
    public void addFlight(String flightInfo) {
        for (Flight f:this.flights){
            String[] split = flightInfo.split(" ");
            if(f.getFlightNo().equals(split[0])){
                return;
            }
        }

        this.flights.add(new Flight(flightInfo));
    }

    @Override
    public String getFlightInfo(String flightNo) {
        if(flightNo == null){
            return null;
        }

        for (Flight f:this.flights){
            if(flightNo.equals(f.getFlightNo())){
                return f.toString();
            }
        }

        return null;
    }

    @Override
    public List<String> getFlightRoute() {
        Map<String,Airport> instances = Airport.getInstanceMap();
        List<Airport> airports = new ArrayList<>(instances.values());
        airports.sort(Comparator.comparing(Airport::getName));

        for (Airport depart :airports){
            depart.getDepartFlight().sort(Comparator.comparing(flight -> flight.getArrivePort().getName()));
        }

        List<String> result = new ArrayList<>();
        for (Airport de:airports){
            StringBuilder b = new StringBuilder();

            List<Flight> departFlight = de.getDepartFlight();
            if(departFlight.isEmpty()){
                //注意此处的空列表特判！
                continue;
            }

            b.append(de.getName()).append("->");

            for (int i = 0;i < departFlight.size();i++){
                Airport arrive = departFlight.get(i).getArrivePort();
                b.append(arrive.getName());
                if(i!=departFlight.size()-1){
                    b.append(" ");
                }
            }
            result.add(b.toString());
        }

        return result;
    }

    @Override
    public boolean removeFlight(String flightNo) {
        if(flightNo == null){
            return false;
        }

        boolean removed = false;

        for (int i = 0; i < this.flights.size(); i++) {
            Flight f = this.flights.get(i);
            if(f.getFlightNo().equals(flightNo)){
                f.removeThis();//图节点中也删除相关信息
                removed = true;
                this.flights.remove(i);
                i--;
            }
        }

        return removed;
    }

    @Override
    public List<Flight> getAllFlightsAboutDepartPort(String departPort) {
        if(departPort == null){
            return new ArrayList<>();
        }

        Airport ap = Airport.instance(departPort);
        List<Flight> result = ap.getDepartFlight();
        result.sort(Comparator.comparing(Flight::getFlightNo));

        return result;
    }

    @Override
    public boolean isContainsFlight(String deportPort, String arrivePort) {
        if(deportPort==null || arrivePort == null){
            return false;
        }

        Airport depart = Airport.instance(deportPort);
        for (Flight departFlight:depart.getDepartFlight()){
            if(departFlight.getArrivePort().getName().equals(arrivePort)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isRoundTrip(String port1, String port2) {
        if (port1 == null || port2 == null) {
            return false;
        }

        return isContainsFlight(port1, port2) && isContainsFlight(port2, port1);
    }

/*    @Override
    public void setSearchResource(Search search) {
        this.searchSource = search;
    }*/

    @Override
    public List<String> searchAllRoutes(String departPort, String arrivePort, Search search) {
        return search.searchRoutes(departPort, arrivePort);
    }

    @Override
    public String searchBestRoute(String departPort, String arrivePort, Search search, SearchPlan searchPlan) {
        return search.searchBestRoute(departPort, arrivePort, searchPlan);
    }
}