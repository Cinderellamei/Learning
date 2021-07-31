package thread;

public class MyThread2 implements Runnable{
    /**
     * 实现Runnable接口，实现run（）方法，启动线程必须要使用Thread的start方法
     */
    public static int sum = 0;

    @Override
    public void run() {
        while(true) {
            System.out.println("线程名称"+Thread.currentThread().getName()+",当前sum值:"+sum++);
            try{
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String [] args) {
        MyThread2 threadA = new MyThread2();
        Thread thread1 = new Thread(threadA);
        thread1.setName("线程1");
        thread1.start();

        MyThread2 threadB = new MyThread2();
        Thread thread2 = new Thread(threadB);
        thread2.setName("线程2");
        thread2.start();

        MyThread2 threadC = new MyThread2();
        Thread thread3 = new Thread(threadC);
        thread3.setName("线程3");
        thread3.start();
    }
}
