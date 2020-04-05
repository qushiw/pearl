package com.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jrqushiwen on 2017-11-04.
 */
public class InterruptDemo {


    public static void main(String[] args) {
        Thread t1 = new Thread(new Task());
//        t1.start();
//
//        try {
//            Thread.sleep(3000);
//            t1.interrupt();
//            Thread.sleep(3000);
//
//        } catch (Exception e) {
//
//        }


        final Thread t2 = new Thread(new Task2());
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (t2.isInterrupted()){
                        System.out.println("t2 is interrupt");
                        break;
                    }
                }
            }
        });
        try {
            t3.start();
            Thread.sleep(3000);
            t2.interrupt();
        } catch (Exception e) {

        }
    }



    private static class Task2 implements Runnable{

        @Override
        public void run() {
            BlockingQueue blockingQueue = new LinkedBlockingQueue();
            try {
                blockingQueue.take();
            } catch (InterruptedException e) {
                System.out.println("interrupt");
                Thread.currentThread().interrupt();
            }
        }
    }


    private static class Task implements Runnable{

        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("interrupt");
                    throw new RuntimeException("interrupt");
                }
            }
        }
    }
}
