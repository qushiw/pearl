package com.thread;

/**
 * Created by jrqushiwen on 2017-10-22.
 */
public class OrderThread {

    private static Object object = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Test(0, "A"));
        Thread t2 = new Thread(new Test(1, "B"));
        Thread t3 = new Thread(new Test(2, "C"));


        t1.start();
        t2.start();
        t3.start();
    }


    private static Integer num = 0;

    private static class Test implements Runnable {

        private Integer index;
        private String message;

        public Test(Integer index, String message){
            this.index = index;
            this.message = message;
        }

        @Override
        public void run() {
            synchronized (object) {
                while (true) {
                    if (num % 3 == index) {
                        System.out.println("message:" + message);
                        num ++;
                        object.notifyAll();
                    } else {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }


    }



}
