package Algorithm.producerAndConsumer;

public class Consumer implements Runnable{

    private Container container;

    public Consumer(Container container) {
        this.container = container;
    }
    @Override
    public void run() {
        Integer value =container.take();
    }
}
