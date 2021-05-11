package testManagerPackage;

import java.lang.reflect.Method;

public class TestLogWriter {

    private int countPassedTests = 0;
    private int countFailedTests = 0;
    private int countErrorTests = 0;
    private boolean isCompleteTest = true;

    private StringBuffer stringBuffer;

    public TestLogWriter(Class clazz) {
        stringBuffer = new StringBuffer();
        stringBuffer.append("\r\n==========================");
        stringBuffer.append("\r\n" + "Tested Class: " + clazz.getName() + "\r\n");
    }

    public void writeLogNotCaughtExceptions(Method method, Test test) {
        stringBuffer.append("\r\nMethod: " + method.getName()  + " did not catch the expected exception -> " + test.expected());
    }

    public void writeLogCaughtExceptions(Method method, double time, Throwable e) {
        stringBuffer.append("\r\nMethod " + method.getName() + "()" + " caught the expected exception");
        stringBuffer.append("\r\nException: " + e.getCause());
        stringBuffer.append("\r\nTime passed: " + time + " ms");
        stringBuffer.append("\r\nTest: Passed\r\n");
        countPassedTests++;
    }

    public void writeLogCaughtNotExpectedExceptions(Method method, double time, Throwable e) {
        stringBuffer.append("\r\nMethod " + method.getName() + "()" + " caught the not expected exception");
        stringBuffer.append("\r\nTest: " + method + " Error: " + e.getCause());
        stringBuffer.append("\r\nTest: Error\r\n");
        countErrorTests++;
        isCompleteTest = false;
    }

    public void writeLogWithAsserts(Method method, double time, boolean testStatus) {
        stringBuffer.append("\r\nMethod " + method.getName() + "()");
        stringBuffer.append("\r\nTime passed: " + time + " ms");
        if (testStatus) {
            countPassedTests++;
            stringBuffer.append("\r\nTest: Passed\r\n");
        } else {
            countFailedTests++;
            stringBuffer.append("\r\nTest: Failed\r\n");
        }
    }

    public void noticeData() {
        stringBuffer.append("\r\n------------------------------");
        stringBuffer.append("\r\nPassed: " + countPassedTests + "; Failed: " + countFailedTests + "; Errors: " + countErrorTests + ".");
        stringBuffer.append("\r\nThe test is completed: " + (isCompleteTest ? "Yes" : "No"));
        System.out.println(stringBuffer.toString());
    }
}
