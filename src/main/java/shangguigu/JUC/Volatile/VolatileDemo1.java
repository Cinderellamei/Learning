package shangguigu.JUC.Volatile;

import java.util.concurrent.TimeUnit;

/**
 * 资源类
 */
class MyData1 {
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }
}

/**
 * 验证volitile的可见性
 */
public class VolatileDemo1 {
    public static void main(String [] args) {
        MyData1 myData = new MyData1();
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName()+" come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+" update number value to "+myData.number);
            System.out.println();
        },"AAA").start();

        while(myData.number == 0) {
            //main线程一直在这里等待，知道number的值不为0
        }
        System.out.println(Thread.currentThread().getName()+" mission is over");
    }

}
