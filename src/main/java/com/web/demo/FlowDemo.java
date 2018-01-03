package com.web.demo;


/**
*
* @author qushiwen
* @create 2017-12-27
* @version 1.0
**/
public class FlowDemo<T>{
//
//    private Flow.Subscription subscription;
//
//
//    public static void main(String[] args) throws InterruptedException {
//        SubmissionPublisher submissionPublisher = new SubmissionPublisher();
//
//        FlowDemo flowDemo = new FlowDemo();
//        submissionPublisher.subscribe(flowDemo);
//
//        System.out.println("publish item ...");
//        String[] items = {"1", "2", "3"};
//        Arrays.asList(items).stream().forEach(i -> submissionPublisher.submit(1));
//        submissionPublisher.close();
//        Thread.sleep(20000);
//
//    }
//
//    @Override
//    public void onSubscribe(Flow.Subscription subscription) {
//        this.subscription = subscription;
//        subscription.request(1);
//    }
//
//    @Override
//    public void onNext(T item) {
//        System.out.println("GOT:" + item);
//        subscription.request(1);
//    }
//
//    @Override
//    public void onError(Throwable throwable) {
//        throwable.printStackTrace();
//    }
//
//    @Override
//    public void onComplete() {
//        System.out.append("demo");
//    }
}
