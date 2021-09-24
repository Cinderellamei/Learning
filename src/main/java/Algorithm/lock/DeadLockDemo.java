package Algorithm.lock;

public class DeadLockDemo {
    public static void main(String [] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (lockA) {
                    System.out.println(name+"拿到lockA，想要lockB");
                    try{
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB) {
                        System.out.println(name+"get lockB");
                        System.out.println(name+"say hello");
                    }
                }
            }
        }," 线程A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (lockB) {
                    System.out.println(name+"get lockB,want lockA");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockA) {
                        System.out.println(name+"get lockA");
                        System.out.println(name+"say hello");
                    }
                }
            }
        },"线程2").start();
    }
}
