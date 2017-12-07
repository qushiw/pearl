package com.thread;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrqushiwen on 2017-10-13.
 */
public class CommonApache {


    public static void main(String[] args) {


        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

//        CollectionUtils.filter(list, new Predicate(){
//            @Override
//            public boolean evaluate(Object o) {
//                if (o instanceof Integer) {
//                    if (o == 2) {
//                        return false;
//                    }
//                }
//                return true;
//            }
//        });

        System.out.println(list);
    }


}
