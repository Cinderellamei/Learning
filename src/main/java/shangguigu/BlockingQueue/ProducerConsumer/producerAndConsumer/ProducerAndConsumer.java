package shangguigu.BlockingQueue.ProducerConsumer.producerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue是一个已经在内部实现了同步的队列，采用的是wait()和signal()方法
 * 可以在生成对象时指定容量大小，用户阻塞操作的put和get方法
 * put:容量达到最大，自动阻塞
 * get:容量为0，自动阻塞
 */
public class ProducerAndConsumer {
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    class Producer extends Thread {
        @Override
        public void run() {
            producer();
        }

        public void producer() {
            while(true) {
                try {
                    queue.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产者生产一条任务，当前队列长度为："+queue.size());
            }
        }
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consumer();
        }

        public void consumer() {
            while(true) {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者消费一条任务，当前队列长度为："+queue.size());
            }
        }
    }

    public static void main(String [] args) {
        ProducerAndConsumer pd = new ProducerAndConsumer();
        Producer producer = pd.new Producer();
        Consumer consumer = pd.new Consumer();
        producer.start();
        consumer.start();
    }
}
