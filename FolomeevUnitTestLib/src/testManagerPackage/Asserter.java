package testManagerPackage;

import java.util.MissingFormatArgumentException;

public class Asserter {

    public static void assertEquals(Object obj1, Object obj2) {
        boolean result = obj1.equals(obj2);
        if(result) throw new testManagerPackage.AssertTrueException();
    }

    public static void assertEquals(int a, int b) {
        boolean result = a == b;
        if(result) throw new testManagerPackage.AssertTrueException();
    }

    public static void assertEquals(boolean result, boolean to){
        boolean resultTo = result == to;
        if(result) throw new testManagerPackage.AssertTrueException();
    }

    public static void assertEquals(String st1, String st2) {
        boolean result = st1.equals(st2);
        if(result) throw new testManagerPackage.AssertTrueException();
    }
}
