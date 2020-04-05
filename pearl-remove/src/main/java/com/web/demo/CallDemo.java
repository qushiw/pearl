package com.web.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
*
* @author qushiwen
* @create 2018-01-01
* @version 1.0
**/
public class CallDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);
    private static Random random = new Random();




    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Future> list = new ArrayList<Future>();
        Future<String> future = null;
        for (int i=0;i<10;i++) {
            future = executorService.submit(new TaskDemo());
            list.add(future);
        }

        for (Future future1 : list) {
            System.out.println("future.get" + future1.get());
        }

    }

    private static class TaskDemo implements Callable {
        @Override
        public String call() {
            try {
                Thread.sleep(10000 * atomicInteger.addAndGet(1));
            } catch (Exception e) {

            }
            System.out.println("task " + Thread.currentThread().getName() + "over");
            return Thread.currentThread().getName();
        }
    }


}
