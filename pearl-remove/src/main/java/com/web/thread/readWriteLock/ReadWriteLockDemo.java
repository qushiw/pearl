package com.web.thread.readWriteLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jrqushiwen on 2017-10-21.
 */
public class ReadWriteLockDemo {


    public static void main(String[] args) {

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        Container container = new Container(readLock, writeLock);

//        Thread wt1 = new Thread(new TestWrite(container), "wt1");
//        Thread wt2 = new Thread(new TestWrite(container), "wt2");
//        wt1.start();
//        wt2.start();


//        Thread wt3 = new Thread(new TestWrite(container), "wt3");
//        Thread r1 = new Thread(new TestRead(container, "r1"));
//        wt3.start();
//        r1.start();


        Thread wt4 = new Thread(new TestWrite(container), "wt4");
        wt4.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread r2 = new Thread(new TestRead(container, "r2"));
        Thread r3 = new Thread(new TestRead(container, "r3"));
        r2.start();
        r3.start();



    }




    private static class TestRead implements Runnable{
        Container container;
        String threadName;
        public TestRead(Container container, String threadName){
            this.container = container;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            try {
                container.get(threadName);
            } catch (Exception e) {

            }


        }
    }

    private static class TestWrite implements Runnable{
        Container container;
        public TestWrite(Container container){
            this.container = container;
        }

        @Override
        public void run() {
            container.put(Thread.currentThread().getName());
        }
    }


    private static class Container{

        private Lock readLock;
        private Lock writeLock;

        private List<Integer> data = new ArrayList<Integer>();

        public Container(Lock readLock, Lock writeLock){
            this.readLock = readLock;
            this.writeLock = writeLock;
        }


        public void put(String threadName) {
            try {
                writeLock.lock();
                for (int i=0; i<5; i++) {
                    System.out.println("method[put],threadName = " +threadName + ",num = " +i);
                    data.add(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } finally {
                writeLock.unlock();
            }
        }

        public void get(String threadName){
            try {
                readLock.lock();
                for (Integer integer : data) {
                    System.out.println("method[get],ThreadName = " + threadName + " ,value = " + integer);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }


    }



}
