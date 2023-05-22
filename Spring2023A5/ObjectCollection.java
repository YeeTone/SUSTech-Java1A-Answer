package Spring2023A5;

import java.util.List;

public interface ObjectCollection {
    public void addObject(ObjectColor objectColor, double ... parameters);
    public List<String> getObjectInfo();
    public int getObjectCount();
    public List<String> getCountsByColor();
    public List<String> getObjectByVolume();
    public List<String> getObjectsBySurface();
    public double getWaterInjected(double area, double height);
}
