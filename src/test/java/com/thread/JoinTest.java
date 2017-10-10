package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jrqushiwen on 2017-09-18.
 */
public class JoinTest {


    public static void main(String[] args) throws InterruptedException {

//        ExecutorService service = Executors.newCachedThreadPool();
//        service.submit(new Task());

        try {
            Thread t1 = new Thread(new Task());
            t1.start();
            Thread.currentThread().join();
        } finally {

        }


    }



    private static class Task implements Runnable {
        @Override
        public void run() {

            for (int i=0; i<10; i++) {
                System.out.println("i" + i);
            }

        }
    }
}
