package com.web.restful.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
*
* @author qushiwen
* @create 2017-12-27
* @version 1.0
**/
public class RestAdapter {




    public <T> T create(Class<T> service){
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new RestHandler());
    }



    private class RestHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            System.out.println(method);
            App app = new App();
            app.setName(methodName);
            return app;
        }
    }



}
