package shangguigu.OOM;

import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {
    public static void main(String [] args) {
        System.out.println("配置的maxDirectMemory"+sun.misc.VM.maxDirectMemory()/(double)1024/1024+"MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocateDirect(6*1024*1024);
    }
}
