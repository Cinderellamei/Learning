package shangguigu.BlockingQueue.ProducerConsumer.producerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerAndConsumer1 {
    private final int MAX_LEN=10;
    private Queue<Integer> queue = new LinkedList<>();

    class Producer extends Thread{
        @Override
        public void run() {
            producer();
        }
        public void producer() {
            while(true) {
                synchronized(queue) {
                    while(queue.size() == MAX_LEN) {
                        queue.notify();
                        System.out.println("当前队列已满");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(1);
                    queue.notify();
                    System.out.println("生产者生产一条任务，当前队列长度为："+ queue.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consumer();
        }

        public void consumer(){
            while(true) {
                synchronized(queue) {
                    while(queue.size() == 0) {
                        queue.notify();
                        System.out.println("当前队列为空");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    queue.notify();
                    System.out.println("消费者消费一条任务，当前任务长度为："+queue.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String [] args) {
        ProducerAndConsumer1 pc = new ProducerAndConsumer1();
        Producer producer = pc.new Producer();
        Consumer consumer = pc.new Consumer();
        producer.start();
        consumer.start();
    }
}
