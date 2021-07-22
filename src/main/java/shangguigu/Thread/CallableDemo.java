package shangguigu.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception{
        System.out.println("********come in callable");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String [] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread());
        Thread t1 = new Thread(futureTask,"AAA");
        t1.start();

        int result01 = 100;
        int result02 = (int) futureTask.get();
        System.out.println("****reuslt"+(result01+result02));
    }

}
