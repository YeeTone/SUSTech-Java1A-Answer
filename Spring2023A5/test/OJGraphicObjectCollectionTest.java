package Spring2023A5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class OJGraphicObjectCollectionTest {
    private ObjectCollection graphicObjectCollection;

    @BeforeEach
    public void setUp() {
        graphicObjectCollection = new GraphicObjectCollection();
    }

    @Test
    public void testAddObjectAndGetObjectInfo() {
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.9, 4.5);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 6.24, 5.67, 9.36);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 8.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 4.8);

        // Test object count
        int objectCount = graphicObjectCollection.getObjectCount();

        Assertions.assertEquals(5, objectCount, "Error at line: object count test");

        // Test object info
        List<String> objectInfo = graphicObjectCollection.getObjectInfo();
        Assertions.assertEquals(5, objectInfo.size(),
            "Error at line: object info size test");

        Assertions.assertEquals("Cone: r=2.90, l=4.50", objectInfo.get(0),
            "Error at line: WHITE cone test");
        Assertions.assertEquals("Cuboid: x=2.00, y=3.00, z=4.00", objectInfo.get(1),
            "Error at line: GREEN cuboid test");
        Assertions.assertEquals("Cuboid: x=6.24, y=5.67, z=9.36", objectInfo.get(2),
            "Error at line: BLACK cuboid test");
        Assertions.assertEquals("Sphere: r=8.00", objectInfo.get(3),
            "Error at line: BLUE sphere test");
        Assertions.assertEquals("Sphere: r=4.80", objectInfo.get(4),
            "Error at line: GREEN sphere test");
    }

    @Test
    public void testGetCountsByColorSorting1() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        graphicObjectCollection.addObject(ObjectColor.RED, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 3.0, 5.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.9, 4.5);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 6.24, 5.67, 9.36);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 8.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 4.8);

        List<String> countsByColor = graphicObjectCollection.getCountsByColor();

        // Check the size of the countsByColor list
        Assertions.assertEquals(12, graphicObjectCollection.getObjectCount(),
            "Error at line: countsByColor size test");

        // Check the counts of each color and the order
        Assertions.assertEquals("RED 2", countsByColor.get(0), "Error: RED count test");
        Assertions.assertEquals("BLUE 3", countsByColor.get(1), "Error: BLUE count test");
        Assertions.assertEquals("GREEN 3", countsByColor.get(2), "Error: GREEN count test");
        Assertions.assertEquals("BLACK 2", countsByColor.get(3), "Error: BLACK count test");
        Assertions.assertEquals("WHITE 2", countsByColor.get(4), "Error: WHITE count test");
    }

    @Test
    public void testGetCountsByColorSorting2() {
        graphicObjectCollection.addObject(ObjectColor.RED, 3.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 2.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 1.0, 2.0, 3.0);
        graphicObjectCollection.addObject(ObjectColor.RED, 4.0);
        graphicObjectCollection.addObject(ObjectColor.RED, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 3.0, 5.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 3.0, 5.0);
        graphicObjectCollection.addObject(ObjectColor.YELLOW, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.RED, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.0, 3.0, 4.0);

        List<String> countsByColor = graphicObjectCollection.getCountsByColor();

        // Check the size of the countsByColor list
        Assertions.assertEquals(12, graphicObjectCollection.getObjectCount(),
            "Error at line: countsByColor size test");

        // Check the counts of each color and the order
        Assertions.assertEquals("RED 4", countsByColor.get(0), "Error: RED count test");
        Assertions.assertEquals("YELLOW 1", countsByColor.get(1), "Error: YELLOW count test");
        Assertions.assertEquals("BLUE 3", countsByColor.get(2), "Error: BLUE count test");
        Assertions.assertEquals("GREEN 1", countsByColor.get(3), "Error: GREEN count test");
        Assertions.assertEquals("BLACK 1", countsByColor.get(4), "Error: BLACK count test");
        Assertions.assertEquals("WHITE 2", countsByColor.get(5), "Error: WHITE count test");
    }

    @Test
    public void testGetObjectByVolumeSorting() {
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.9, 4.5);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 6.24, 5.67, 9.36);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 8.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 4.8);

        List<String> objectsByVolume = graphicObjectCollection.getObjectByVolume();

        // Check the size of the objectsByVolume list
        Assertions.assertEquals(5, objectsByVolume.size(), "Error: objectsByVolume size test");

        // Check the order of the objects by volume
        Assertions.assertEquals("Cuboid: x=2.00, y=3.00, z=4.00", objectsByVolume.get(0), "Error: First object (by volume) test");
        Assertions.assertEquals("Cone: r=2.90, l=4.50", objectsByVolume.get(1), "Error: Second object (by volume) test");
        Assertions.assertEquals("Cuboid: x=6.24, y=5.67, z=9.36", objectsByVolume.get(2), "Error: Third object (by volume) test");
        Assertions.assertEquals("Sphere: r=4.80", objectsByVolume.get(3), "Error: Fourth object (by volume) test");
        Assertions.assertEquals("Sphere: r=8.00", objectsByVolume.get(4), "Error: Fifth object (by volume) test");
    }

    @Test
    public void testGetObjectsBySurfaceSorting() {
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.9, 4.5);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 6.24, 5.67, 9.36);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 8.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 4.8);

        List<String> objectsBySurface = graphicObjectCollection.getObjectsBySurface();

        // Check the size of the objectsBySurface list
        Assertions.assertEquals(5, objectsBySurface.size(), "Error: objectsBySurface size test");

        // Check the order of the objects by surface area
        Assertions.assertEquals("Cuboid: x=2.00, y=3.00, z=4.00", objectsBySurface.get(0), "Error: First object (by surface area) test");
        Assertions.assertEquals("Cone: r=2.90, l=4.50", objectsBySurface.get(1), "Error: Second object (by surface area) test");
        Assertions.assertEquals("Sphere: r=4.80", objectsBySurface.get(2), "Error: Third object (by surface area) test");
        Assertions.assertEquals("Cuboid: x=6.24, y=5.67, z=9.36", objectsBySurface.get(3), "Error: Fourth object (by surface area) test");
        Assertions.assertEquals("Sphere: r=8.00", objectsBySurface.get(4), "Error: Fifth object (by surface area) test");
    }


    @Test
    public void testGetWaterInjected() {
        graphicObjectCollection.addObject(ObjectColor.WHITE, 2.9, 4.5);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 2.0, 3.0, 4.0);
        graphicObjectCollection.addObject(ObjectColor.BLACK, 6.24, 5.67, 9.36);
        graphicObjectCollection.addObject(ObjectColor.BLUE, 8.0);
        graphicObjectCollection.addObject(ObjectColor.GREEN, 4.8);
        double[] expectedVolumes = new double[]{24.0, 30.30403542207566, 331.164288,
            463.2466863277364, 2144.660584850632};

        double containerBaseArea = 5000.0;
        double containerHeight = 100.0;
        double containerVolume = containerBaseArea * containerHeight; // 500000.0

        double expectedWaterInjected = containerVolume - Arrays.stream(expectedVolumes).sum();
        double actualWaterInjected = graphicObjectCollection.getWaterInjected(containerBaseArea, containerHeight);

        Assertions.assertEquals(expectedWaterInjected, actualWaterInjected, 1e-6,
            "Error: getWaterInjected test");
    }
}
