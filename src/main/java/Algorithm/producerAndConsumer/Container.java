package Algorithm.producerAndConsumer;

import java.util.LinkedList;

public class Container {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 10;

    public void put(int value) {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                while(list.size() == capacity) {
                    System.out.println("container is full,waiting...");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("producer:"+Thread.currentThread().getName()+"put:"+value);
                list.add(value++);
                notifyAll();
            }
        }
    }

    public Integer take() {
        Integer value = 0;
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                if(list.size() == 0) {
                    System.out.println("container is empty,waiting...");
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                value = list.removeFirst();
                System.out.println("consumer:"+Thread.currentThread().getName()+"take:"+value);
                notifyAll();
            }
        }
    }
}
