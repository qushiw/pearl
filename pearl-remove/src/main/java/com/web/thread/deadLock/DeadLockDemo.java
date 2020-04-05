package com.web.thread.deadLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jrqushiwen on 2017-10-20.
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread t1 = new Thread(new A(lock1, lock2));
        Thread t2 = new Thread(new A(lock2, lock1));

        t1.start();
        t2.start();

    }





    private static class A implements Runnable{
        private Lock lock1;
        private Lock lock2;


        public A(Lock lock1, Lock lock2){
            this.lock1 = lock1;
            this.lock2 = lock2;
        }



        @Override
        public void run() {


            lock1.lock();

            try {
                Thread.sleep(3000);
            }catch (Exception e) {

            }
            lock2.lock();
            try {
                Thread.sleep(3000);
            }catch (Exception e) {

            }
            lock2.unlock();

            lock1.unlock();
        }
    }


}
