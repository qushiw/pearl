package com.web.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
*
* @author qushiwen
* @create 2017-12-30
* @version 1.0
**/
public class ClientDemo {

    public static void main(String[] args) throws Exception {
        
        Integer[] data = new Integer[3];
        data[0] = 1;
        data[1] = 2;

//        test8(data);


        for (int i=0;i<10;i++) {
            for (int j=0;j<10;j++) {
                if (j==5) {
                    break;
                }
                System.out.println("i=" + i + ", j="+ j);
            }
        }

    }


    public static void test8 (Object[] objects) {
        Integer[] data = (Integer[]) objects;
        for (Integer i : data) {
            System.out.println(i);
        }
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
