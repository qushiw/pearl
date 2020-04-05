package com.web.demo.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
*
* @author qushiwen
* @create 2018-01-20
* @version 1.0
**/
public class ThreadPoolDemo {

    private int corePoolNum;
    private int maxPoolNum;


    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutorDemo(
            2,
            2,
            3000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadFactoryDemo()
    );



    private  static class ThreadPoolExecutorDemo extends ThreadPoolExecutor {

        public ThreadPoolExecutorDemo(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("开始执行前");
            super.beforeExecute(t, r);
        }


        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }


        @Override
        protected void terminated() {
            super.terminated();
        }
    }


    private static AtomicInteger atomicInteger = new AtomicInteger(0);


    private static class ThreadFactoryDemo implements ThreadFactory {
        private final AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t1 = new Thread(r, "name:" + count.getAndIncrement());
            return t1;
        }
    }
    private static class RejectedExecutionDemo implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolDemo.threadPoolExecutor;
        for (int i=0; i<3; i++) {
            threadPoolExecutor.execute(new TaskDemo());
        }

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
