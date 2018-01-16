package com.thread.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
*
* @author qushiwen
* @create 2018-01-16
* @version 1.0
**/
public class DelayItem<T> implements Delayed{

    private static final long NANO_ORGIN = System.nanoTime();

    final static long now() {
        return System.nanoTime() - NANO_ORGIN;
    }


    private static final AtomicLong sequence = new AtomicLong(0);

    private final long sequenceNumber;

    private final long time;

    private final T item;


    public DelayItem(T submit, long timeOut) {
        this.time = now() + timeOut;
        this.item = submit;
        this.sequenceNumber = sequence.getAndIncrement();
    }


    public T getItem() {
        return item;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - now(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {

        if (other == this) {
            return 0;
        }

        if (other instanceof DelayItem) {
            DelayItem delayItem = (DelayItem)other;
            long diff = time - delayItem.time;
            if (diff < 0) {
                return -1;
            }
            else if (diff > 0) {
                return 1;
            }
            else if (sequenceNumber < delayItem.sequenceNumber) {
                return -1;
            }
            else {
                return 1;
            }

        }
        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
        return (d == 0) ? 0 : (d < 0) ? -1 : 1;
    }
}
