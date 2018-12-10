package com.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @Author: qushiwen
 * @Date: 18-12-6
 * @version: 1.0
 */
public class BuddyClient {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        MemoryDatabase loggingDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                .method(named("load"))
                .intercept(MethodDelegation.to(LoggerInterceptor.class))
                .make()
                .load(BuddyClient.class.getClassLoader())
                .getLoaded()
                .newInstance();

        List<String> strings = loggingDatabase.load("test");

        System.out.println(strings);
    }


}
