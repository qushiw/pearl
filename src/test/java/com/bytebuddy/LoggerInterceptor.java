package com.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author: qushiwen
 * @Date: 18-12-6
 * @version: 1.0
 */
public interface LoggerInterceptor {

    public static List<String> log(@SuperCall Callable<List<String>> zuper)
            throws Exception {
        System.out.println("Calling database");
        try {
            return zuper.call();
        } finally {
            System.out.println("Returned from database");
        }
    }


}
