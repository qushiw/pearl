package com.jdk;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Vector;

/**
 * Created by jrqushiwen on 2017-10-28.
 */

@ThreadSafe
public class MList<E> extends Vector<E>{



    public synchronized boolean putIfAbsent(E e){
        boolean contain = !contains(e);
        if (contain) {
            add(e);
        }
        return contain;
    }

}
