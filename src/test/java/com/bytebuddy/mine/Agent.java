package com.bytebuddy.mine;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.reflect.Method;

/**
 * @Author: qushiwen
 * @Date: 18-12-6
 * @version: 1.0
 */


public class Agent {
    @RuntimeType
    public void a(@Origin Method method, @AllArguments Object[] args) {
        System.out.println("i am a agent method");

    }
}
