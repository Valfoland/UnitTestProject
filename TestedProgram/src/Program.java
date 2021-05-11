import testManagerPackage.AssertTrueException;
import testManagerPackage.Asserter;
import testManagerPackage.TestManager;

public class Program {

    public static void main(String[] args) throws Exception {
        new TestManager(args).runTesting();
    }
}

