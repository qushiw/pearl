package com.web.demo.pool;/**
*
* @author qushiwen
* @create 2018-01-20
* @version 1.0
**/
public class TaskDemo implements Runnable {


    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
