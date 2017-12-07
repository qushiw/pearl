package com.web.util;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-30
 */
public class ThreadUtil {


    public static final void doWait(Object obj) {
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static final void doWait(Object obj, long timeout) {
        synchronized (obj) {
            try {
                obj.wait(timeout);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static final void doNotify(Object obj) {
        synchronized (obj) {
            obj.notify();
        }
    }

    public static final void doNotifyAll(Object obj) {
        synchronized (obj) {
            obj.notifyAll();
        }
    }
}
