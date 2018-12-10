package com.web.demo;

import com.google.common.base.Stopwatch;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: qushiwen
 * @Date: 18-11-28
 * @version: 1.0
 */
public class DelayQueueDemo {


    public static void main(String[] args) {

/*        Stopwatch stopwatch = Stopwatch.createStarted();
        DelayQueue<DelayTask> queue = new DelayQueue();
        queue.offer(new DelayTask(6000, "i am task"));

        try {
            System.out.println(queue.take().getMsg());
            System.out.println("#################" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        String str = "qweqeqeqeqweqweqeqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqweqeeeeeeeeeeeeeeeeeeeeeeeeeeewwwwwqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
        byte[] data = str.getBytes();
        System.out.println(data.length);
    }



    static class DelayTask implements Delayed {

        private long delay;
        private String msg;
        private long expire;
        private long now;

        public DelayTask (long delay, String msg) {
            this.delay = delay;
            this.msg = msg;
            expire = System.currentTimeMillis() + delay; //到期时间 = 当前时间+延迟时间
            now = System.currentTimeMillis();
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert((expire - System.currentTimeMillis()), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
        }

        public String getMsg() {
            return msg;
        }
    }

}
