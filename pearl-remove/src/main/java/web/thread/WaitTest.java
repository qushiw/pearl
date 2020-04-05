package web.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrqushiwen on 2017-10-14.
 */
public class WaitTest {
    static Object object1 = new Object();

    public static void main(String[] args) {
        List list = new ArrayList();


        Thread t1 = new Thread(new Task1(list));
        Thread t2 = new Thread(new Task2(list));

        t1.start();
        t2.start();
    }


    private static class Task1 implements Runnable {

        List object;

        public Task1(List object) {
            this.object = object;
        }


        @Override
        public void run() {
            synchronized (object1) {
                for (int i=0; i<5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("thread1 i = " + i);
                    } catch (Exception e) {

                    }
                }
                object1.notifyAll();
            }

        }
    }



    private static class Task2 implements Runnable {
        List object;
        public Task2(List o){
            this.object = o;
        }
        @Override
        public void run() {
            synchronized (object1) {
                try {
                    object1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i=0; i<5; i++) {
                    System.out.println("thread2 i = " + i);
                }
            }
        }
    }




}
