import java.util.List;

public abstract class Search {
    protected final int AIRPORT_FEE = 80;
    protected final int TRANSIT_TIME = 30;

    public boolean isNULL(String departPort, String arrivePort){
        return departPort ==null || arrivePort==null;
    }

    public boolean isNULL(String departPort, String arrivePort, SearchPlan searchPlan){
        return isNULL(departPort, arrivePort) || searchPlan==null;
    }

    public abstract List<String> searchRoutes(String departPort,String arrivePort);

    public abstract String searchBestRoute(String departPort, String arrivePort, SearchPlan searchPlan);

    public boolean isOKforTransit(Flight departFlight, Flight neighborDepartFlight){
        if(departFlight.getArriveTime().getMinutes()< neighborDepartFlight.getDepartTime().getMinutes()){
            return departFlight.getArriveTime().timeDifference(neighborDepartFlight.getDepartTime()) > this.TRANSIT_TIME;
        }else {
            return false;
        }
    }
}