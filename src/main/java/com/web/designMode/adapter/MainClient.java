package com.web.designMode.adapter;

/**
 * Created by jrqushiwen on 2017-10-10.
 */
public class MainClient {

    public static void main(String[] args) {

        Target target = new ConcreteTarget();
        target.request();

        Target target1 = new AdaperOne();
        target1.request();

        Target target2 = new AdaperTwo(new RiseConcrete());
        target2.request();

    }
}
