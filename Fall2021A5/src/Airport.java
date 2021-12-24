import java.util.*;

public class Airport {
    //这个类表示的是图数据结构里的节点

    //常量
    private static final int AIRPORT_FEE = 80;
    private static final int TRANSIT_TIME = 30;

    //用于BFS的变量定义
    private int pricesFromStart = 0;
    private int timeFromStart = 0;
    private static Route bfsResult = null;
    private Flight startFlight = null;

    //用于BFS,DFS的变量定义
    //private boolean visited = false;
    //private Flight previousFlight = null;

    //用于DFS的变量定义
    private static final Stack<Airport> dfsStack = new Stack<>();
    private static final Stack<Flight> dfsFlightStack = new Stack<>();
    private static final List<Route> dfsResult = new ArrayList<>();

    //享元模式的哈希表
    private static final Map<String, Airport> instanceMap = new HashMap<>();

    //图节点的变量定义
    private final String name; //名称：例如A
    private final List<Flight> departFlight = new ArrayList<>(); //表示图节点的出边
    private final List<Flight> arriveFlight = new ArrayList<>(); //表示图节点的入边

    public static void airportInitiate(){
        //由于是享元模式，@BeforeEach注解会重新构造对象，这时候需要将享元模式里的对象信息全部清空
        dfsStack.clear();
        dfsResult.clear();

        for (Airport ap:instanceMap.values()){
            ap.departFlight.clear();
            ap.arriveFlight.clear();
        }

        instanceMap.clear();
    }

    //获取享元模式的图节点对象
    public static Airport instance(String name) {
        if (!instanceMap.containsKey(name)) {
            instanceMap.put(name, new Airport(name));
        }

        return instanceMap.get(name);
    }

    //DFS初始化之前，一定要将相关的数据结构内容做清除初始化操作
    public static void dfsInitiate() {
        Airport.dfsFlightStack.clear();
        Airport.dfsResult.clear();
        Airport.dfsStack.clear();
    }

    //同dfsInitiate
    public static void bfsInitiate() {
        for (Airport a : Airport.getInstanceMap().values()) {
            //a.visited = false;
            //a.previousFlight = null;
            a.pricesFromStart = 987654321;
            a.timeFromStart = 1234567890;
        }
        Airport.dfsResult.clear();
    }

    //享元模式的设计规则，构造器私有化
    private Airport(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(this.name, airport.name);
    }

    //相同内容触发哈希冲突，从而使得对象内容同一
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    public List<Flight> getArriveFlight() {
        return this.arriveFlight;
    }

    public List<Flight> getDepartFlight() {
        return this.departFlight;
    }

    public void addArriveFlight(Flight f) {
        for (Flight ar:this.arriveFlight){
            if(ar.getFlightNo().equals(f.getFlightNo())){
                return;
            }
        }
        this.arriveFlight.add(f);
    }

    public void addDepartFlight(Flight f) {
        for (Flight de :this.departFlight){
            if(de.getFlightNo().equals(f.getFlightNo())){
                return;
            }
        }
        this.departFlight.add(f);
    }

    public static Map<String, Airport> getInstanceMap() {
        return instanceMap;
    }

    //DFS的实现，获取所有可达路径
    public void dfs(Airport start, Airport end) {
        if(this == end){
            dfsStack.push(this);
            Route r = new Route(start, end);
            for (int i = 0; i < dfsStack.size() - 1; i++) {
                r.addFlight(dfsFlightStack.get(i));
            }

            dfsResult.add(r);
            dfsStack.pop();
            return;
        }

        dfsStack.push(this);
        for (Flight f: this.getDepartFlight()){
            Airport arrive = f.getArrivePort();
            if(!dfsFlightStack.isEmpty()){
                SustechTime peek = dfsFlightStack.peek().getArriveTime();
                if(f.getDepartTime().getMinutes() - peek.getMinutes() <= TRANSIT_TIME){
                    continue;
                }
            }

            dfsFlightStack.push(f);
            arrive.dfs(start,end);
            dfsFlightStack.pop();
        }
        dfsStack.pop();
    }



    public static List<Route> getDfsResult() {
        return dfsResult;
    }

    public static Route getBfsResult() {
        return bfsResult;
    }
}