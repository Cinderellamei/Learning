package Algorithm.leecode.bytedance.singleton;

/**
 *DCL(Double Check Lock)，双端检锁机制，进去和出来的时候进行检测
 * 存在的问题：有指令重排
 */
public class Singleton3 {
    private static Singleton3 instance = null;

    private Singleton3(){}

    public static Singleton3 getInstance() {
        if(instance == null) {
            //同步代码块的时候进行检测
            synchronized (Singleton3.class) {
                if(instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
