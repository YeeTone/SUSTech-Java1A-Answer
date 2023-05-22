package Spring2021A5;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Classroom> rooms;
    private Location location;
    private int id;


    public Building(Location location, int id) {
        this.location = location;
        this.id = id;
        rooms = new ArrayList<>();
    }

    public Building() {
        rooms = new ArrayList<>();
    }

    public List<Classroom> getRooms() {
        return rooms;
    }

    public void setRooms(List<Classroom> rooms) {
        this.rooms = rooms;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean addRoom( Classroom room ) {
        if(room.getBuilding()==this){
            return rooms.add(room);
        }
        return false;
    }
    public boolean deleteRoom( Classroom room ) {
        return rooms.remove(room);
    }

    @Override
    public String toString() {
        StringBuilder b=new StringBuilder();
        if(this.location==Location.LycheePark){
            b.append("LP");
        }else {
            b.append("TB");
        }

        b.append("#").append(this.id);
        return b.toString();
    }
}
