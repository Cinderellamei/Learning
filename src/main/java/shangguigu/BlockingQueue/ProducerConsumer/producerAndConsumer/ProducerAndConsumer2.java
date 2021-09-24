package shangguigu.BlockingQueue.ProducerConsumer.producerAndConsumer;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://cloud.tencent.com/developer/article/1777705
 */

public class ProducerAndConsumer2 {
    private final int MAX_LEN=10;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    class Producer extends Thread{
        @Override
        public void run() {
            producer();
        }
        private void producer() {
            while(true) {
                lock.lock();
                try {
                    while(queue.size() == MAX_LEN){
                        System.out.println("the queue is full");
                        try{
                            condition.await();
                        }catch(InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(1);
                    condition.signal();
                    System.out.println("the producer produce a task,the size of queue is:"+queue.size());
                    try{
                        Thread.sleep(500);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally{
                    lock.unlock();
                }
            }
        }
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consumer();
        }
        private void consumer() {
            while(true) {
                lock.lock();
                try{
                    while(queue.size() == 0) {
                        System.out.println("the queue is empty");
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    condition.signal();
                    System.out.println("a consumer consume a task,the size of queue is:"+queue.size());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally{
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String [] args) {
        ProducerAndConsumer2 pc = new ProducerAndConsumer2();
        Producer producer = pc.new Producer();
        Consumer consumer = pc.new Consumer();
        producer.start();
        consumer.start();
    }
}
