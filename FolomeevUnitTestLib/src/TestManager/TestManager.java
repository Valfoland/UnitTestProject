package TestManager;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;

public class TestManager {

    private int numberOfThreads;
    private ArrayDeque<Class> classQueue = new ArrayDeque<>();

    public TestManager(String[]args) {
        initData(args);
    }

    private void initData(String[]args) {
        for (int i = 0; i < args.length; i++) {
            try {
                if (i == 0) {
                    numberOfThreads = Integer.valueOf(args[i]);
                } else {
                    classQueue.push(Class.forName(args[i]));
                }
            } catch (Exception e) {

            }
        }
    }

    public void runTesting(){
        for(int i = 0; i < numberOfThreads; i++){
            new Thread(() -> startThreadTesting()).start();
        }
    }

    private void startThreadTesting() {
        while (true) {
            try {
                Class clazz;

                synchronized (classQueue){
                    clazz = classQueue.pop();
                }

                new TestRunner(clazz).runTesting();

            } catch (NoSuchElementException e) {
                break;
            }
        }
    }
}
