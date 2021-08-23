package Algorithm.leecode.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 按序打印
 */
public class Foo {
    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo(){}

    public void First(Runnable printFirst) {
        printFirst.run();
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) {
        while(firstJobDone.get() != 1) {

        }
        printSecond.run();
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) {
        while(secondJobDone.get() !=1) {

        }
        printThird.run();
    }
}
