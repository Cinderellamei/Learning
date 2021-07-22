package shangguigu;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String [] args) {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i = 1;i<=6;i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getReMessage()).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t*******教师最后关门走人");
    }
}
