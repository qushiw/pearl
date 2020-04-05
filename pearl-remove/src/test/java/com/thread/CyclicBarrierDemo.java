package com.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jrqushiwen on 2017-11-04.
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {

        final AtomicInteger atomicInteger = new AtomicInteger(0);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        while (atomicInteger.get() < 5) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("cyclicbarrier write in");
                        cyclicBarrier.await();
                        System.out.println("cyclicbarrier down" + atomicInteger.addAndGet(1));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });

            t1.start();
        }

    }



}
