package com.web.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.ejb.Schedule;
import java.time.Duration;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
*
* @author qushiwen
* @create 2017-12-30
* @version 1.0
**/
public class ClientDemo {

    public static void main(String[] args) throws Exception {
//        ServiceLoader<LoadDemo> serviceLoader = ServiceLoader.load(LoadDemo.class);
//        Iterator<LoadDemo> iterator = serviceLoader.iterator();
//        LoadDemo loadDemo;
//        while (iterator.hasNext()) {
//            loadDemo = iterator.next();
//            loadDemo.a();
//        }
//
//        Flux<Long> flux = Flux.empty();
//        final Flux<Long> source = null;
//        ClientDemo clientDemo = new ClientDemo();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    clientDemo.test7(source);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        Thread.sleep(5000);
//        source.toStream().forEach(System.out :: println);
//
        String a = "/proxy/qsw_test_5/nodes";

        String b = a.substring(0, a.lastIndexOf("/"));
        System.out.println(b);

    }




    public void test() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("asd");

        stringBuilder.append("ert");

        System.out.println(stringBuilder.toString());
    }


    public void test2() {
        Flux.create(sink -> {
            for (int i=0; i<10 ;i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out :: println);
    }

    public void test3() {
        Flux.range(1,10).take(3).subscribe(System.out::println);

    }


    public void test4() {
        Flux.just(5, 10).flatMap(x -> Flux.interval(Duration.ofMillis(100)).take(x)).toStream().forEach(System.out :: println);
    }


    public void test5() {
        Flux.just(1,3).concatWith(Mono.error(new IllegalStateException())).onErrorReturn(0).subscribe(System.out::println, System.err::println);
    }


    public void test6(){
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        }).publishOn(Schedulers.single())
                .map(x -> String.format("[%s]%s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s]%s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out :: println);
    }


    public void test7(Flux<Long> source) throws InterruptedException {
        source = Flux.interval(Duration.ofMillis(1000)).take(10).publish().autoConnect();
        source.subscribe();

    }
}
