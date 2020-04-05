package com.web.designMode.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * Created by qsfs on 2017-10-19.
 */
public class MainClient {

    public static void main(String[] args) {

        ProxyInterface proxyInterface = (ProxyInterface) Proxy.newProxyInstance(ProxyImpl.class.getClassLoader(),
                ProxyHandler.class.getInterfaces(), new ProxyHandler(new ProxyImpl()));
        System.out.println(proxyInterface.doSometing());

        System.out.println(" ------------------------------------------------    ");

        ProxyHandler<ProxyInterface> proxyHandler = new ProxyHandler<ProxyInterface>(new ProxyImpl());
        ProxyInterface proxyInt = proxyHandler.getProxy();
        proxyInt.doSometing();
    }

}
