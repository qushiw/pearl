package web.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jrqushiwen on 2017-10-21.
 */
public class InterruptLockDemo {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        Thread t1 = new Thread(new Test(lock), "t1");

        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }

        t1.interrupt();

    }





    private static class Test implements Runnable {

        Lock lock;
        public Test(Lock lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println("son thread running");
                lock.lock();
                System.out.println("threan Name :" + Thread.currentThread().getName() + "in");
            }  finally {
            }
        }
    }



}
