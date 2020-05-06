package com.ssm.Server.spring.aop;

/**
 * @author jrqushiwen
 * @date 2020/5/5 16:09
 */
public class TargetObject implements TargetObjectInterface{

    @Override
    public void method() {
        System.out.println("target method");
    }


}
