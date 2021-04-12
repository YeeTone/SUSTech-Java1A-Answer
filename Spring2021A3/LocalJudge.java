package Spring2021A3;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class LocalJudge {

    @Test
    public void test01_class() {
        findVector();
    }

    @Test
    public void test02_attributes() {
        findX();
        findY();
        findZ();
    }

    @Test
    public void test03_constructor() {
        findConstructor(Objects.requireNonNull(findVector()), Modifier.PUBLIC, int.class, int.class, int.class);
    }

    @Test
    public void test04_methods() {
        findQuadrant();
        findModulus();
        findToString();
        findAdd();
        findArea();
        findStaticAdd();
        findStaticArea();
    }

    @Test
    public void test05_quadrant() {
        assertEquals(1, callQuadrant(newVector(1, 2, 3)), "(1,2,3).quadrant should be 1");
        assertEquals(2, callQuadrant(newVector(-1, 2, 3)), "(-1,2,3).quadrant should be 2");
        assertEquals(3, callQuadrant(newVector(-1, -2, 3)), "(-1,-2,3).quadrant should be 3");
        assertEquals(4, callQuadrant(newVector(1, -2, 3)), "(1,-2,3).quadrant should be 4");
        assertEquals(5, callQuadrant(newVector(1, 2, -3)), "(1,2,-3).quadrant should be 5");
        assertEquals(6, callQuadrant(newVector(-1, 2, -3)), "(-1,2,-3).quadrant should be 6");
        assertEquals(7, callQuadrant(newVector(-1, -2, -3)), "(-1,-2,-3).quadrant should be 7");
        assertEquals(8, callQuadrant(newVector(1, -2, -3)), "(1,-2,-3).quadrant should be 8");

        assertEquals(-1, callQuadrant(newVector(0, 2, 3)), "(0,2,3).quadrant should be -1");
        assertEquals(-1, callQuadrant(newVector(1, 0, 3)), "(1,0,3).quadrant should be -1");
        assertEquals(-1, callQuadrant(newVector(1, 2, 0)), "(1,2,0).quadrant should be -1");
        assertEquals(-1, callQuadrant(newVector(1, 0, 0)), "(1,0,0).quadrant should be -1");
        assertEquals(-1, callQuadrant(newVector(0, 2, 0)), "(0,2,0).quadrant should be -1");
        assertEquals(-1, callQuadrant(newVector(0, 0, 3)), "(0,0,3).quadrant should be -1");
        assertEquals(-1, callQuadrant(newVector(0, 0, 0)), "(0,0,0).quadrant should be -1");
    }

    @Test
    public void test06_modulus() {
        assertEquals(0.0, callModulus(newVector(0, 0, 0)), 0.0001, "(0,0,0).modulus should be 1");
        assertEquals(1.0, callModulus(newVector(1, 0, 0)), 0.0001, "(1,0,0).modulus should be 2");
        assertEquals(1.41421356, callModulus(newVector(1, 1, 0)), 0.0001, "(1,1,0).modulus should be 3");
        assertEquals(1.73205080, callModulus(newVector(1, 1, 1)), 0.0001, "(1,1,1).modulus should be 4");
        assertEquals(3.74165738, callModulus(newVector(1, 2, -3)), 0.0001, "(1,2,-3).modulus should be 5");
        assertEquals(3.74165738, callModulus(newVector(1, -2, -3)), 0.0001, "(1,-2,-3).modulus should be 6");
    }

    @Test
    public void test07_toString() {
        assertEquals("(0,0,0)", callToString(newVector(0, 0, 0)), "toString should be (0,0,0)");
        assertEquals("(1,0,0)", callToString(newVector(1, 0, 0)), "toString should be (1,0,0)");
        assertEquals("(1,1,0)", callToString(newVector(1, 1, 0)), "toString should be (1,1,0)");
        assertEquals("(1,1,1)", callToString(newVector(1, 1, 1)), "toString should be (1,1,1)");
        assertEquals("(1,2,-3)", callToString(newVector(1, 2, -3)), "toString should be (1,2,-3)");
        assertEquals("(1,-2,-3)", callToString(newVector(1, -2, -3)), "toString should be (1,-2,-3)");
    }


    @Test
    public void test08_add() {
        assertVectorEquals(
                newVector(2, 4, 6),
                callAdd(
                        newVector(1, 2, 3),
                        newVector(1, 2, 3)
                ),
                "(1,2,3).add((1,2,3)) should be (2,4,6)"
        );
        assertVectorEquals(
                newVector(1, 2, 3),
                callAdd(
                        newVector(0, 0, 0),
                        newVector(1, 2, 3)
                ),
                "(0,0,0).add((1,2,3)) should be (1,2,3)"
        );
        assertVectorEquals(
                newVector(0, 0, 0),
                callAdd(
                        newVector(1, 2, 3),
                        newVector(-1, -2, -3)
                ),
                "(1,2,3).add((-1,-2,-3)) should be (0,0,0)"
        );
        assertVectorEquals(
                newVector(5, 3, -3),
                callAdd(
                        newVector(1, -2, 3),
                        newVector(4, 5, -6)
                ),
                "(1,-2,3).add((4,5,-6)) should be (5,3,-3)"
        );
    }

    @Test
    public void test09_area() {
        assertEquals(
                0.5,
                callArea(
                        newVector(1, 0, 0),
                        newVector(0, 1, 0)
                ),
                0.0001,
                "(1,0,0).area((0,1,0)) should be 0.5"
        );
        assertEquals(
                0.86602540,
                callArea(
                        newVector(1, 1, 0),
                        newVector(0, 1, 1)
                ),
                0.0001,
                "(1,1,0).area((0,1,1)) should be 0.86602540"
        );
        assertEquals(
                0.0,
                callArea(
                        newVector(0, 0, 0),
                        newVector(1, 1, 1)
                ),
                0.0001,
                "(0,0,0).area((1,1,1)) should be 0.0"
        );
        assertEquals(
                11.20267825,
                callArea(
                        newVector(1, 2, -3),
                        newVector(4, -5, 6)
                ),
                0.0001,
                "(1,2,-3).area((4,-5,6)) should be 11.20267825"
        );

    }

    @Test
    public void test10_static_add() {
        assertVectorEquals(
                newVector(2, 4, 6),
                callStaticAdd(
                        newVector(1, 2, 3),
                        newVector(1, 2, 3)
                ),
                "add((1,2,3),(1,2,3)) should be (2,4,6)"
        );
        assertVectorEquals(
                newVector(1, 2, 3),
                callStaticAdd(
                        newVector(0, 0, 0),
                        newVector(1, 2, 3)
                ),
                "add((0,0,0),(1,2,3)) should be (1,2,3)"
        );
        assertVectorEquals(
                newVector(0, 0, 0),
                callStaticAdd(
                        newVector(1, 2, 3),
                        newVector(-1, -2, -3)
                ),
                "add((1,2,3),(-1,-2,-3)) should be (0,0,0)"
        );
        assertVectorEquals(
                newVector(5, 3, -3),
                callStaticAdd(
                        newVector(1, -2, 3),
                        newVector(4, 5, -6)
                ),
                "add((1,-2,3),(4,5,-6)) should be (5,3,-3)"
        );
    }

    @Test
    public void test11_static_area() {
        assertEquals(
                0.5,
                callStaticArea(
                        newVector(1, 0, 0),
                        newVector(0, 1, 0)
                ),
                0.0001,
                "area((1,0,0),(0,1,0)) should be 0.5"
        );
        assertEquals(
                0.86602540,
                callStaticArea(
                        newVector(1, 1, 0),
                        newVector(0, 1, 1)
                ),
                0.0001,
                "area((1,1,0),(0,1,1)) should be 0.86602540"
        );
        assertEquals(
                0.0,
                callStaticArea(
                        newVector(0, 0, 0),
                        newVector(1, 1, 1)
                ),
                0.0001,
                "area((0,0,0),(1,1,1)) should be 0.0"
        );
        assertEquals(
                11.20267825,
                callStaticArea(
                        newVector(1, 2, -3),
                        newVector(4, -5, 6)
                ),
                0.0001,
                "area((1,2,-3),(4,-5,6)) should be 11.20267825"
        );

    }

    private void assertVectorEquals(Object v1, Object v2, String error) {
        if (getX(v1) != getX(v2) || getY(v1) != getY(v2) || getZ(v1) != getZ(v2)) {
            fail(error);
        }
    }

    private int getX(Object vector) {
        Field field = findX();
        field.setAccessible(true);
        try {
            return field.getInt(vector);
        } catch (IllegalAccessException e) {
            fail(e);
            return 0;
        }
    }

    private int getY(Object vector) {
        Field field = findY();
        field.setAccessible(true);
        try {
            return field.getInt(vector);
        } catch (IllegalAccessException e) {
            fail(e);
            return 0;
        }
    }

    private int getZ(Object vector) {
        Field field = findZ();
        field.setAccessible(true);
        try {
            return field.getInt(vector);
        } catch (IllegalAccessException e) {
            fail(e);
            return 0;
        }
    }

    private Object newVector(int x, int y, int z) {
        try {
            return findConstructor(findVector(), Modifier.PUBLIC, int.class, int.class, int.class)
                    .newInstance(x, y, z);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private int callQuadrant(Object vector) {
        try {
            return (int) findQuadrant().invoke(vector);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private double callModulus(Object vector) {
        try {
            return (double) findModulus().invoke(vector);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private String callToString(Object vector) {
        try {
            return (String) findToString().invoke(vector);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private Object callAdd(Object v1, Object v2) {
        try {
            return findAdd().invoke(v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private double callArea(Object v1, Object v2) {
        try {
            return (double) findArea().invoke(v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private Object callStaticAdd(Object v1, Object v2) {
        try {
            return findStaticAdd().invoke(null, v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    private double callStaticArea(Object v1, Object v2) {
        try {
            return (double) findStaticArea().invoke(null, v1, v2);
        } catch (IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return 0;
        }
    }

    private Class<?> findVector() {
        try {
            return Class.forName("Spring2021A3.Vector");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'A3.Vector'. Please check the class name. Class 'A3.Vector' should not in a package");
            return null;
        }
    }

    private Field findX() {
        return findField(findVector(), "x", int.class, Modifier.PRIVATE);
    }

    private Field findY() {
        return findField(findVector(), "y", int.class, Modifier.PRIVATE);
    }

    private Field findZ() {
        return findField(findVector(), "z", int.class, Modifier.PRIVATE);
    }

    private Method findQuadrant() {
        return findMethod(findVector(), "quadrant", Modifier.PUBLIC, int.class);
    }

    private Method findModulus() {
        return findMethod(findVector(), "modulus", Modifier.PUBLIC, double.class);
    }

    private Method findToString() {
        return findMethod(findVector(), "toString", Modifier.PUBLIC, String.class);
    }

    private Method findAdd() {
        return findMethod(findVector(), "add", Modifier.PUBLIC, findVector(), findVector());
    }

    private Method findArea() {
        return findMethod(findVector(), "area", Modifier.PUBLIC, double.class, findVector());
    }

    private Method findStaticAdd() {
        return findMethod(findVector(), "add",
                Modifier.PUBLIC | Modifier.STATIC, findVector(),
                findVector(), findVector());
    }

    private Method findStaticArea() {
        return findMethod(findVector(), "area",
                Modifier.PUBLIC | Modifier.STATIC, double.class,
                findVector(), findVector());
    }

    private Field findField(Class<?> clazz, String name, Class<?> type, int modifier) {
        try {
            Field field = clazz.getDeclaredField(name);
            if (!field.getType().equals(type)) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong type, it should be '%s'",
                        name, clazz.getCanonicalName(), type.toGenericString()
                ));
            }
            if (field.getModifiers() != modifier) {
                fail(String.format(
                        "Attribute '%s' in class '%s' has wrong modifier, it should be '%s'",
                        name, clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return field;
        } catch (NoSuchFieldException e) {
            fail(String.format("Cannot find attribute '%s' in class '%s'", name, clazz.getCanonicalName()));
            return null;
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz, int modifier, Class<?>... parameters) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameters);
            if (constructor.getModifiers() != modifier) {
                fail(String.format(
                        "Constructor '(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return constructor;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find constructor '(%s)' in class '%s'",
                    getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    private Method findMethod(Class<?> clazz, String name, int modifier, Class<?> returnType, Class<?>... parameters) {
        try {
            Method method = clazz.getDeclaredMethod(name, parameters);
            if (method.getReturnType() != returnType) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong return type, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), returnType.toGenericString()
                ));
            }
            if (method.getModifiers() != modifier) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        name, getParameterTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return method;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find method '%s(%s)' in class '%s'",
                    name, getParameterTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    private String getParameterTypeString(Class<?>[] parameters) {
        return Arrays.stream(parameters)
                .map(Class::getCanonicalName)
                .collect(Collectors.joining(", "));
    }
}
