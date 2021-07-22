package shangguigu.JUC.Volatile;

public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"我是构造方法");
    }

    public static SingletonDemo getInstance() {
        /*if(instance == null) {
            instance = new SingletonDemo();
        }
        return instance;*/

        /**
         * DCL Double Check Lock 双端检锁机制
         */
        if (instance == null) {
            //同步代码块的时候，进行检测
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String [] args) {
        /*System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());*/

        for(int i=0;i<10;i++) {
            new Thread(()-> {
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
