public class Flight {
    private String flightNo;
    private SustechTime departTime;
    private SustechTime arriveTime;
    private int price;
    private Airport departPort;
    private Airport arrivePort;

    public Flight(String flightInfo){
        String[]fi_dp_ap_dt_at_p = flightInfo.split(" ");
        this.flightNo = fi_dp_ap_dt_at_p[0];
        this.departPort = Airport.instance(fi_dp_ap_dt_at_p[1]);
        this.arrivePort = Airport.instance(fi_dp_ap_dt_at_p[2]);
        this.departTime = new SustechTime(fi_dp_ap_dt_at_p[3]);
        this.arriveTime = new SustechTime(fi_dp_ap_dt_at_p[4]);
        this.price = Integer.parseInt(fi_dp_ap_dt_at_p[5]);
        this.departPort.addDepartFlight(this);
        this.arrivePort.addArriveFlight(this);
        //在初始化Flight的过程中，要记得在出节点和入节点都增加该边的出入边情况
    }

    public String toString(){
        return String.format("%s [%s -> %s] %s -> %s (%d)",
                this.flightNo,this.departPort,this.arrivePort,
                this.departTime,this.arriveTime,this.price);
    }

    public String getFlightNo() {
        return this.flightNo;
    }

    public Airport getDepartPort() {
        return departPort;
    }

    public Airport getArrivePort() {
        return arrivePort;
    }

    public void removeThis(){
        this.departPort.getDepartFlight().remove(this);
        this.arrivePort.getArriveFlight().remove(this);
        //removeFlight的时候，不要忘记在Airport节点也做删除操作
    }

    public SustechTime getArriveTime() {
        return arriveTime;
    }

    public SustechTime getDepartTime() {
        return departTime;
    }

    public int getPrice() {
        return price;
    }
}