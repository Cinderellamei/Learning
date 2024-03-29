package Algorithm.producerAndConsumer.blockQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {
    private ArrayBlockingQueue<Integer> queue;

    public Producer(ArrayBlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        Random random = new Random();
        while(true) {
            try {
                Thread.sleep(100);
                if(queue.size() == 10) {
                    System.out.println("the queue is full,the producer is waiting...");
                    int item = random.nextInt(100);
                    queue.put(item);
                    System.out.printf("producer"+Thread.currentThread().getName()+"produce:"+item+",the size of queue:"+queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
