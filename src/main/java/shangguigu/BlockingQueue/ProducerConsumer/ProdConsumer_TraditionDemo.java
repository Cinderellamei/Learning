package shangguigu.BlockingQueue.ProducerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        //等待，不生产
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        //等待，不消费
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 *  传统版生产者消费者模式
 * 一个初始值为零的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String [] args) {

        ShareData shareData = new ShareData();
        new Thread(()-> {
            for(int i = 1;i<=5;i++) {
                shareData.increment();
            }
        },"AAA").start();

        new Thread(()-> {
            for(int i = 1;i<=5;i++) {
                shareData.decrement();
            }
        },"BBB").start();
    }

}

