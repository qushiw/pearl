package web.demo;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.LongConsumer;

/**
*
* @author qushiwen
* @create 2018-01-11
* @version 1.0
**/
public class ReactorDemo {

    public static void main(String[] args) {


//        final Random random = new Random();
//        Flux.generate(ArrayList::new, (list, sink) -> {
//            int value = random.nextInt(100);
//            list.add(value);
//            sink.next(value);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (list.size() == 20) {
//                sink.complete();
//            }
//            return list;
//        }).subscribe(System.out::println);



        ReactorDemo reactorDemo = new ReactorDemo();
        reactorDemo.test1();

    }



    private void test() {
        Mono<Integer> mono = Mono.from(monoMock()).flatMap((Integer num) -> {
            System.out.println(num);
            return Mono.just(num + 1);
        }).flatMap((Integer num) -> {
            System.out.println(num);
            return Mono.just(num + 1);
        });

        mono.block();

        mono.doOnRequest(new LongConsumer() {
            @Override
            public void accept(long value) {
                System.out.println(value + ",");
            }
        }).block();

        System.out.println("ceshi");
    }

    private Mono<Integer> monoMock() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Mono<Integer>> future = executorService.submit(new Test());
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }


    private class Test implements Callable {

        @Override
        public Mono<Integer> call() {
            return Mono.just(1);
        }
    }



    public void test1() {
//
//        List<Integer> data = new LinkedList<>();
//        data.add(1);
//        data.add(2);
//        data.add(3);

        Mono<Integer> mono = Mono.fromRunnable(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Mono.just(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mono.block();
        mono.subscribe(System.out :: println);


//
//        Flux.fromIterable(data).reduce((item, itemList) -> {
////            System.out.println(item);
//            itemList = itemList + itemList;
//            return itemList;
//        }).subscribe(System.out :: println);
    }



    public void test2() {
        List<String> data = Arrays.asList("qwe", "asd", "zxc");




    }

}
