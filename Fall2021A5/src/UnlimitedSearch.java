import java.util.*;

public class UnlimitedSearch extends Search{

    @Override
    public List<String> searchRoutes(String departPort, String arrivePort) {
        List<Route> result = getAllRoutes(departPort,arrivePort);
        List<String> answer = new ArrayList<>();

        for (Route r: result){
            answer.add(r.toString());
        }

        return answer;
    }

    private List<Route> getAllRoutes(String departPort,String arrivePort){
        Airport.dfsInitiate();
        Airport depart = Airport.instance(departPort);
        Airport arrive = Airport.instance(arrivePort);

        depart.dfs(depart,arrive);

        return Airport.getDfsResult();
    }


    @Override
    public String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan) {
        /*Airport.bfsInitiate();
        Airport depart = Airport.instance(departPort);
        Airport arrive = Airport.instance(arrivePort);
        depart.bfs(depart,arrive,searchPlan);
        Route result = Airport.getBfsResult();
        return result.toString();*/

        List<Route> result = getAllRoutes(departPort,arrivePort);

        Route bestRoute = null;
        int time = 999999999;
        int price = 666666666;

        for (Route r:result){
            if(searchPlan == SearchPlan.LESS_PRICE){
                if(r.getTotalPrice()<price){
                    bestRoute = r;
                    price = r.getTotalPrice();
                }else if(r.getTotalPrice() == price && bestRoute!=null){
                    if(bestRoute.toString().compareTo(r.toString()) > 0){
                        bestRoute = r;
                    }
                }
            }else {
                if(r.getTotalTime()<time){
                    bestRoute = r;
                    time = r.getTotalTime();
                }else if(r.getTotalTime() == time && bestRoute != null){
                    if(bestRoute.toString().compareTo(r.toString()) > 0){
                        bestRoute = r;
                    }
                }
            }
        }
        if(bestRoute == null){
            return null;
        }else {
            return bestRoute.toString();
        }

    }
}