package com.web.demo;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
*
* @author qushiwen
* @create 2017-12-30
* @version 1.0
**/
public class ClientDemo {

    public static void main(String[] args) {
        ServiceLoader<LoadDemo> serviceLoader = ServiceLoader.load(LoadDemo.class);
        Iterator<LoadDemo> iterator = serviceLoader.iterator();
        LoadDemo loadDemo;
        while (iterator.hasNext()) {
            loadDemo = iterator.next();
            loadDemo.a();
        }

    }


}
