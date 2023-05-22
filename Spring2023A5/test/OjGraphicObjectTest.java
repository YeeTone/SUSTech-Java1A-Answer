package Spring2023A5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OjGraphicObjectTest {

    private Cone cone1;
    private Cone cone2;

    private Cuboid cuboid1;
    private Cuboid cuboid2;
    private Cuboid cuboid3;
    private Cuboid cuboid4;

    private Sphere sphere1;
    private Sphere sphere2;
    private Sphere sphere3;

    @BeforeEach
    void setUp() {
        cone1 = new Cone(ObjectColor.YELLOW, 3.0, 5.0);
        cone2 = new Cone(ObjectColor.WHITE, 2.9, 4.5);

        cuboid1 = new Cuboid(ObjectColor.GREEN, 2.0, 3.0, 4.0);
        cuboid2 = new Cuboid(ObjectColor.BLACK, 6.1, 6.8, 2.6);
        cuboid3 = new Cuboid(ObjectColor.BLACK, 6.24, 5.67, 9.36);
        cuboid4 = new Cuboid(ObjectColor.RED, 3.15, 4.28, 6.98);

        sphere1 = new Sphere(ObjectColor.BLUE, 8.0);
        sphere2 = new Sphere(ObjectColor.GREEN, 4.8);
        sphere3 = new Sphere(ObjectColor.YELLOW, 9.77);
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
        String constructorSignature = "public " + clazz.getName() + "("+ObjectColor.class.getName();
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
        testObjectAttributes(cone2, new String[]{"radius", "length"}, new Object[]{2.9, 4.5}, ObjectColor.WHITE);

        // Cuboid attributes
        testObjectAttributes(cuboid3, new String[]{"x", "y", "z"}, new Object[]{6.24, 5.67, 9.36}, ObjectColor.BLACK);

        // Sphere attributes
        testObjectAttributes(sphere1, new String[]{"radius"}, new Object[]{8.0}, ObjectColor.BLUE);
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
        double coneSurface1 = cone1.surfaceMeanSize();
        double coneSurface2 = cone2.surfaceMeanSize();
        assertEquals(75.39, coneSurface1, 0.01);
        assertEquals(67.41, coneSurface2, 0.01);

        double cuboidSurface1 = cuboid1.surfaceMeanSize();
        double cuboidSurface2 = cuboid2.surfaceMeanSize();
        double cuboidSurface3 = cuboid3.surfaceMeanSize();
        double cuboidSurface4 = cuboid4.surfaceMeanSize();
        assertEquals(52.00, cuboidSurface1, 0.01);
        assertEquals(150.04, cuboidSurface2, 0.01);
        assertEquals(293.71, cuboidSurface3, 0.01);
        assertEquals(130.68, cuboidSurface4, 0.01);

        double sphereSurface1 = sphere1.surfaceMeanSize();
        double sphereSurface2 = sphere2.surfaceMeanSize();
        double sphereSurface3 = sphere3.surfaceMeanSize();
        assertEquals(804.24, sphereSurface1, 0.01);
        assertEquals(289.52, sphereSurface2, 0.01);
        assertEquals(1199.49, sphereSurface3, 0.01);
    }

    @Test
    void testVolume() {
        double coneVolume1 = cone1.volume();
        double coneVolume2 = cone2.volume();
        assertEquals(37.69, coneVolume1, 0.01);
        assertEquals(30.30, coneVolume2, 0.01);

        double cuboidVolume1 = cuboid1.volume();
        double cuboidVolume2 = cuboid2.volume();
        double cuboidVolume3 = cuboid3.volume();
        double cuboidVolume4 = cuboid4.volume();
        assertEquals(24.00, cuboidVolume1, 0.01);
        assertEquals(107.84, cuboidVolume2, 0.01);
        assertEquals(331.16, cuboidVolume3, 0.01);
        assertEquals(94.10, cuboidVolume4, 0.01);

        double sphereVolume1 = sphere1.volume();
        double sphereVolume2 = sphere2.volume();
        double sphereVolume3 = sphere3.volume();
        assertEquals(2144.66, sphereVolume1, 0.01);
        assertEquals(463.24, sphereVolume2, 0.01);
        assertEquals(3906.36, sphereVolume3, 0.01);
    }

    @Test
    void testToString() {
        String coneString1 = cone1.toString();
        String coneString2 = cone2.toString();
        assertEquals("Cone: r=3.00, l=5.00", coneString1);
        assertEquals("Cone: r=2.90, l=4.50", coneString2);

        String cuboidString1 = cuboid1.toString();
        String cuboidString2 = cuboid2.toString();
        String cuboidString3 = cuboid3.toString();
        String cuboidString4 = cuboid4.toString();
        assertEquals("Cuboid: x=2.00, y=3.00, z=4.00", cuboidString1);
        assertEquals("Cuboid: x=6.10, y=6.80, z=2.60", cuboidString2);
        assertEquals("Cuboid: x=6.24, y=5.67, z=9.36", cuboidString3);
        assertEquals("Cuboid: x=3.15, y=4.28, z=6.98", cuboidString4);

        String sphereString1 = sphere1.toString();
        String sphereString2 = sphere2.toString();
        String sphereString3 = sphere3.toString();
        assertEquals("Sphere: r=8.00", sphereString1);
        assertEquals("Sphere: r=4.80", sphereString2);
        assertEquals("Sphere: r=9.77", sphereString3);
    }
}


