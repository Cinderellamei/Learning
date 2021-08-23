package Algorithm.leecode.bytedance.singleton;

/**
 * 饿汉式单例模式
 * 在类被加载时实例化一个对象，线程安全
 * 缺点：不管要不要都会创建一个对象，会消耗一定的性能
 */
public class Singleton5 {
    private static Singleton5 instance = new Singleton5();
    private Singleton5(){}
    public static Singleton5 getInstance(){
        return instance;
    }
}
