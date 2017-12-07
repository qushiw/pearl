package com.thread;

import com.web.util.ThreadUtil;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-30
 */
public class MainClient {

    public static void main(String[] args) {
        Object obj = new Object();
        a(obj);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        b(obj);
    }



    public static void a(final Object obj){

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait is be wait");
                ThreadUtil.doWait(obj);

                System.out.println("wait is be notify");
            }
        }).start();
    }

    public static void b(Object obj){

        ThreadUtil.doNotify(obj);

    }




}
