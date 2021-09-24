package Algorithm.leecode.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 按序打印
 */
public class Foo {
    private AtomicInteger jobDone=new AtomicInteger(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        jobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(jobDone.get()!=1) {

        }
        printSecond.run();
        jobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while(jobDone.get() != 2) {

        }
        printThird.run();
    }
}
