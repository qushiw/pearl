package web.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
*
* @author qushiwen
* @create 2017-12-30
* @version 1.0
**/
public class LoadDemoImpl implements LoadDemo {


    public static void main(String[] args) {
        CompletableFuture<A> completableFuture = new CompletableFuture<A>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                completableFuture.complete(new A());

            }
        }).start();

        try {
            System.out.println(completableFuture.get().a());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void a() {
        System.out.println("test a");
    }

    static class A {
        public String a(){
            return "a";
        }
    }
}
