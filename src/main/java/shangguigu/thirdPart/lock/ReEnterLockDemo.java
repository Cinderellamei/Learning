package shangguigu.thirdPart.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁
 *
 * 在一个synchronized修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 */
public class ReEnterLockDemo {

    static Object objectLockA = new Object();

    /**
     * 同步代码块
     */
    public static void m1() {
        new Thread(()-> {
            synchronized(objectLockA) {
                System.out.println(Thread.currentThread().getName()+"外层调用");
                synchronized(objectLockA) {
                    System.out.println(Thread.currentThread().getName()+"中层调用");
                    synchronized (objectLockA) {
                        System.out.println(Thread.currentThread().getName()+"内层调用");
                    }
                }
            }
        },"t1").start();
    }

    /**
     * 同步方法
     */
    public synchronized void mm1() {
        System.out.println("外层调用");
        mm2();
    }

    public synchronized void mm2() {
        System.out.println("中层调用");
        mm3();
    }

    public synchronized void mm3() {
        System.out.println("内层调用");
    }


    /**
     * ReentrantLock
     * @param args
     */
    static Lock lock = new ReentrantLock();
    public static void main(String [] args) {
        new Thread(()-> {
            lock.lock();
            try{
                System.out.println("外层调用");
                lock.lock();
                try{
                    System.out.println("内层调用");
                }catch(Exception e) {

                }finally{
                    lock.unlock();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        },"t1").start();

        new Thread(()-> {
            lock.lock();
            try{
                System.out.println("t2调用开始");
            }catch(Exception e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        },"t2").start();
    }
}
