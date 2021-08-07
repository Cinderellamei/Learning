package Algorithm.leecode.bytedance.singleton;

/**
 * 单线程下的单例模式
 * 缺点：多线程并发环境下无法保证多个线程获取的是同一个实例
 */
public class Singleton1 {
    private static Singleton1 instance = null;

    //构造方法
    private Singleton1(){}

    public static Singleton1 getInstance() {
        if(instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
