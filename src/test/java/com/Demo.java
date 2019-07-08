package com;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Callable;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @Author: qushiwen
 * @Date: 18-12-6
 * @version: 1.0
 */
public class Demo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

/*        try {
            InetAddress inetAddress = InetAddress.getByName("db-btmarketing-0101.pekdc1.jdfin.local");
            System.out.println(inetAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/


/*        Class<?> proxyClass = new ByteBuddy()
                .subclass(Object.class, ConstructorStrategy.Default.DEFAULT_CONSTRUCTOR)
                .name(ExecuteObject.class.getName())
                .implement(ExecuteInterface.class)
                .intercept(MethodDelegation.to(Agent.class))
                .make()
                .load(Agent.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        ExecuteObject executeInterface = (ExecuteObject)proxyClass.newInstance();
        executeInterface.a();*/

/*        MemoryDatabase loggingDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                .method(named("load")).intercept(MethodDelegation.to(LoggerInterceptor.class))
                .make()
                .load(LoggerInterceptor.class.getClassLoader())
                .getLoaded()
                .newInstance();
        List<String> list = loggingDatabase.load("load test");
        System.out.println(list);*/

        int[] data = new int[10];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;

        System.out.println(containsDuplicate(data));
    }

    public static boolean containsDuplicate(int[] nums) {
        if (nums == null) {
            return false;
        }

        if (nums.length == 0) {
            return false;
        }

        for (int i=0; i<nums.length; i++) {
            for (int j=(i+1); j<nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean containsDuplicate2(int[] nums) {
        if (nums == null) {
            return false;
        }

        if (nums.length == 0) {
            return false;
        }
        Set set = new HashSet();
        for (int i=0; i<nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            } else {
                set.add(nums[i]);
            }
        }
        return false;
    }



    public class MemoryDatabase {
        public List<String> load(String info) {
            return Arrays.asList(info + ": foo", info + ": bar");
        }
    }


    class LoggerInterceptor {
        public List<String> log(@SuperCall Callable<List<String>> zuper)
                throws Exception {
            System.out.println("Calling database");
            try {
                return zuper.call();
            } finally {
                System.out.println("Returned from database");
            }
        }
    }


    public interface ExecuteInterface {
        void a();
    }

    private class ExecuteObject implements ExecuteInterface{

        @Override
        public void a() {
            System.out.println("i am execute method");
        }

    }



    private class Agent {
        @RuntimeType
        public void a(@Origin Method method, @AllArguments Object[] args) {
            System.out.println("i am a agent method");

        }
    }


}
