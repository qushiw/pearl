package com.web.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jrqushiwen on 2017-11-02.
 */
public class CacheUtil {


    public interface Computable<A, V> {
        public V dec(A data);
    }

    private static class Decorate implements Computable<String, String>{
        public String dec(String data) {
            return "dec" + data;
        }
    }


    private static class Compute<A,V>{

        private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();

        private Computable<A, V> decorate;

        public Compute(Computable<A, V> decorate) {
            this.decorate = decorate;
        }


        public V compute(final A a){
            while (true) {
                Future<V> future = cache.get(a);
                if (future == null) {
                    Callable<V> callable = new Callable<V>() {
                        @Override
                        public V call() throws Exception {
                            return decorate.dec(a);
                        }
                    };

                    FutureTask futureTask = new FutureTask(callable);

                    future = cache.putIfAbsent(a, futureTask);// 原子的
                    if (future == null) {
                        future = futureTask;
                        futureTask.run();
                    }
                }

                try {
                    return future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    public static void main(String[] args) {
//        ConcurrentMap concurrentMap = new ConcurrentHashMap();
//        System.out.println(concurrentMap.putIfAbsent("a", "a"));

        Computable computable = new Decorate();

        Compute compute = new Compute(computable);
        for (int i = 0; i<10; i++) {
            System.out.println(compute.compute(i + ""));
        }


        try {
            Class.forName("");
            Connection connection = DriverManager.getConnection("");
            PreparedStatement preparedStatement = connection.prepareStatement("");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
