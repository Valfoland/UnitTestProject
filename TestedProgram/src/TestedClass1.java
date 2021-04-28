import TestManager.*;

public class TestedClass1 {

    private int a;
    private int b;

    @Before
    public void beforeMethod(){
        a = 2;
        b = 2;
        System.out.println("--------- Before 1 ---------");
    }

    @Test
    public void method1() {
        System.out.println("--------- Test Method 1.1 ---------");
        Asserter.assertEquals(a, b);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void method2() {
        System.out.println("--------- Test Method 1.2 ---------");
        int[] i = new int[0];
        i[1] = 2;
    }

    @After
    public void afterMethod(){
        System.out.println("--------- After 1 ---------");
    }
}
