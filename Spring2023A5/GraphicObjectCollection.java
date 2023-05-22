package Spring2023A5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GraphicObjectCollection implements ObjectCollection {
    private List<GraphicObject> graphicObjects; //A list that stores graphics, the list should be empty (NOT be null) if there is no graphic

    public GraphicObjectCollection() {
        this.graphicObjects = new ArrayList<>();
    }

    @Override
    public void addObject(ObjectColor objectColor, double... parameters) {
        switch (parameters.length) {
            case 1 -> this.graphicObjects.add(new Sphere(objectColor, parameters[0]));
            case 2 -> this.graphicObjects.add(new Cone(objectColor, parameters[0], parameters[1]));
            case 3 -> this.graphicObjects.add(new Cuboid(objectColor, parameters[0], parameters[1], parameters[2]));
        }
    }

    @Override
    public List<String> getObjectInfo() {
        List<String> result = new ArrayList<>();
        for (GraphicObject go : this.graphicObjects) {
            result.add(go.toString());
        }

        return result;
    }

    @Override
    public int getObjectCount() {
        return this.graphicObjects.size();
    }

    @Override
    public List<String> getCountsByColor() {
        List<String> result = new ArrayList<>();
        for (ObjectColor oc : ObjectColor.values()) {
            int count = 0;
            for (GraphicObject go : this.graphicObjects) {
                if (go.getColor() == oc) {
                    count++;
                }
            }

            if (count != 0) {
                result.add(String.format("%s %d", oc.toString(), count));
            }
        }

        return result;
    }

    @Override
    public List<String> getObjectByVolume() {
        List<GraphicObject> gos = new ArrayList<>(this.graphicObjects);
        gos.sort((e1, e2) -> {
            if (e1.volume() != e2.volume()) {
                return Double.compare(e1.volume(), e2.volume());
            } else {
                return Integer.compare(e1.getId(), e2.getId());
            }
        });

        List<String> result = new ArrayList<>();
        for (GraphicObject go : gos) {
            result.add(go.toString());
        }

        return result;
    }

    @Override
    public List<String> getObjectsBySurface() {
        List<GraphicObject> gos = new ArrayList<>(this.graphicObjects);
        gos.sort((e1, e2) -> {
            if (e1.surfaceMeanSize() != e2.surfaceMeanSize()) {
                return Double.compare(e1.surfaceMeanSize(), e2.surfaceMeanSize());
            } else {
                return Integer.compare(e1.getId(), e2.getId());
            }
        });

        List<String> result = new ArrayList<>();
        for (GraphicObject go : gos) {
            result.add(go.toString());
        }

        return result;
    }

    @Override
    public double getWaterInjected(double area, double height) {
        double result = area * height;
        for (GraphicObject go : this.graphicObjects) {
            result -= go.volume();
        }
        return result;
    }
}
