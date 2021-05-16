package testsystem.manager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private double time;
    private TestLogWriter testLogWriter;
    private Class clazz;
    private Object clazzObject;

    public TestRunner(Class clazz) {
        this.clazz = clazz;
        try {
            clazzObject = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        testLogWriter = new TestLogWriter(clazz);
    }

    public void runTesting() {
        List<Method> testList = getMethods(Test.class);
        List<Method> beforeList = getMethods(Before.class);
        List<Method> afterList = getMethods(After.class);

        for (Method method : testList) {

            beforeList.forEach(this::helperMethods);
            testMethods(method);
            afterList.forEach(this::helperMethods);
        }

        testLogWriter.noticeData();
    }

    private List<Method> getMethods(Class unitClass) {
        List<Method> listMethods = new ArrayList<>();

        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(unitClass)) {
                listMethods.add(method);
            }
        }

        return listMethods;
    }

    private void helperMethods(Method method) {
        try {
            method.invoke(clazzObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void testMethods(Method method) {
        Test test = method.getAnnotation(Test.class);
        boolean testStatus = false;

        try {
            time = System.nanoTime();
            method.invoke(clazzObject);
            calculateTime();

            testLogWriter.writeLogWithAsserts(method, time, false);

        } catch (Throwable e) {
            if (e.getCause().getClass().equals(AssertTrueException.class)) {
                testLogWriter.writeLogWithAsserts(method, time, true);
            } else {
                if (test.expected().equals(e.getCause().getClass())) {
                    testLogWriter.writeLogCaughtExceptions(method, time, e);
                } else {
                    testLogWriter.writeLogCaughtNotExpectedExceptions(method, time, e);
                }
            }
        }
    }

    private void calculateTime() {
        time = (System.nanoTime() - time) / 1000000;
    }
}
