public class SustechTime {
    private int hour;
    private int minute;

    public SustechTime(String timeInfo){
        String[]hm = timeInfo.split(":");
        this.hour = Integer.parseInt(hm[0]);
        this.minute = Integer.parseInt(hm[1]);
    }

    public int timeDifference(SustechTime targetTime) {
        return Math.abs(this.getMinutes() - targetTime.getMinutes());
    }

    public int getMinutes(){
        return this.hour * 60 + this.minute;
    }

    public String toString(){
        return String.format("%02d:%02d",this.hour,this.minute);
    }
}