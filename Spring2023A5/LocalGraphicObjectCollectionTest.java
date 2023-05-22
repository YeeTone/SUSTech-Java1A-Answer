package Spring2023A5;

import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LocalGraphicObjectCollectionTest {
    private ObjectCollection graphicObjectCollection;

    @BeforeEach
    public void setUp() {
        graphicObjectCollection = new GraphicObjectCollection();
    }

    @Test
    public void testAddObjectAndGetObjectInfo() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0);

        // Test object count
        int objectCount = graphicObjectCollection.getObjectCount();
        Assertions.assertEquals(3, objectCount, "Error at line: object count test");

        // Test object info
        List<String> objectInfo = graphicObjectCollection.getObjectInfo();
        Assertions.assertEquals(3, objectInfo.size(), "Error at line: object info size test");
        Assertions.assertEquals("Sphere: r=3.00", objectInfo.get(0), "Error at line: RED sphere test");
        Assertions.assertEquals("Cone: r=2.00, l=4.00", objectInfo.get(1), "Error at line: BLUE cone test");
        Assertions.assertEquals("Cuboid: x=1.00, y=2.00, z=3.00", objectInfo.get(2), "Error at line: GREEN cuboid test");
    }

    @Test
    public void testGetCountsByColorSorting1() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        graphicObjectCollection.addObject(ObjectColor.RED, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 3.0, 5.0);
        graphicObjectCollection.addObject(ObjectColor.YELLOW, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.0, 3.0, 4.0);

        List<String> countsByColor = graphicObjectCollection.getCountsByColor();

        // Check the size of the countsByColor list
        Assertions.assertEquals(8, graphicObjectCollection.getObjectCount(),
            "Error at line: countsByColor size test");

        // Check the counts of each color and the order
        Assertions.assertEquals("RED 2", countsByColor.get(0), "Error: RED count test");
        Assertions.assertEquals("YELLOW 1", countsByColor.get(1), "Error: YELLOW count test");
        Assertions.assertEquals("BLUE 2", countsByColor.get(2), "Error: BLUE count test");
        Assertions.assertEquals("GREEN 1", countsByColor.get(3), "Error: GREEN count test");
        Assertions.assertEquals("BLACK 1", countsByColor.get(4), "Error: BLACK count test");
        Assertions.assertEquals("WHITE 1", countsByColor.get(5), "Error: WHITE count test");
    }

    @Test
    public void testGetCountsByColorSorting2() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        graphicObjectCollection.addObject(ObjectColor.RED, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 3.0, 5.0);
        graphicObjectCollection.addObject(ObjectColor.YELLOW, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.RED, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.0, 3.0, 4.0);

        List<String> countsByColor = graphicObjectCollection.getCountsByColor();

        // Check the size of the countsByColor list
        Assertions.assertEquals(8, graphicObjectCollection.getObjectCount(),
            "Error at line: countsByColor size test");

        // Check the counts of each color and the order
        Assertions.assertEquals("RED 3", countsByColor.get(0), "Error: RED count test");
        Assertions.assertEquals("YELLOW 1", countsByColor.get(1), "Error: YELLOW count test");
        Assertions.assertEquals("BLUE 2", countsByColor.get(2), "Error: BLUE count test");
        Assertions.assertEquals("GREEN 1", countsByColor.get(3), "Error: GREEN count test");
        Assertions.assertEquals("WHITE 1", countsByColor.get(4), "Error: WHITE count test");
    }

    @Test
    public void testGetObjectByVolumeSorting() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        graphicObjectCollection.addObject(ObjectColor.YELLOW, 2.0, 3.0, 4.0);

        List<String> objectsByVolume = graphicObjectCollection.getObjectByVolume();

        // Check the size of the objectsByVolume list
        Assertions.assertEquals(4, objectsByVolume.size(), "Error: objectsByVolume size test");

        // Create the expected GraphicObjects and their toString() representations
        GraphicObject redObject = new Sphere(ObjectColor.RED, 3.0);
        GraphicObject blueObject = new Cone(ObjectColor.BLUE, 2.0, 4.0);
        GraphicObject greenObject = new Cuboid(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        GraphicObject yellowObject = new Cuboid(ObjectColor.YELLOW, 2.0, 3.0, 4.0);

        // Check the order of the objects by volume
        Assertions.assertEquals(greenObject.toString(), objectsByVolume.get(0), "Error: First object (by volume) test");
        Assertions.assertEquals(blueObject.toString(), objectsByVolume.get(1), "Error: Second object (by volume) test");
        Assertions.assertEquals(yellowObject.toString(), objectsByVolume.get(2), "Error: Third object (by volume) test");
        Assertions.assertEquals(redObject.toString(), objectsByVolume.get(3), "Error: Fourth object (by volume) test");
    }

    @Test
    public void testGetObjectsBySurfaceSorting() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        graphicObjectCollection.addObject(ObjectColor.YELLOW, 2.0, 3.0, 4.0);

        List<String> objectsBySurface = graphicObjectCollection.getObjectsBySurface();

        // Check the size of the objectsBySurface list
        Assertions.assertEquals(4, objectsBySurface.size(), "Error: objectsBySurface size test");

        // Create the expected GraphicObjects and their toString() representations
        GraphicObject redObject = new Sphere(ObjectColor.RED, 3.0);
        GraphicObject blueObject = new Cone(ObjectColor.BLUE, 2.0, 4.0);
        GraphicObject greenObject = new Cuboid(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        GraphicObject yellowObject = new Cuboid(ObjectColor.YELLOW, 2.0, 3.0, 4.0);

        // Check the order of the objects by surface area
        Assertions.assertEquals(greenObject.toString(), objectsBySurface.get(0), "Error: First object (by surface area) test");
        Assertions.assertEquals(blueObject.toString(), objectsBySurface.get(1), "Error: Second object (by surface area) test");
        Assertions.assertEquals(yellowObject.toString(), objectsBySurface.get(2), "Error: Third object (by surface area) test");
        Assertions.assertEquals(redObject.toString(), objectsBySurface.get(3), "Error: Fourth object (by surface area) test");
    }


    @Test
    public void testGetWaterInjected() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0); // Sphere volume: 113.09733552923254
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0); // Cone volume: 16.755160819145562
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0); // Cuboid volume: 6.0

        double containerBaseArea = 50.0;
        double containerHeight = 10.0;
        double containerVolume = containerBaseArea * containerHeight; // 500.0

        double expectedWaterInjected = containerVolume - (113.09733552923254 + 14.510394913873741 + 6.0);
        double actualWaterInjected = graphicObjectCollection.getWaterInjected(containerBaseArea, containerHeight);

        Assertions.assertEquals(expectedWaterInjected, actualWaterInjected, 1e-6, "Error: getWaterInjected test");
    }

}
