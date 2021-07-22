package Reflect;

public class TestReflection {

    public static void main(String [] args) {
        ITest iTest = createTest("ITestImpl1");
        ITest iTest2 = createTest("ITestImpl2");
        iTest2.testReflect();
    }

    public static ITest createTest(String name) {
        if(name.equals("ITestImpl1")) {
            return new ITestImpl1();
        } else if(name.equals("ITestImpl2")) {
            return new ITestImpl2();
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
