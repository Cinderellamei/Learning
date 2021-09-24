package Algorithm.leecode.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    private AtomicInteger hasDone = new AtomicInteger(0);

    private void first(Runnable printFirst) {
        printFirst.run();
        hasDone.incrementAndGet();
    }

    private void second(Runnable printSecond) {
        while(hasDone.get() !=1) {

        }
        printSecond.run();
        hasDone.incrementAndGet();
    }

    private void third(Runnable printThird) {
        while(hasDone.get() != 2) {

        }
        printThird.run();
    }
}
