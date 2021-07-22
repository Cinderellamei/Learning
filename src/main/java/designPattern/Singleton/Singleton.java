package designPattern.Singleton;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
    private static Singleton instance;

    ReentrantLock  lock = new ReentrantLock();

    private Singleton() {

    }

    public   synchronized Singleton getInstance() {
        /*lock.lock();
        try {
            if(instance == null) {
                instance = new Singleton();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return instance;*/

        if(instance == null) {
            lock.lock();
            try{
                if(instance == null) {
                    instance = new Singleton();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
        return instance;
    }
}
