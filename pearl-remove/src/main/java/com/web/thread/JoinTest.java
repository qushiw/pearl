package com.web.thread;

/**
 * Created by jrqushiwen on 2017-10-14.
 */
public class JoinTest {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Task1(), "t1");
        Thread t2 = new Thread(new Task1(), "t2");


        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {

        }
        t2.start();

        System.out.println("i am main thread");
//        try {
//            t2.join();
//        } catch (InterruptedException e) {
//
//        }

    }




    private static class Task1 implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            for (int i=0; i<5; i++) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    e.printStackTrace();
                }

                System.out.println("i" + i);
            }

        }
    }



    private class Task2 implements Runnable {


        @Override
        public void run() {

        }
    }


}
