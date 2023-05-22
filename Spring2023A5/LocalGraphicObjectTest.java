package Spring2023A5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LocalGraphicObjectTest {

    private Cone cone;
    private Cuboid cuboid;
    private Sphere sphere;
    private Cone cone2;
    private Cuboid cuboid2;
    private Sphere sphere2;

    @BeforeEach
    void setUp() {
        cone = new Cone(ObjectColor.RED, 5, 10);
        cuboid = new Cuboid(ObjectColor.BLUE,  5, 10, 15);
        sphere = new Sphere(ObjectColor.GREEN, 7);
        cone2 = new Cone(ObjectColor.BLUE, 3.10, 4.20);
        cuboid2 = new Cuboid(ObjectColor.GREEN,  2.25, 4.55, 6.65);
        sphere2 = new Sphere(ObjectColor.RED, 5.80);
    }

    @Test
    void testDefinedFieldsAndMethods() throws NoSuchFieldException, NoSuchMethodException {
        // Cone fields, constructor and methods
        testFieldsAndMethods(
                Cone.class,
                new String[]{"radius", "length"},
                new Class<?>[]{double.class, double.class},
                new Class<?>[]{ObjectColor.class, double.class, double.class}
        );

        // Cuboid fields, constructor and methods
        testFieldsAndMethods(
                Cuboid.class,
                new String[]{"x", "y", "z"},
                new Class<?>[]{double.class, double.class, double.class},
                new Class<?>[]{ObjectColor.class, double.class, double.class, double.class}
        );

        // Sphere fields, constructor and methods
        testFieldsAndMethods(
                Sphere.class,
                new String[]{"radius"},
                new Class<?>[]{double.class},
                new Class<?>[]{ObjectColor.class, double.class}
        );
    }

    void testFieldsAndMethods(Class<?> clazz, String[] fieldNames, Class<?>[] fieldTypes, Class<?>[] constructorParamTypes) throws NoSuchFieldException, NoSuchMethodException {
        for (int i = 0; i < fieldNames.length; i++) {
            Field field = clazz.getDeclaredField(fieldNames[i]);
            assertEquals(fieldTypes[i], field.getType());
        }

        Constructor<?> constructor = clazz.getDeclaredConstructor(constructorParamTypes);
        String constructorSignature = "public " + clazz.getSimpleName() + "(ObjectColor";
        for (int i = 0; i < constructorParamTypes.length - 1; i++) {
            constructorSignature += ",double";
        }
        constructorSignature += ")";
        assertEquals(constructorSignature, constructor.toString());

        Method surfaceMeanSize = clazz.getDeclaredMethod("surfaceMeanSize");
        Method volume = clazz.getDeclaredMethod("volume");
        Method toString = clazz.getDeclaredMethod("toString");

        assertEquals(double.class, surfaceMeanSize.getReturnType());
        assertEquals(double.class, volume.getReturnType());
        assertEquals(String.class, toString.getReturnType());
    }



    @Test
    void testAttributes() throws NoSuchFieldException, IllegalAccessException {
        // Cone attributes
        testObjectAttributes(cone, new String[]{"radius", "length"}, new Object[]{5.0, 10.0}, ObjectColor.RED);

        // Cuboid attributes
        testObjectAttributes(cuboid, new String[]{"x", "y", "z"}, new Object[]{5.0, 10.0, 15.0}, ObjectColor.BLUE);

        // Sphere attributes
        testObjectAttributes(sphere, new String[]{"radius"}, new Object[]{7.0}, ObjectColor.GREEN);
    }

    void testObjectAttributes(Object obj, String[] fieldNames, Object[] expectedValues, ObjectColor expectedColor) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field idField = clazz.getSuperclass().getDeclaredField("id");
        Field colorField = clazz.getSuperclass().getDeclaredField("color");

        idField.setAccessible(true);
        colorField.setAccessible(true);

        assertEquals(expectedColor, colorField.get(obj));

        for (int i = 0; i < fieldNames.length; i++) {
            Field field = clazz.getDeclaredField(fieldNames[i]);
            field.setAccessible(true);
            assertEquals(expectedValues[i], field.get(obj));
        }
    }


    @Test
    void testSurfaceMeanSize() {
        double coneSurface = cone.surfaceMeanSize();
        double cuboidSurface = cuboid.surfaceMeanSize();
        double sphereSurface = sphere.surfaceMeanSize();

        assertEquals(235.61, coneSurface, 0.01);
        assertEquals(550.00, cuboidSurface, 0.01);
        assertEquals(615.75, sphereSurface, 0.01);

        double cone2Surface = cone2.surfaceMeanSize();
        double cuboid2Surface = cuboid2.surfaceMeanSize();
        double sphere2Surface = sphere2.surfaceMeanSize();

        assertEquals(71.09, cone2Surface, 0.01);
        assertEquals(110.91, cuboid2Surface, 0.01);
        assertEquals(422.73, sphere2Surface, 0.01);
    }

    @Test
    void testVolume() {
        double coneVolume = cone.volume();
        double cuboidVolume = cuboid.volume();
        double sphereVolume = sphere.volume();

        assertEquals(226.72, coneVolume, 0.01);
        assertEquals(750, cuboidVolume, 0.01);
        assertEquals(1436.76, sphereVolume, 0.01);

        double cone2Volume = cone2.volume();
        double cuboid2Volume = cuboid2.volume();
        double sphere2Volume = sphere2.volume();

        assertEquals(28.51, cone2Volume, 0.01);
        assertEquals(68.07, cuboid2Volume, 0.01);
        assertEquals(817.28, sphere2Volume, 0.01);
    }

    @Test
    void testToString() {
        String coneString = cone.toString();
        String cuboidString = cuboid.toString();
        String sphereString = sphere.toString();

        assertEquals("Cone: r=5.00, l=10.00", coneString);
        assertEquals("Cuboid: x=5.00, y=10.00, z=15.00", cuboidString);
        assertEquals("Sphere: r=7.00", sphereString);

        String cone2String = cone2.toString();
        String cuboid2String = cuboid2.toString();
        String sphere2String = sphere2.toString();

        assertEquals("Cone: r=3.10, l=4.20", cone2String);
        assertEquals("Cuboid: x=2.25, y=4.55, z=6.65", cuboid2String);
        assertEquals("Sphere: r=5.80", sphere2String);
    }
}


