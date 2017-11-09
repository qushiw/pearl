package com.thread;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Semaphore;

/**
 * Created by jrqushiwen on 2017-11-04.
 */
public class SemaphoreDemo<D> {

    private final Semaphore semaphore;
    private final Set<D> data;

    public SemaphoreDemo(Integer num){
        semaphore = new Semaphore(num);
        data = new CopyOnWriteArraySet<D>();
    }


    public boolean add(D d) throws InterruptedException {
        boolean wasAdd = false;
        semaphore.acquire();
        try {

            wasAdd = data.add(d);
            return wasAdd;
        }  finally {
            if (!wasAdd) {
                semaphore.release();
            }
        }
    }


    public static void main(String[] args) {
        SemaphoreDemo<Integer> semaphoreDemo = new SemaphoreDemo<Integer>(5);
        try {
            for (int i=0; i<10; i++) {
                System.out.println(semaphoreDemo.add(i));
            }
        } catch (Exception e) {
        }

    }



}
