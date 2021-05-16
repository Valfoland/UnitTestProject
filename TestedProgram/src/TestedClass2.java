import testsystem.manager.*;

public class TestedClass2 {

    private int a;
    private int b;

    @Before
    public void beforeMethod(){
        a = 2;
        b = 3;
    }

    @Test
    public void method1() {
        Asserter.assertEquals(a, b);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void method2() {
        int[] i = new int[0];
        i[1] = 2;
    }

    @After
    public void afterMethod(){
        System.out.println("--------- After 2 ---------");
    }
}
