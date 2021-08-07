package Algorithm.leecode.bytedance.singleton;

/**
 * 多线程下的单例模式(加synchronized关键字)
 * 缺点：重量级同步机制，同一时刻只允许一个线程访问获取实例的方法
 */
public class Singleton2 {
    private static Singleton2 instance = null;

    private Singleton2(){}

    public synchronized static Singleton2 getInstance() {
        if(instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
