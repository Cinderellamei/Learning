package shangguigu.Volatile;


import java.util.concurrent.atomic.AtomicInteger;

class MyData1 {
    public int number = 0;
    public synchronized void addPlusPlus() {
        number++;
    }


}
public class VolatileDemo1 {
    public static void main(String [] args) {
        MyData1 myData1 = new MyData1();
        for(int i = 0;i<20;i++) {
            new Thread(()-> {
                for(int j=0;j<1000;j++) {
                    myData1.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName()+myData1.number);
    }
}
