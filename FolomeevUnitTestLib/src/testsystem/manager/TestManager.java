package testsystem.manager;

import java.util.ArrayDeque;

public class TestManager {

    private String[] args;
    private int count;

    public TestManager(String[] args) {
        this.args = args;
    }

    public void runTesting() {
        count = 1;

        for (int i = 0; i < Integer.valueOf(args[0]); i++) {
            new Thread(() -> startThreadTesting()).start();
        }
    }

    private void startThreadTesting() {

        while (true) {
            try {
                try {
                    Class clazz;

                    synchronized (args) {
                        clazz = Class.forName(args[count++]);
                    }

                    new TestRunner(clazz).runTesting();

                } catch (ClassNotFoundException e) {
                    System.out.println("=====================================");
                    System.out.println("Класс не найден");
                    e.printStackTrace();
                    System.out.println("=====================================");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                break;
            }
        }
    }
}
