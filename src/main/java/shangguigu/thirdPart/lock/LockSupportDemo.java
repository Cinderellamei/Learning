package shangguigu.thirdPart.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {

    static Object objectLock = new Object();

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String [] args) {
        lockSupportParkUnpark();
    }

    public static void synchronizedWaitNotify() {
        new Thread(()-> {
            /*try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            synchronized(objectLock) {
                System.out.println(Thread.currentThread().getName()+" come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 被唤醒");
            }
        },"A").start();


        new Thread(()-> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName()+" 唤醒");
            }
        },"B").start();
    }

    public static void conditionAwaitSignal() {
        new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+" come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 被唤醒");
            }finally{
                lock.unlock();
            }
        },"A").start();


        new Thread(()-> {
            lock.lock();
            try{
                condition.signal();
                System.out.println(Thread.currentThread().getName()+" 发出通知");
            }finally{
                lock.unlock();
            }
        },"B").start();
    }


    public static void lockSupportParkUnpark() {
        Thread a = new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" come in");
            LockSupport.park();//被阻塞，等待通知等待放行，他要通过需要许可证
            System.out.println(Thread.currentThread().getName()+" 被唤醒");
        },"A");
        a.start();


        Thread b = new Thread(()-> {
            LockSupport.unpark(a);//被阻塞，等待通知等待放行，他要通过需要许可证
            System.out.println(Thread.currentThread().getName()+" 通知a");
        },"B");
        b.start();

    }
}
