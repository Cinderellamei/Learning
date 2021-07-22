package shangguigu.Thread;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * 第4种使用Java多线程的方式：线程池
 */
public class MyThreadPoolDemo {

    public static void main(String [] args) {
        /*ExecutorService threadPool = newCachedThreadPool();
        try {
            for(int i = 0;i<=10;i++) {
                threadPool.execute(()-> {
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
                TimeUnit.SECONDS.sleep(2);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }*/

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for(int i = 1;i<=100;i++) {
                threadPool.execute(()-> {
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
                //TimeUnit.SECONDS.sleep(2);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
