import testsystem.manager.AssertTrueException;
import testsystem.manager.Asserter;
import testsystem.manager.TestManager;

public class Program {

    public static void main(String[] args) throws Exception {
        new TestManager(args).runTesting();
    }
}

