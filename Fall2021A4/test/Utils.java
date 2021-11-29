package Fall2021A4;

import java.io.Closeable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Utils {

    public static class ErrorLogger implements Closeable {
        private final List<String> errors = new ArrayList<>();

        public void log(String message) {
            errors.add(message);
        }

        @Override
        public void close() {
            assertTrue(errors.isEmpty(), String.join(System.lineSeparator(), errors));
        }
    }

    public static void checkClass(Class<?> clazz, String[] fieldsExpected, String[] methodsExpected, String[] methodsUnexpected) {
        Set<String> fields = Arrays.stream(clazz.getDeclaredFields()).map(Field::toString).collect(Collectors.toSet());
        Set<String> methods = Arrays.stream(clazz.getDeclaredMethods()).map(Method::toString).collect(Collectors.toSet());
        try (ErrorLogger err = new ErrorLogger()) {
            for (String field : fieldsExpected) {
                if (!fields.contains(field)) {
                    err.log(field + " is missing!");
                }
            }
            for (String method : methodsExpected) {
                if (!methods.contains(method)) {
                    err.log(method + " is missing!");
                }
            }
            for (String method : methodsUnexpected) {
                if (methods.contains(method)) {
                    err.log(method + " should not exist!");
                }
            }
        }
    }

    public static void checkClass(Class<?> clazz, String[] fieldsExpected, String[] methodsExpected) {
        checkClass(clazz, fieldsExpected, methodsExpected, new String[]{});
    }

}
