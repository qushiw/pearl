package web.demo;


import java.util.LinkedList;


/**
*
* @author qushiwen
* @create 2017-12-28
* @version 1.0
**/
public class GsonDemo {

    public static void main(String[] args) {
//
//        Flux<Integer> flux = Flux.create(sink -> {
//            for (int i=0;i<10;i++) {
//                sink.next(i);
//            }
//            sink.complete();
//        });
//
//
//        flux.subscribe(System.out::print);


//        Flux.range(1,10).filter(i->i%2 == 0) .subscribe(System.out::print);
//        Flux.just("a","b").zipWith(Flux.just("c","d")).subscribe(System.out::print);
//        Flux.just("a","b").zipWith(Flux.just("c","d"),(s1,s2)->String.format("%s-%s:",s1,s2)).subscribe(System.out::print);
//
//
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//
//        Flux.fromIterable(list).flatMap((Integer integer) -> {
//
//        }).reduce(true, (ok1, ok2) -> {
//
//        })

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);

        Integer[] data = new Integer[linkedList.size()];

        linkedList.toArray(data);

        for (Integer i : data) {
            System.out.println(i);
        }
    }


}
