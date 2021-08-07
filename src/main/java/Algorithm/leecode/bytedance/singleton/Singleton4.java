package Algorithm.leecode.bytedance.singleton;

/**
 * 在DCL基础上，加上volatile关键字禁止指令重排
 */
public class Singleton4 {
    private static volatile Singleton4 instance = null;

    private Singleton4(){}

    public static Singleton4 getInstance() {
        if(instance == null) {
            synchronized (Singleton4.class) {
                if(instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
