package com.jdk;

import java.util.HashSet;
import java.util.Set;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-12-09
 */
public class BAclass {

    Set<ABClass> cancelSet = new HashSet<ABClass>();


    public ABClass getAbClass(){
        return new ABClass(this);
    }

    public void cancel(ABClass abClass){
        cancelSet.add(abClass);
    }


}
