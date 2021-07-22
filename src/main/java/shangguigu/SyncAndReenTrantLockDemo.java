package shangguigu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource {

    private int number = 1;//A：1，B：2，C：3

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();

    private Condition condition2 = lock.newCondition();

    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while(number != 1) {
                condition1.wait();
            }

            for(int i = 1;i<=5;i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 2;
            condition2.signal();
        } catch(Exception e) {

        }finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while(number != 2) {
                condition2.wait();
            }

            for(int i = 1;i<=10;i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 3;
            condition3.signal();
        } catch(Exception e) {

        }finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while(number != 3) {
                condition3.wait();
            }

            for(int i = 1;i<=15;i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }

            number = 1;
            condition1.signal();
        } catch(Exception e) {

        }finally {
            lock.unlock();
        }
    }
}

/**
 * 题目：多线程之间按顺序调用，实现 A-> B -> C 三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 */
public class SyncAndReenTrantLockDemo {

    public static void main(String [] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()-> {
            for(int i = 1;i<10;i++) {
                shareResource.print5();
            }
        },"AAA").start();

        new Thread(()-> {
            for(int i = 1;i<10;i++) {
                shareResource.print10();
            }
        },"BBB").start();

        new Thread(()-> {
            for(int i = 1;i<10;i++) {
                shareResource.print15();
            }
        },"CCC").start();
    }
}
