package web.thread;

import java.util.concurrent.CompletableFuture;

/**
*
* @author qushiwen
* @create 2018-01-18
* @version 1.0
**/
public class CompletableFutureDemo {

    public static void main(String[] args) {

        System.out.println("test");

        CompletableFuture<Void> completeFuture1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("i am A");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> completeFuture2 = CompletableFuture.runAsync(() -> {
            try {

                Thread.sleep(4000);
                System.out.println("i am B");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        completeFuture1.runAfterEither(completeFuture2, () -> {
           System.out.println("i am C");
        });

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
