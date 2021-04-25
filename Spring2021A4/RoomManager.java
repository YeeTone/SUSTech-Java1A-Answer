package Spring2021A4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class RoomManager {
    private static final HashMap<Library,ArrayList<Room>>library_rooms_hashMap=new HashMap<>();
    private static final HashMap<Library,Integer> order_hashMap =new HashMap<>();
    static {
        initiateAll();
    }

    public static boolean addRoom (String rid, Library location, int capacity){
        return addRoom(rid, location, capacity,true,true);
    }

    public static boolean addRoom (String rid, Library library, int capacity,
                                   boolean hasDisplay, boolean hasWhiteboard){
        if(!Room.isRIDValid(rid)){
            return false;
        }
        if(capacity<=0){
            return false;
        }

        ArrayList<Room>rooms=library_rooms_hashMap.get(library);
        for (Room room : rooms) {
            if (room.getRid().equals(rid)) {
                return false;
            }
        }

        return library_rooms_hashMap.get(library).add(
                new Room(rid, library, capacity,hasDisplay,hasWhiteboard));
    }

    public static boolean orderRoom (Library library, String rid,
                                     String SID, int start, int end, int numberOfTeammates){
        ArrayList<Room>rooms=library_rooms_hashMap.get(library);

        Room selectedRoom=null;
        for(Room r:rooms){
            if (r != null && r.getRid().equals(rid)) {
                selectedRoom=r;
                break;
            }
        }
        if(selectedRoom==null){
            return false;
        }

        for (Room r:rooms){
            if(r.hasOrdered(SID)){
                return false;
            }
        }

        return selectedRoom.setApplicant(start,end,SID,numberOfTeammates);
    }

    public static boolean cancelOrder (String SID){
        boolean hasRemoved =false;
        for (Library l:library_rooms_hashMap.keySet()){
            hasRemoved=cancelOrder(SID,l)||hasRemoved;
        }
        return hasRemoved;
    }

    public static boolean cancelOrder (String SID, Library location){
        boolean hasRemoved=false;
        ArrayList<Room>rooms=library_rooms_hashMap.get(location);
        for(Room r:rooms){
            hasRemoved=r.removeApplicant(SID)||hasRemoved;
        }
        return hasRemoved;
    }

    public static ArrayList<Room>searchRoom(Library location, int start, int end,
                                            boolean needDisplay, boolean needWhiteboard){
        ArrayList<Room>rooms=library_rooms_hashMap.get(location);
        ArrayList<Room>result=new ArrayList<>();

        for (Room r:rooms){
            if(r==null){
                continue;
            }

            boolean isValid =true;
            if(r.isEmptyInterval(start,end)){
                if(needDisplay){
                    if(!r.isHasDisplay()){
                        isValid =false;
                    }
                }

                if(needWhiteboard){
                    if(!r.isHasWhiteboard()){
                        isValid =false;
                    }
                }
            }else {
                isValid =false;
            }

            if(isValid){
                result.add(r);
            }
        }

        resultSort(result);
        return result.isEmpty()?null:result;
    }

    public static ArrayList<Room> searchRoom (int start, int end){
        ArrayList<Room>result=new ArrayList<>();
        for (Library l:library_rooms_hashMap.keySet()){
            result.addAll(searchRoom(l,start,end,false,false));
        }
        resultSort(result);
        return result;
    }

    private static void resultSort(ArrayList<Room> result) {
        result.sort((r1,r2)->{
            int order1=order_hashMap.get(r1.getLocation());
            int order2=order_hashMap.get(r2.getLocation());

            if(order1!=order2){
                return order1-order2;
            }

            return r1.getRid().compareTo(r2.getRid());
        });
    }

    public static ArrayList<Room> searchRoom (int start, int end, Landmark landmark){
        ArrayList<Room>result=new ArrayList<>();
        Library[]priority=landmark.getPriority();

        for(Library l:priority){
            ArrayList<Room>rooms=searchRoom(l,start,end,false,false);
            rooms.sort(Comparator.comparing(Room::getRid));
            result.addAll(rooms);
        }

        return result;
    }

    public static String showOrder (ArrayList<Room> list){
        if(list==null||list.isEmpty()){
            return "No room to show.";
        }

        StringBuilder b=new StringBuilder();
        for(Room r:list){
            if(r!=null){
                b.append(r.toString());
            }
        }

        return b.toString();
    }

    private static void initiateAll(){
        int priority=1;
        for (Library lib:Library.values()){
            library_rooms_hashMap.put(lib,new ArrayList<>());
            order_hashMap.put(lib,priority++);
        }
    }
}
