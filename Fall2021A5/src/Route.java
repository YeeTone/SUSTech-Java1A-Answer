import java.util.*;

public class Route {
    private static final int AIRPORT_FEE = 80;
    private static final int TRANSIT_TIME = 30;

    private final Airport startAirport;
    private final Airport endAirport;
    private final List<Flight> flights = new ArrayList<>();

    public Route(Airport start,Airport end){
        this.startAirport = start;
        this.endAirport = end;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for (Flight f:flights){
            totalPrice += f.getPrice();
        }

        totalPrice += (flights.size()-1)*AIRPORT_FEE;

        return totalPrice;
    }

    public void addFlight(Flight f){
        this.flights.add(f);
    }

    public int getTotalTime(){
        if(flights.size()==0){
            return 0;
        }else {
            return flights.get(flights.size()-1).getArriveTime().getMinutes()
                    - flights.get(0).getDepartTime().getMinutes();
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < this.flights.size(); i++) {
            b.append(this.flights.get(i));
            if (i != this.flights.size() - 1) {
                b.append("\t");
            }
        }

        return b.toString();
    }

    public void reverse(){

    }
}