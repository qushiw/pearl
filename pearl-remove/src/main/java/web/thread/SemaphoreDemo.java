package web.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreDemo {

    private static final Semaphore sem = new Semaphore(3);
    private static final AtomicInteger ato = new AtomicInteger(0);

    public static void main(String[] args) {
        for (;;) {
            Thread thread = new Test();
            thread.start();
        }
    }

    private static class Test extends Thread {

        @Override
        public void run() {
            try {
                sem.acquire();
                Thread.sleep(2000);
                System.out.println(ato.addAndGet(1));
                if (ato.get() > 20) {
                    Thread.currentThread().stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }


        }
    }





}
