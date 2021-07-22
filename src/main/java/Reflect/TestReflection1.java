package Reflect;

public class TestReflection1 {

    public static void main(String [] args) {
        //普通创建对象
        ITest itest1 = createTest("ITestImpl1");
        itest1.testReflect();
        ITest itest2 = createTest("ITestImpl2");
        itest2.testReflect();

        //反射创建对象
        ITest itest3 = createTest2("ITestImpl1");
        itest3.testReflect();
        ITest itest4 = createTest2("ITestImpl2");
        itest4.testReflect();
    }

    public static ITest createTest(String name) {
        if(name.equals("ITestImpl1")) {
            return new ITestImpl1();
        } else if(name.equals("ITestImpl2")) {
            return new ITestImpl2();
        }
        return null;
    }

    public static ITest createTest2(String name) {
        try {
            Class calss1 = Class.forName(name);
            ITest iTest = null;
            try {
                iTest = (ITest)calss1.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return iTest;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    interface ITest {
        public void testReflect();
    }

    static class ITestImpl1 implements ITest {
        @Override
        public void testReflect() {
            System.out.println("I am ITestImpl1");
        }
    }

    static class ITestImpl2 implements ITest {
        @Override
        public void testReflect() {
            System.out.println("I am ITestImpl2");
        }
    }
}
