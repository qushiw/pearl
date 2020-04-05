package com.thread.delayQueue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
*
* @author qushiwen
* @create 2018-01-16
* @version 1.0
**/
public class Cache<K, V> {

    private ConcurrentHashMap<K,V> cacheMap = new ConcurrentHashMap<K,V>();

    private DelayQueue<DelayItem<Pair<K, V>>> queue = new DelayQueue<DelayItem<Pair<K, V>>>();

    private Thread deamonThread;


    public Cache() {
        deamonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                deamonCheck();
            }
        });

        deamonThread.setDaemon(true);
        deamonThread.setName("cache deamon thread");
        deamonThread.start();
    }



    private void deamonCheck() {
        for (;;) {
            try {
                DelayItem<Pair<K, V>> delayItem = queue.take();
                if (delayItem != null) {
                    Pair<K,V> pair = delayItem.getItem();
                    cacheMap.remove(pair.key, pair.value);
                }
            } catch (InterruptedException e) {

            }
        }
    }


    public void put(K key, V value, long time, TimeUnit timeUnit) {
        V old = cacheMap.put(key, value);
        if (old != null) {
            queue.remove(old);
        }
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        queue.put(new DelayItem<>(new Pair<K, V>(key, value), nanoTime));
    }


    public V get(K key) {
        return cacheMap.get(key);
    }

}
