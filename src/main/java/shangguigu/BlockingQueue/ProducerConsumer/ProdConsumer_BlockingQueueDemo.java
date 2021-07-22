package shangguigu.BlockingQueue.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean FLAG = true;

    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while(FLAG) {
           data = atomicInteger.incrementAndGet()+ "";
           retValue = blockingQueue.offer(data,2, TimeUnit.SECONDS);
           if(retValue) {
               System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
           } else {
               System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
           }
           TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\tflag为false，生产动作结束");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while(FLAG) {
            result = blockingQueue.poll(2,TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t超过2秒没有取到蛋糕，消费退出");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");
            }
        }
    }

    public void stop() {
        this.FLAG = false;
    }

}

public class ProdConsumer_BlockingQueueDemo {

    public static void main(String [] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue(10));
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()-> {
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("5秒时间到，线程叫停，活动结束");
        myResource.stop();

    }
}
