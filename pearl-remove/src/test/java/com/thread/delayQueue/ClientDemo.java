package com.thread.delayQueue;

import java.util.concurrent.TimeUnit;

/**
*
* @author qushiwen
* @create 2018-01-16
* @version 1.0
**/
public class ClientDemo {

    public static void main(String[] args) {

        Cache<Integer, Object> cache =  new Cache<Integer, Object>();


        cache.put(1, 123, 5000, TimeUnit.MILLISECONDS);

        System.out.println(cache.get(1));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.get(1));


    }


}
