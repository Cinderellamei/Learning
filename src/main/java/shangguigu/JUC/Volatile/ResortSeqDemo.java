package shangguigu.JUC.Volatile;

public class ResortSeqDemo {
    static int a = 0;
    static boolean flag = false;

    public static void method1() {
        a = 1;
        flag = true;
    }

    public static void method2() {
        if(flag) {
            a = a+5;
            System.out.println("revalue: "+a);
        }
    }

    public static void main(String [] args) {
        method1();
        method2();
    }
}
