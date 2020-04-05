package web.demo.reactive;

import reactor.core.publisher.Mono;

/**
*
* @author qushiwen
* @create 2018-01-16
* @version 1.0
**/
public class Demo1 {

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.test1();
    }


    private Mono test() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mono.just("ceshi");
    }

    private void test1() {
       Object o = Mono.from(test()).block();
       System.out.println(String.valueOf(o));
    }


}
