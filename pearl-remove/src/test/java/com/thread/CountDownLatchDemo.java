package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jrqushiwen on 2017-11-04.
 */
public class CountDownLatchDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);


    public static void main(String[] args) {
        try {
            completeTime(5, new Task());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private static void completeTime(Integer threadNum, final Runnable runnable) throws InterruptedException {

        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(threadNum);

        for (int i=0; i<threadNum; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        start.await();
                        try {
                            runnable.run();
                        } finally {
                            end.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            t.start();
        }


        long startTime = System.nanoTime();
        start.countDown();
        end.await();
        long endTime = System.nanoTime();
        System.out.println("time cost = " + (endTime = startTime));

    }



    private static class Task implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread Task = " + atomicInteger.addAndGet(1));
        }
    }







}
