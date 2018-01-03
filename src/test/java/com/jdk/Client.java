package com.jdk;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
*
* @author qushiwen
* @create 2017-11-23
* @version 1.0
**/
public class Client {

    public Client(){

    }

    public static void main(String[] args) throws Throwable {


        while (true) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(A.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                @Override
//                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                    return methodProxy.invokeSuper(o, objects);
//                }
//            });
//
//            enhancer.create();
        }


    }



    private static class A{

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }

        public A(){

        }
    }


}
