package thread;

public class MyThread extends Thread{
    /**
     * 方式一：继承Thread类，重写run()方法
     */
    public static int sum = 0;
    @Override
    public void run() {
        System.out.println("线程名称"+Thread.currentThread().getName()+",当前sum的值"+(sum++));
        try{
            sleep(5000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args) {
        MyThread thread1 = new MyThread("线程1");
        MyThread thread2 = new MyThread("线程2");
        MyThread thread3 = new MyThread("线程3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    MyThread(String name) {
        Thread.currentThread().setName(name);
    }
}
