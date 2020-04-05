package com.web.demo.reactive;


import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
*
* @author qushiwen
* @create 2018-01-18
* @version 1.0
**/
public class FromFutureDemo {

    public static void main(String[] args) {

        FromFutureDemo fromFutureDemo = new FromFutureDemo();

        Mono.from(fromFutureDemo.getMode()).flatMap(data -> {
            System.out.println(data);
            return Mono.just(data);
        }).subscribe();

    }


    public Mono<String> getMode() {
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "test";
        });
        return Mono.fromFuture(future).subscribeOn(Schedulers.elastic());
    }

    private static class TaskDemo implements Callable {

        @Override
        public String call() {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {

            }

            return "test";
        }
    }





}
