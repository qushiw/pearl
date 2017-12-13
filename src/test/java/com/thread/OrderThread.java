package com.thread;

/**
 * Created by jrqushiwen on 2017-10-22.
 */
public class OrderThread {
    static Integer index = 0;
    static Object object = new Object();



    public static void main(String[] args) {

        Thread t1 = new Thread(new PrintTask(0, "A"));
        Thread t2 = new Thread(new PrintTask(1, "B"));
        Thread t3 = new Thread(new PrintTask(2, "C"));


        t1.start();
        t2.start();
        t3.start();
    }





    private static class PrintTask implements Runnable {

        private Integer order;
        private String value;

        public PrintTask(Integer order, String value) {
            this.order = order;
            this.value = value;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    if (index % 3 == order) {

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("value" + value);
                        index ++ ;
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
