package TestManager;

import java.lang.reflect.Method;

public class TestLogWriter {

    public static boolean statusTest = false;

    private int countPassedTests = 0;
    private int countFailedTests = 0;
    private int countErrorTests = 0;
    private boolean isCompleteTest = true;

    private StringBuffer stringBuffer;

    public TestLogWriter(Class clazz) {
        stringBuffer = new StringBuffer();
        stringBuffer.append("\r\n" + "Tested Class: " + clazz.getName());
    }

    public void writeLogNotCaughtExceptions(Method method, Test test) {
        stringBuffer.append("\r\nMethod: " + method.getName()  + " did not catch the expected exception -> " + test.expected());
    }

    public void writeLogCaughtExceptions(Method method, Test test, double time, Throwable e) {
        if (test.expected().equals(e.getCause().getClass())) {
            stringBuffer.append("\r\nMethod " + method.getName() + "()" + " caught the expected exception");
            stringBuffer.append("\r\nException: " + e.getCause());
            stringBuffer.append("\r\nTime passed: " + time + " ms");

            if (statusTest) {
                countPassedTests++;
                stringBuffer.append("\r\nTest: Passed\r\n");
                statusTest = false;
            } else {
                countFailedTests++;
                stringBuffer.append("\r\nTest: Failed\r\n");
            }
        } else {
            stringBuffer.append("\r\nTest: " + method + " Error: " + e.getCause());
            countErrorTests++;
            isCompleteTest = false;
        }
    }

    public void writeLogWithAsserts(Method method, double time) {
        stringBuffer.append("\r\nMethod " + method.getName() + "()");
        stringBuffer.append("\r\nTime passed: " + time + " ms");
        if (statusTest) {
            countPassedTests++;
            stringBuffer.append("\r\nTest: Passed\r\n");
            statusTest = false;
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
