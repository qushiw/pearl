package web.demo.pool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
*
* @author qushiwen
* @create 2018-01-20
* @version 1.0
**/
public class SynchronousQueueDemo {
    private static SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<Integer>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Producter(), "thread-product");
        Thread t2 = new Thread(new Consumer(), "thread-consumer");

        t1.start();
        t2.start();

    }


    private static class Producter implements Runnable {
        private AtomicInteger atomicInteger = new AtomicInteger(1);
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(2000);
                    Integer data = atomicInteger.getAndIncrement();
                    System.out.println("product data:" + data);
                    synchronousQueue.put(data);



                }
            } catch (InterruptedException e) {

            }
        }
    }


    private static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(3000);
                    Integer data = synchronousQueue.take();
                    System.out.println("consumer data:" + data);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
