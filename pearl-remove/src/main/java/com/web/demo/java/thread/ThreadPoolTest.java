package com.web.demo.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() {
                while (true) {
                    System.out.println("执行中：" +Thread.currentThread().isInterrupted());
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
                return "success";
            }
        });


        Thread.sleep(5000);

        future.cancel(true);

        System.out.println(future.isCancelled());
    }



}
