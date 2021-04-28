package TestManager;

public class Asserter {

    public static boolean assertEquals(Object obj1, Object obj2) {
        boolean result = obj1.equals(obj2);
        if (result) {
            TestLogWriter.statusTest = true;
        }
        return result;
    }

    public static boolean assertEquals(int a, int b) {
        boolean result = a == b;
        if (result) {
            TestLogWriter.statusTest = true;
        }
        return result;
    }

    public static boolean assertEquals(boolean result, boolean to) {
        boolean resultTo = result == to;
        if (result) {
            TestLogWriter.statusTest = true;
        }
        return resultTo;
    }

    public static boolean assertEquals(String st1, String st2) {
        boolean result = st1.equals(st2);
        if (result) {
            TestLogWriter.statusTest = true;
        }
        return result;
    }
}
