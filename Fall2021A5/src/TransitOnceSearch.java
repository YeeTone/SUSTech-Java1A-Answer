import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TransitOnceSearch extends Search{
    @Override
    public List<String> searchRoutes(String departPort, String arrivePort) {
        List<Route> routes = getAllRoutes(departPort,arrivePort);
        List<String> result = new ArrayList<>();

        if(isNULL(departPort, arrivePort)){
            return result;
        }

        for (Route r:routes){
            result.add(r.toString());
        }

        return result;
    }

    private List<Route> getAllRoutes(String departPort,String arrivePort){
        Airport depart = Airport.instance(departPort);
        Airport arrive = Airport.instance(arrivePort);
        List<Flight> departNeighbors = depart.getDepartFlight();

        List<Route> result = new ArrayList<>();
        for (Flight departFlight:departNeighbors){
            Airport neigh = departFlight.getArrivePort();
            for (Flight neighDepartFlight :neigh.getDepartFlight()){
                Airport neighbor_neighbor = neighDepartFlight.getArrivePort();

                if(neighbor_neighbor.getName().equals(arrivePort) && isOKforTransit(departFlight,neighDepartFlight)){
                    Route r = new Route(depart,arrive);
                    r.addFlight(departFlight);
                    r.addFlight(neighDepartFlight);
                    result.add(r);
                }
            }
        }
        return result;
    }

    @Override
    public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan) {
        List<Route> routes = getAllRoutes(departPort, arrivePort);

        if(isNULL(departPort, arrivePort, searchPlan)){
            return null;
        }

        Route best = null;

        for (Route r:routes){
            if(best==null){
                best = r;
                continue;
            }

            if(searchPlan == SearchPlan.LESS_PRICE){
                if(best.getTotalPrice() > r.getTotalPrice()){
                    best = r;
                }else if(best.toString().compareTo(r.toString())>0 && best.getTotalPrice() == r.getTotalPrice()){
                    best = r;
                }
            }else {
                if(best.getTotalTime() > r.getTotalTime()){
                    best = r;
                }
                else if(best.toString().compareTo(r.toString())>0 && best.getTotalTime() == r.getTotalTime()){
                    best = r;
                }
            }
        }

        return best == null ? null: best.toString();
    }
}