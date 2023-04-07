import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConversionTest {
    static String error = "Error";
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER_MAP = Map.of(
            boolean.class, Boolean.class,
            byte.class, Byte.class,
            char.class, Character.class,
            short.class, Short.class,
            int.class, Integer.class,
            long.class, Long.class,
            float.class, Float.class,
            double.class, Double.class
    );

    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE_MAP = PRIMITIVE_TO_WRAPPER_MAP.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    public static String checkConversion(Class<?> from, Class<?> to) {
        if (from.equals(to)) {
            return "Identity";
        }

        if (from.isPrimitive() && to.isPrimitive()) {
            return checkPrimitiveConversion(from, to);
        }

        if (from.isPrimitive()) {
            Class<?> boxedFrom = box(from);
            if (boxedFrom.equals(to)) {
                return "Boxing";
            }
            if (to.isAssignableFrom(boxedFrom)) {
                return "Boxing & Widening";
            }
        }

        if (to.isPrimitive()) {
            Class<?> unboxedFrom = unbox(from);
            if (unboxedFrom.equals(to)) {
                return "Unboxing";
            }
            if (unboxedFrom.isAssignableFrom(to)) {
                return "Unboxing & Widening";
            }
            if (to.isAssignableFrom(unboxedFrom)) {
                return "Unboxing & Narrowing";
            }
        }

        if (to.isAssignableFrom(from)) {
            return "Widening";
        }

        return error;
    }

    private static String checkPrimitiveConversion(Class<?> from, Class<?> to) {
        // List of primitive types in widening order, including char
        List<Class<?>> numericWideningOrder = List.of(
                byte.class, short.class, char.class, int.class, long.class, float.class, double.class
        );

        int fromIndex = numericWideningOrder.indexOf(from);
        int toIndex = numericWideningOrder.indexOf(to);

        if (fromIndex != -1 && toIndex != -1) {
            if (fromIndex < toIndex) {
                return "Widening";
            }

            if (fromIndex > toIndex) {
                return "Narrowing";
            }
        }

        return error;
    }

    private static String checkUnboxingConversion(Class<?> reference, Class<?> primitive) {
        Map<Class<?>, Class<?>> unboxingMap = Map.of(
                Boolean.class, boolean.class,
                Byte.class, byte.class,
                Short.class, short.class,
                Integer.class, int.class,
                Long.class, long.class,
                Float.class, float.class,
                Double.class, double.class,
                Character.class, char.class
        );

        if (unboxingMap.get(reference).equals(primitive)) {
            return "Unboxing";
        }

        return error;
    }

    private static String checkBoxingConversion(Class<?> primitive, Class<?> reference) {
        Map<Class<?>, Class<?>> boxingMap = Map.of(
                boolean.class, Boolean.class,
                byte.class, Byte.class,
                short.class, Short.class,
                int.class, Integer.class,
                long.class, Long.class,
                float.class, Float.class,
                double.class, Double.class,
                char.class, Character.class

        );

        if (boxingMap.get(primitive).equals(reference)) {
            return "Boxing";
        }

        return error;
    }

    private static Class<?> box(Class<?> primitive) {
        return PRIMITIVE_TO_WRAPPER_MAP.get(primitive);
    }

    private static Class<?> unbox(Class<?> wrapper) {
        return WRAPPER_TO_PRIMITIVE_MAP.get(wrapper);
    }

    // main method for testing
    public static void main(String[] args) {
        System.out.println("To: \t" + "bool\t" + "byte\t" + "char\t" + "short\t" + "int\t" + "long\t" + "float\t" +
                "double\t" + "Boolean\t" + "Byte\t" + "Character\t" + "Short\t" + "Integer\t" + "Long\t" +
                "Float\t" + "Double\t");
        System.out.println("From:");
        // define the classes to be used in the table


        Class<?>[] classes;
        // define the classes to be used in the table
        classes = new Class<?>[]{boolean.class, byte.class, char.class, short.class, int.class, long.class,
                float.class, double.class, Boolean.class, Byte.class, Character.class, Short.class, Integer.class,
                Long.class, Float.class, Double.class};
        // print the table
        for (Class<?> from : classes) {
            System.out.print(from.getSimpleName() + "\t");
            for (Class<?> to : classes) {
                System.out.print(checkConversion(from, to) + "\t");
            }
            System.out.println();
        }
    }
}
