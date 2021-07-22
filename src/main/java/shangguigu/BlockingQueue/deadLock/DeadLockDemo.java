package shangguigu.BlockingQueue.deadLock;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1,String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName()+"持有"+lock1+",尝试获取"+lock2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+"自己持有"+lock2+"，尝试持有"+lock1);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String [] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";

        new Thread(new HoldLockThread(lock1,lock2),"ThreadA").start();
        new Thread(new HoldLockThread(lock2,lock1),"ThreadB").start();
    }
}
