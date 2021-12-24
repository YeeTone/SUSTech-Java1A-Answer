import java.util.ArrayList;
import java.util.List;

public class DirectSearch extends Search{
    @Override
    public List<String> searchRoutes(String departPort, String arrivePort) {
        List<String> result = new ArrayList<>();
        if(super.isNULL(departPort, arrivePort)){
            return result;
        }

        Airport depart = Airport.instance(departPort);

        for (Flight departFlight:depart.getDepartFlight()){
            Airport arrive = departFlight.getArrivePort();
            if(arrive.getName().equals(arrivePort)){
                result.add(departFlight.toString());
            }
        }

        return result;
    }

    @Override
    public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan) {
        if(super.isNULL(departPort, arrivePort, searchPlan)){
            return null;
        }
        List<String> routeWithOnly1Flight= this.searchRoutes(departPort, arrivePort);

        if(!routeWithOnly1Flight.isEmpty()){
            return routeWithOnly1Flight.get(0);
        }else {
            return null;
        }
    }
}