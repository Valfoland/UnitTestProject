import testManagerPackage.*;

public class TestedClass1 {

    private int a;
    private int b;

    private String str1;
    private String str2;

    @Before
    public void beforeMethod(){
        a = 6;
        b = 6;
        str1 = "str1";
        str2 = "str2";
    }

    @Test
    public void method1(){
        Asserter.assertEquals(12, a + b);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void method2() {
        int[] i = new int[0];
        i[1] = 2;
    }

    @Test
    public void method3(){
        String number = "12";
        Asserter.assertEquals(number, a + b);
    }

    @Test(expected = NegativeArraySizeException.class)
    public void method4() {
        int[] i = new int[-1];
    }

    @Test
    public void method5() {
        Asserter.assertEquals(str1, str2);
    }

    @After
    public void afterMethod(){
        System.out.println("--------- After 1 ---------");
    }
}
