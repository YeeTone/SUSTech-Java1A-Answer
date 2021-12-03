public class Flight {
    // fields must be declared
    private String flightNo;
    private SustechTime departTime;
    private SustechTime arriveTime;
    private int price;

    //todo: you can add any fields that you think is necessary


    /**
     * example of flightInfo:
     * S101 A B 8:00 11:20 960
     * S102 A C 9:20 14:20 1210
     *
     * @param flightInfo format: [flightNo] [departPort] [arrivePort] [departTime] [arriveTime]
     */
    public Flight(String flightInfo) {
        //todo: complete the constructor
    }

    /**
     * @return a format like: [flightNo] [[departPort] -> [arrivePort]] [departTime] -> [arriveTime] ([price])
     * for example:
     * S103 [A -> E] 03:00 -> 05:30 (900)
     */
    @Override
    public String toString() {
//      todo: complete the return format:  return String.format("%s [%s -> %s] %s -> %s (%d)", );
        return null;
    }

    //todo: you can add any method that you think necessary

}
