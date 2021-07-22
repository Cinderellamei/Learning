package shangguigu.OOM;

import java.util.Random;

public class GoneDemo {
    public static void main(String [] args) {
        System.out.println("===========GCDemo Hello");
        try {
            String str = "atguigu";
            while(true) {
                str += str+ new Random().nextInt(7777777)+new Random().nextInt(88888888);
                str.intern();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
