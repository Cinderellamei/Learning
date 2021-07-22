package shangguigu.JUC.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    volatile int number = 0;

    //创建原子累，其值默认为0
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }

    public void addTo60() {
        this.number = 60;
    }

    //在该方法加synchronized也可解决原子性问题，但太重量级
    public void addPlusPlus() {
        number++;
    }
}
public class VolatileDemo2 {
    public static void main(String [] args) {

        MyData myData = new MyData();

        for(int i = 0;i<20;i++) {
            new Thread(()-> {
                for(int j = 0;j<1000;j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        while(Thread.activeCount()>2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"获取的number值为："+myData.number);
        System.out.println(Thread.currentThread().getName()+"获取的atomicInteger的值为："+myData.atomicInteger);
    }
}
