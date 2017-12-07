package com.netty.futrue;

import io.netty.channel.DefaultChannelPromise;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;

import java.util.concurrent.ExecutionException;

/**
*
* @author qushiwen
* @create 2017-12-04
* @version 1.0
**/
public class PromiseDemo {

    private static CustomPromise customPromise = new CustomPromise();

    private static int index = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(new Task());
        t1.start();
        customPromise.sync();
        while (customPromise.get() != "0") {
            System.out.println("customPromise.get()" + customPromise.get());
        }

    }


    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(8000);
                    customPromise.setSuccess("success : " + (index++));
                    System.out.println(customPromise.get());
                }
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {

            }

        }
    }

    private static class CustomPromise extends DefaultPromise<String> {

    }
}
