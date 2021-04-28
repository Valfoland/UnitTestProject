package TestManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private double time;
    private TestLogWriter testLogWriter;
    private Class clazz;

    List<Method> testList = new ArrayList<>();
    List<Method> beforeList = new ArrayList<>();
    List<Method> afterList = new ArrayList<>();

    public TestRunner(Class clazz) {
        this.clazz = clazz;
        testLogWriter = new TestLogWriter(clazz);
    }

    public void runTesting() {
        getBeforeMethods();
        getTestMethods();
        getAfterMethods();

        for (Method method : testList) {

            beforeList.forEach(this::beforeMethods);
            testMethods(method);
            afterList.forEach(this::afterMethods);
        }

        testLogWriter.noticeData();
    }

    private void getBeforeMethods(){
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeList.add(method);
            }
        }
    }

    private void getTestMethods(){
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testList.add(method);
            }
        }
    }

    private void getAfterMethods(){
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(After.class)) {
                afterList.add(method);
            }
        }
    }

    private void beforeMethods(Method method) {
        try {
            method.invoke(clazz.newInstance());
        } catch (Throwable e) {

        }
    }

    private void testMethods(Method method) {
        Test test = method.getAnnotation(Test.class);

        try {
            time = System.nanoTime();
            method.invoke(clazz.newInstance());
            calculateTime();

            if (!test.expected().equals(Null.class)) {
                testLogWriter.writeLogNotCaughtExceptions(method, test);
            }

            testLogWriter.writeLogWithAsserts(method, time);

        } catch (Throwable e) {
            calculateTime();
            testLogWriter.writeLogCaughtExceptions(method, test, time, e);
        }
    }

    private void afterMethods(Method method) {
        try {
            method.invoke(clazz.newInstance());
        } catch (Throwable e) {

        }
    }

    private void calculateTime() {
        time = (System.nanoTime() - time) / 1000000;
    }

}
