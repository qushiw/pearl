package web.designMode.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by qsfs on 2017-10-19.
 */
public class ProxyHandler<T> implements InvocationHandler{
    private T object;


    public ProxyHandler() {

    }

    public ProxyHandler(T o){
        this.object = o;
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String result;
        System.out.println("i am the before method");
        result = (String) method.invoke(object, args);
        System.out.println("i am the after method");
        return result;
    }


    public T getProxy() {
        return (T)Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

}
