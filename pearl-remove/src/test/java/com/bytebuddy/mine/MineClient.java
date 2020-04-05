package com.bytebuddy.mine;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.MethodDelegation;

/**
 * @Author: qushiwen
 * @Date: 18-12-6
 * @version: 1.0
 */
public class MineClient {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> proxyClass = new ByteBuddy()
            .subclass(Object.class, ConstructorStrategy.Default.DEFAULT_CONSTRUCTOR)
            .name(ExecuteObject.class.getName()+"$Proxy")
            .implement(ExecuteInterface.class)
            .intercept(MethodDelegation.to(Agent.class))
            .make()
            .load(Agent.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        ExecuteInterface executeInterface = (ExecuteInterface)proxyClass.newInstance();
        executeInterface.a();
    }
}
