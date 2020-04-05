package com.web.thread.deadLock;

/**
 * Created by jrqushiwen on 2017-10-21.
 */
public class DeadLockDemoTwo {


    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();

        Thread t1 = new Thread(new Test(o1, o2));
        Thread t2 = new Thread(new Test(o2, o1));


        t1.start();
        t2.start();
    }


    private static class Test implements Runnable {
        private Object lock;
        private Object lock2;

        public Test(Object lock, Object lock2){
            this.lock = lock;
            this.lock2 = lock2;
        }


        @Override
        public void run() {

            synchronized (lock) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }




}
