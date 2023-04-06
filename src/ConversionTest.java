import java.util.List;
import java.util.Map;

public class ConversionTest {
    static String error = "Error";
    public static String checkConversion(Class<?> from, Class<?> to) {
        if (from.equals(to)) {
            return "Identity";
        }

        if (from.isPrimitive() && to.isPrimitive()) {
            return checkPrimitiveConversion(from, to);
        }

        if (from.isPrimitive()) {
            return checkBoxingConversion(from, to);
        }

        if (to.isPrimitive()) {
            return checkUnboxingConversion(from, to);
        }

        if (to.isAssignableFrom(from)) {
            return "Widening";
        }


        return error;
    }

    private static String checkPrimitiveConversion(Class<?> from, Class<?> to) {
        // List of primitive types in widening order
        List<Class<?>> wideningOrder = List.of(
                byte.class, short.class, int.class, long.class, float.class, double.class
        );

        int fromIndex = wideningOrder.indexOf(from);
        int toIndex = wideningOrder.indexOf(to);

        if (fromIndex < toIndex) {
            return "Widening";
        }

        if (fromIndex > toIndex) {
            return "Narrowing";
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

    // main method for testing
    public static void main(String[] args) {
        /**
         *
         To: 	 bool char int  l  d  Bool Char Int  L	D
         From:
         boolean	I	E	E	E	E	B	E	E	E	E
         char	    E	I	W	W	W	E	B	E	E	E
         int	    E	N 	I	W	W	E	E	B	E	E
         long	    E	N 	N 	I	W	E	E	E	B	E
         double 	E	N 	N 	N 	I	E	E	E	E	B
         Boolean	U	E	E	E	E	I	E	E	E	E
         Character	E	U	E	E	E	E	I	E	E	E
         Integer	E	E	U	E	E	E	E	I	E	E
         Long	    E	E	E	U	E	E	E	E	I	E
         Double	    E	E	E	E	U	E	E	E	E	I
         *
         */
        // check all the above cases
        System.out.println("To:\tbool\tchar\tint\tl\td\tBool\tChar\tInt\tL\tD");
        System.out.println("From");
        System.out.println("boolean\t" + checkConversion(boolean.class, boolean.class) + "\t" +
                checkConversion(boolean.class, char.class) + "\t" + checkConversion(boolean.class, int.class) + "\t" +
                checkConversion(boolean.class, long.class) + "\t" + checkConversion(boolean.class, double.class) + "\t" +
                checkConversion(boolean.class, Boolean.class) + "\t" + checkConversion(boolean.class, Character.class) + "\t" +
                checkConversion(boolean.class, Integer.class) + "\t" + checkConversion(boolean.class, Long.class) + "\t" +
                checkConversion(boolean.class, Double.class));

        System.out.println("char\t" + checkConversion(char.class, boolean.class) + "\t" +
                checkConversion(char.class, char.class) + "\t" + checkConversion(char.class, int.class) + "\t" +
                checkConversion(char.class, long.class) + "\t" + checkConversion(char.class, double.class) + "\t" +
                checkConversion(char.class, Boolean.class) + "\t" + checkConversion(char.class, Character.class) + "\t" +
                checkConversion(char.class, Integer.class) + "\t" + checkConversion(char.class, Long.class) + "\t" +
                checkConversion(char.class, Double.class));

        System.out.println("int\t" + checkConversion(int.class, boolean.class) + "\t" +
                checkConversion(int.class, char.class) + "\t" + checkConversion(int.class, int.class) + "\t" +
                checkConversion(int.class, long.class) + "\t" + checkConversion(int.class, double.class) + "\t" +
                checkConversion(int.class, Boolean.class) + "\t" + checkConversion(int.class, Character.class) + "\t" +
                checkConversion(int.class, Integer.class) + "\t" + checkConversion(int.class, Long.class) + "\t" +
                checkConversion(int.class, Double.class));

        System.out.println("long\t" + checkConversion(long.class, boolean.class) + "\t" +
                checkConversion(long.class, char.class) + "\t" + checkConversion(long.class, int.class) + "\t" +
                checkConversion(long.class, long.class) + "\t" + checkConversion(long.class, double.class) + "\t" +
                checkConversion(long.class, Boolean.class) + "\t" + checkConversion(long.class, Character.class) + "\t" +
                checkConversion(long.class, Integer.class) + "\t" + checkConversion(long.class, Long.class) + "\t" +
                checkConversion(long.class, Double.class));

        System.out.println("double\t" + checkConversion(double.class, boolean.class) + "\t" +
                checkConversion(double.class, char.class) + "\t" + checkConversion(double.class, int.class) + "\t" +
                checkConversion(double.class, long.class) + "\t" + checkConversion(double.class, double.class) + "\t" +
                checkConversion(double.class, Boolean.class) + "\t" + checkConversion(double.class, Character.class) + "\t" +
                checkConversion(double.class, Integer.class) + "\t" + checkConversion(double.class, Long.class) + "\t" +
                checkConversion(double.class, Double.class));

        System.out.println("Boolean\t" + checkConversion(Boolean.class, boolean.class) + "\t" +
                checkConversion(Boolean.class, char.class) + "\t" + checkConversion(Boolean.class, int.class) + "\t" +
                checkConversion(Boolean.class, long.class) + "\t" + checkConversion(Boolean.class, double.class) + "\t" +
                checkConversion(Boolean.class, Boolean.class) + "\t" + checkConversion(Boolean.class, Character.class) + "\t" +
                checkConversion(Boolean.class, Integer.class) + "\t" + checkConversion(Boolean.class, Long.class) + "\t" +
                checkConversion(Boolean.class, Double.class));

        System.out.println("Character\t" + checkConversion(Character.class, boolean.class) + "\t" +
                checkConversion(Character.class, char.class) + "\t" + checkConversion(Character.class, int.class) + "\t" +
                checkConversion(Character.class, long.class) + "\t" + checkConversion(Character.class, double.class) + "\t" +
                checkConversion(Character.class, Boolean.class) + "\t" + checkConversion(Character.class, Character.class) + "\t" +
                checkConversion(Character.class, Integer.class) + "\t" + checkConversion(Character.class, Long.class) + "\t" +
                checkConversion(Character.class, Double.class));

        System.out.println("Integer\t" + checkConversion(Integer.class, boolean.class) + "\t" +
                checkConversion(Integer.class, char.class) + "\t" + checkConversion(Integer.class, int.class) + "\t" +
                checkConversion(Integer.class, long.class) + "\t" + checkConversion(Integer.class, double.class) + "\t" +
                checkConversion(Integer.class, Boolean.class) + "\t" + checkConversion(Integer.class, Character.class) + "\t" +
                checkConversion(Integer.class, Integer.class) + "\t" + checkConversion(Integer.class, Long.class) + "\t" +
                checkConversion(Integer.class, Double.class));

        System.out.println("Long\t" + checkConversion(Long.class, boolean.class) + "\t" +
                checkConversion(Long.class, char.class) + "\t" + checkConversion(Long.class, int.class) + "\t" +
                checkConversion(Long.class, long.class) + "\t" + checkConversion(Long.class, double.class) + "\t" +
                checkConversion(Long.class, Boolean.class) + "\t" + checkConversion(Long.class, Character.class) + "\t" +
                checkConversion(Long.class, Integer.class) + "\t" + checkConversion(Long.class, Long.class) + "\t" +
                checkConversion(Long.class, Double.class));

        System.out.println("Double\t" + checkConversion(Double.class, boolean.class) + "\t" +
                checkConversion(Double.class, char.class) + "\t" + checkConversion(Double.class, int.class) + "\t" +
                checkConversion(Double.class, long.class) + "\t" + checkConversion(Double.class, double.class) + "\t" +
                checkConversion(Double.class, Boolean.class) + "\t" + checkConversion(Double.class, Character.class) + "\t" +
                checkConversion(Double.class, Integer.class) + "\t" + checkConversion(Double.class, Long.class) + "\t" +
                checkConversion(Double.class, Double.class));

    }

}
