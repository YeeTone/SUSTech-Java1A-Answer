package Spring2021A4;

public class Room {
    private static final Library LIBRARY_DEFAULT=Library.Lynn;
    private static final int CAPACITY_DEFAULT=3;
    private static final boolean HAS_DISPLAY_DEFAULT=true;
    private static final boolean HAS_WHITEBOARD_DEFAULT=true;

    private String rid;
    private Library location;
    private int capacity;
    private boolean hasDisplay;
    private boolean hasWhiteboard;
    private String[]applicants=new String[15];

    public Room(String rid){
        this(rid,LIBRARY_DEFAULT,CAPACITY_DEFAULT);
    }

    public Room(String rid,Library location,int capacity){
        this(rid,location,capacity,HAS_DISPLAY_DEFAULT,HAS_WHITEBOARD_DEFAULT);
    }

    public Room(String rid,Library library,int capacity,boolean hasDisplay,boolean hasWhiteboard){
        this.rid=rid;
        this.location=library;
        this.capacity=capacity;
        this.hasDisplay=hasDisplay;
        this.hasWhiteboard=hasWhiteboard;
    }

    public boolean isEmptyInterval(int start,int end){
        if(start>=end){
            return false;
        }

        for (int i = start; i < end; i++) {
            if(applicants[i-8]!=null){
                return false;
            }
        }
        return true;
    }

    public String toString(){
        return toString(8,22);
    }

    public String toString(int start,int end) {
        if(end<=start||isTimeFail(start)||isTimeFail(end)){
            return null;
        }

        StringBuilder b = new StringBuilder();
        b.append(location.toString()).append(" ").append(rid).append(", ");
        b.append("capacity=").append(capacity).append(", ");
        b.append("hasDisplay=").append(hasDisplay).append(", ");
        b.append("hasWhiteboard=").append(hasWhiteboard).append("\n");

        for (int i = start; i < end; i++) {
            String format0 = String.format("%02d:00", i);
            String format = String.format("%-8s", format0);
            b.append("|").append(format);
        }
        b.append("|");
        b.append("\n");

        String pre = null;
        for (int i = start; i < end; i++) {
            if (applicants[i-8] == null) {
                String format=String.format("%-9s","|EMPTY");
                b.append(format);
            } else if (applicants[i-8].equals(pre)) {
                b.append(String.format("%-9s",""));
                pre = applicants[i-8];
            } else {
                b.append("|").append(applicants[i-8]);
                pre = applicants[i-8];
            }
        }

        b.append("|");
        b.append("\n");

        return b.toString();
    }

    public boolean setApplicant(int start, int end, String SID, int numberOfTeammates){
        if(isTimeFail(start) || isTimeFail(end)||is2TimeFail(start,end)){
            return false;
        }else if(end-start>2){
            return false;
        }else if((long)numberOfTeammates+1>capacity||numberOfTeammates<0){
            return false;
        }else {
            for (int i = start; i < end; i++) {
                if(applicants[i-8]!=null){
                    return false;
                }
            }

            for(String t:applicants){
                if(t!=null&&t.equals(SID)){
                    return false;
                }
            }

            for (int i = start; i < end; i++) {
                applicants[i-8]=SID;
            }
            return true;
        }
    }

    private boolean isTimeFail(int t){
        return t < 8 || t > 22;
    }

    private boolean is2TimeFail(int s,int t){
        return s>=t;
    }

    public boolean removeApplicant (String SID){
        boolean removed=false;
        for (int i = 0; i < 15; i++) {
            if(applicants[i]!=null&&applicants[i].equals(SID)){
                applicants[i]=null;
                removed=true;
            }
        }

        return removed;
    }

    public static boolean isRIDValid(String rid){
        if(rid.length()!=4){
            return false;
        }

        if(rid.charAt(0)!='R'){
            return false;
        }

        try{
            int r=Integer.parseInt(rid.substring(1));
            if(r<100){
                throw new NumberFormatException();
            }
        }catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    public String getRid() {
        return rid;
    }

    public boolean hasOrdered(String SID){
        for (String a:applicants){
            if(a!=null&&a.equals(SID)){
                return true;
            }
        }
        return false;
    }

    public boolean isHasDisplay() {
        return hasDisplay;
    }

    public boolean isHasWhiteboard() {
        return hasWhiteboard;
    }

    public Library getLocation() {
        return location;
    }
}