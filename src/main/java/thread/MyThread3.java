package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyThread3 implements Callable {
    /**
     * 实现Callable接口，重写call方法
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("实现call函数开始业务逻辑");
        Thread.sleep(5000);

        //这里有返回值
        return 1;
    }

    public static void main(String [] args) {
        //创建一个FutureTask，指定Callable对象，作为线程任务
        FutureTask<Integer> task = new FutureTask<>(new MyThread3());

        //启动线程
        new Thread(task).start();

        //这里在线程启动后，结果返回前，可以干别的
        System.out.println("线程启动之后，可自定义业务逻辑，因为上面线程call函数睡眠了5秒");

        //获取线程执行结果
        Integer result = null;
        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("主线程中异步任务执行的结果为"+result);
    }

}
