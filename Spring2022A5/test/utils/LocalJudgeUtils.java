package utils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class LocalJudgeUtils {
    public static Class<?> findChessGameClass() {
        try {
            Class<?> chessGame = Class.forName("ChessGame");
            assertTrue(
                    chessGame.isInterface(),
                    "Class 'ChessGame' is not an Interface"
            );
            return chessGame;
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'ChessGame'. Please check the class name. Class 'ChessGame' should not in a package");
            return null;
        }
    }

    public static Class<?> findConcreteChessGameClass() {
        try {
            Class<?> concreteChessGame = Class.forName("ConcreteChessGame");
            assertEquals(findChessGameClass(), concreteChessGame.getInterfaces()[0], "Class 'ConcreteChessGame is not derived from Interface 'ChessGame'");
            return concreteChessGame;
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'ConcreteChessGame'. Please check the class name. Class 'ConcreteChessGame' should not in a package");
            return null;
        }
    }

    public static Class<?> findChessboardPointClass() {
        try {
            return Class.forName("ChessboardPoint");
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'ChessboardPoint'. Please check the class name. Class 'ChessboardPoint' should not in a package");
            return null;
        }
    }

    public static Class<?> findChessComponentClass() {
        try {
            Class<?> chessComponent = Class.forName("ChessComponent");
            assertEquals((Modifier.PUBLIC | Modifier.ABSTRACT), chessComponent.getModifiers(), "Class 'ChessComponent' is is not a public abstract class");
            return chessComponent;
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'ChessComponent'. Please check the class name. Class 'ChessComponent' should not in a package");
            return null;
        }
    }

    public static Class<?> findChessColorClass() {
        try {
            Class<?> chessColor = Class.forName("ChessColor");
            assertTrue(
                    chessColor.isEnum(),
                    "Class 'ChessColor' is not an enum class"
            );
            return chessColor;
        } catch (ClassNotFoundException e) {
            fail("Cannot find class 'ChessColor'. Please check the class name. Class 'ChessColor' should not in a package");
            return null;
        }
    }

    public static Field findXFieldInChessboardPointClass() {
        return findField(
                Objects.requireNonNull(findChessboardPointClass()),
                "x",
                int.class,
                Modifier.PRIVATE
        );
    }

    public static Field findYFieldInChessboardPointClass() {
        return findField(
                Objects.requireNonNull(findChessboardPointClass()),
                "y",
                int.class,
                Modifier.PRIVATE
        );
    }

    public static Field findSourceInChessComponentClass() {
        return findField(
                Objects.requireNonNull(findChessComponentClass()),
                "source",
                findChessboardPointClass(),
                Modifier.PRIVATE
        );
    }

    public static Field findChessColorFieldInChessComponentClass() {
        return findField(
                Objects.requireNonNull(findChessComponentClass()),
                "chessColor",
                findChessColorClass(),
                Modifier.PRIVATE
        );
    }

    public static Field findNameFieldInChessComponentClass() {
        return findField(
                Objects.requireNonNull(findChessComponentClass()),
                "name",
                char.class,
                Modifier.PROTECTED
        );
    }

    public static Field findChessComponentsFieldInConcreteChessGameClass() {
        return findField(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "chessComponents",
                Objects.requireNonNull(Array.newInstance(
                        Array.newInstance(
                                findChessComponentClass(),
                                0).getClass(),
                        0
                ).getClass()),
                Modifier.PRIVATE
        );
    }

    public static Field findCurrentPlayerFieldInConcreteChessGameClass() {
        return findField(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "currentPlayer",
                findChessColorClass(),
                Modifier.PRIVATE
        );
    }

    public static Constructor<?> findChessboardPointConstructor() {
        return findConstructor(
                Objects.requireNonNull(findChessboardPointClass()),
                Modifier.PUBLIC,
                int.class,
                int.class
        );
    }

    public static Constructor<?> findChessComponentConstructor() {
        return findConstructor(
                Objects.requireNonNull(findChessComponentClass()),
                Modifier.PUBLIC
        );
    }

    public static Constructor<?> findConcreteChessGameConstructor() {
        return findConstructor(
                Objects.requireNonNull(findConcreteChessGameClass()),
                Modifier.PUBLIC
        );
    }

    public static Method findGetXMethodInChessboardPointClass() {
        return findMethod(
                Objects.requireNonNull(findChessboardPointClass()),
                "getX",
                Modifier.PUBLIC,
                int.class
        );
    }

    public static Method findGetYMethodInChessboardPointClass() {
        return findMethod(
                Objects.requireNonNull(findChessboardPointClass()),
                "getY",
                Modifier.PUBLIC,
                int.class
        );
    }

    public static Method findToStringMethodInChessboardPointClass() {
        return findMethod(
                Objects.requireNonNull(findChessboardPointClass()),
                "toString",
                Modifier.PUBLIC,
                String.class
        );
    }

    public static Method findOffsetMethodInChessboardPointClass() {
        return findMethod(
                Objects.requireNonNull(findChessboardPointClass()),
                "offset",
                Modifier.PUBLIC,
                findChessboardPointClass(),
                int.class,
                int.class
        );
    }

    public static Method findCanMoveToMethodInChessComponentClass()  {
        return findMethod(
                Objects.requireNonNull(findChessComponentClass()),
                "canMoveTo",
                Modifier.PUBLIC | Modifier.ABSTRACT,
                List.class
        );
    }

    public static Method findToStringMethodInChessComponentClass() {
        return findMethod(
                Objects.requireNonNull(findChessComponentClass()),
                "toString",
                Modifier.PUBLIC,
                String.class
        );
    }

    public static Method findLoadChessGameMethodInConcreteChessGameClass() {
        return findMethod(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "loadChessGame",
                Modifier.PUBLIC,
                void.class,
                List.class
        );
    }

    public static Method findGetCurrentPlayerMethodInConcreteChessGameClass() {
        return findMethod(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "getCurrentPlayer",
                Modifier.PUBLIC,
                findChessColorClass()
        );
    }

    public static Method findGetChessMethodInConcreteChessGameClass() {
        return findMethod(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "getChess",
                Modifier.PUBLIC,
                findChessComponentClass(),
                int.class,
                int.class
        );
    }

    public static Method findGetChessboardGraphMethodInConcreteChessGameClass() {
        return findMethod(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "getChessboardGraph",
                Modifier.PUBLIC,
                String.class
        );
    }

    public static Method findGetCapturedChessMethodInConcreteChessGameClass() {
        return findMethod(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "getCapturedChess",
                Modifier.PUBLIC,
                String.class,
                findChessColorClass()
        );
    }

    public static Method findGetCanMovePointsMethodInConcreteChessGameClass() {
        return findMethod(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "getCanMovePoints",
                Modifier.PUBLIC,
                List.class,
                findChessboardPointClass()
        );
    }

    public static Method findMoveChessMethodInConcreteChessGameClass() {
        return findMethod(
                Objects.requireNonNull(findConcreteChessGameClass()),
                "moveChess",
                Modifier.PUBLIC,
                boolean.class,
                int.class,
                int.class,
                int.class,
                int.class
        );
    }

    public static Object newChessboardPoint(Object... parameters) {
        try {
            return findChessboardPointConstructor().newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    public static Object newConcreteChessGame(Object... parameters) {
        try {
            return findConcreteChessGameConstructor().newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            fail(e);
            return null;
        }
    }

    public static Object callGetXMethodInChessboardPointClass(Object chessboardPoint, Object... parameters) {
        try {
            return findGetXMethodInChessboardPointClass().invoke(chessboardPoint, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callGetYMethodInChessboardPointClass(Object chessboardPoint, Object... parameters) {
        try {
            return findGetYMethodInChessboardPointClass().invoke(chessboardPoint, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callToStringMethodInChessboardPointClass(Object chessboardPoint, Object... parameters) {
        try {
            return findToStringMethodInChessboardPointClass().invoke(chessboardPoint, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callOffsetMethodInChessboardPointClass(Object chessboardPoint, Object... parameters) {
        try {
            return findOffsetMethodInChessboardPointClass().invoke(chessboardPoint, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callCanMoveToMethodInChessComponentClass(Object chessComponent, Object... parameters) {
        try {
            return findCanMoveToMethodInChessComponentClass().invoke(chessComponent, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callToStringMethodInChessComponentClass(Object chessComponent, Object... parameters) {
        try {
            return findToStringMethodInChessComponentClass().invoke(chessComponent, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callLoadChessGameMethodInConcreteChessGameClass(Object concreteChessGame, Object... parameters) {
        try {
            return findLoadChessGameMethodInConcreteChessGameClass().invoke(concreteChessGame, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callGetCurrentPlayerMethodInConcreteChessGameClass(Object concreteChessGame, Object... parameters) {
        try {
            return findGetCurrentPlayerMethodInConcreteChessGameClass().invoke(concreteChessGame, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callGetChessMethodInConcreteChessGameClass(Object concreteChessGame, Object... parameters) {
        try {
            return findGetChessMethodInConcreteChessGameClass().invoke(concreteChessGame, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callGetChessboardGraphMethodInConcreteChessGameClass(Object concreteChessGame, Object... parameters) {
        try {
            return findGetChessboardGraphMethodInConcreteChessGameClass().invoke(concreteChessGame, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callGetCapturedChessMethodInConcreteChessGameClass(Object concreteChessGame, Object... parameters) {
        try {
            return findGetCapturedChessMethodInConcreteChessGameClass().invoke(concreteChessGame, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callGetCanMovePointsMethodInConcreteChessGameClass(Object concreteChessGame, Object... parameters) {
        try {
            return findGetCanMovePointsMethodInConcreteChessGameClass().invoke(concreteChessGame, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Object callMoveChessMethodInConcreteChessGameClass(Object concreteChessGame, Object... parameters) {
        try {
            return findMoveChessMethodInConcreteChessGameClass().invoke(concreteChessGame, parameters);
        } catch (InvocationTargetException | IllegalAccessException e) {
            fail(e);
            return null;
        }
    }

    public static Field findField(Class<?> clazz, String name, Class<?> type, int modifier) {
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
            fail(String.format(
                    "Cannot find the attribute '%s' in class '%s'",
                    name, clazz.getCanonicalName()
            ));
            return null;
        }
    }

    public static Constructor<?> findConstructor(Class<?> clazz, int modifier, Class<?>... parameters) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(parameters);
            if (constructor.getModifiers() != modifier) {
                fail(String.format(
                        "Constructor '(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        getParametersTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return constructor;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find constructor '(%s)' in class '%s'",
                    getParametersTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    public static Method findMethod(Class<?> clazz, String name, int modifier, Class<?> returnType, Class<?>... parameters) {
        try {
            Method method = clazz.getDeclaredMethod(name, parameters);
            if (!method.getReturnType().equals(returnType)) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong return type, it should be '%s'",
                        name, getParametersTypeString(parameters), clazz.getCanonicalName(), returnType.toGenericString()
                ));
            }
            if (method.getModifiers() != modifier) {
                fail(String.format(
                        "Method '%s(%s)' in class '%s' has wrong modifier, it should be '%s'",
                        name, getParametersTypeString(parameters), clazz.getCanonicalName(), Modifier.toString(modifier)
                ));
            }
            return method;
        } catch (NoSuchMethodException e) {
            fail(String.format(
                    "Cannot find the method '%s(%s)' in class '%s'",
                    name, getParametersTypeString(parameters), clazz.getCanonicalName()
            ));
            return null;
        }
    }

    public static String getParametersTypeString(Class<?>... parameters) {
        return Arrays.stream(parameters)
                .map(Class::getCanonicalName)
                .collect(Collectors.joining(", "));
    }
}
