package com.basics.lock;

import java.util.concurrent.TimeUnit;

/**
 *
 *  以一个对象所谓锁，如果这个对象中的方法本身也有锁的话，那么这两个锁会成为互相排斥锁。
 *
 * @Author: qushiwen
 * @Date: 20-4-30
 * @version: 1.0
 */
public class ObjectLock {
    static SimpleObject lock = new SimpleObject();
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.getMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        /**
         * 以这个对象作为锁，如果对象里面也有锁的话，这两把锁会成为互相排斥锁
         */
        synchronized (lock) {
            System.out.println("main print");
        }
    }


    private static class SimpleObject {

        /**
         * 锁住此方法
         * @throws InterruptedException
         */
        public synchronized void getMessage() throws InterruptedException {
            System.out.println("simple Object print");
            TimeUnit.SECONDS.sleep(10);
        }
    }
}






