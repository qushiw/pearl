package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jrqushiwen on 2017-11-04.
 */
public class FutureTaskDemo {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final AtomicInteger atomicInteger = new AtomicInteger(0);


        List<Future> futureTaskList = new ArrayList<Future>();
        ExecutorService executorService = new ThreadPoolExecutor(4, 4, 3000l, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


        for (int i=0; i<10; i++) {
            Thread.sleep(1000);

            Future<String> future = executorService.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(3000);
                    return atomicInteger.addAndGet(1);
                }
            });

            futureTaskList.add(future);
        }


        for (int i=0; i<futureTaskList.size(); i++) {
            System.out.println(futureTaskList.get(i).get());
        }
    }

}
