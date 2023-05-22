package Spring2023A5;

public abstract class GraphicObject {
    private ObjectColor color; //Every graphic has the color, you should use the enum ObjectColor.
    private int id; //Every graphic has an id and the id is unique for each graphic
    private static int idcnt = 0;

    public GraphicObject(ObjectColor oc){
        this.color = oc;
        this.id = idcnt;
        idcnt++;
    }

    public abstract double surfaceMeanSize();

    public abstract double volume();

    public ObjectColor getColor() {
        return color;
    }

    public int getId() {
        return id;
    }
}
