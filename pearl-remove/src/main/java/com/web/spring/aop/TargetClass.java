package com.web.spring.aop;

public class TargetClass implements TargetClassInterFace {
    @Override
    public void test() {
        System.out.println("i am target method");
    }
}
