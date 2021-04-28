import TestManager.*;

public class TestedClass2 {

    private int a;
    private int b;

    @Before
    public void beforeMethod(){
        a = 2;
        b = 2;
        System.out.println("--------- Before Method 2 ---------");
    }

    @Test
    public void method1() {
        System.out.println("--------- Test Method 2.1 ---------");
        Asserter.assertEquals(a, b);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void method2() {
        System.out.println("--------- Test Method 2.2 ---------");
        int[] i = new int[0];
        i[1] = 2;
    }

    @After
    public void afterMethod(){
        System.out.println("--------- After 2 ---------");
    }
}
