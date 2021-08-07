package Algorithm.leecode.bytedance;

/**
 * 双重检验锁方式实现单例模式（线程安全）
 */
public class Singleton {
    private volatile static Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        //判断对象是否被实例过，没被实例过才进入此代码
        if(instance == null) {
            synchronized(Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
