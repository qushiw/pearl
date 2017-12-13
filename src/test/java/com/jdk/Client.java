package com.jdk;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
*
* @author qushiwen
* @create 2017-11-23
* @version 1.0
**/
public class Client {


    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
//        BAclass bAclass = new BAclass();
//        ABClass abClass = bAclass.getAbClass();
//        abClass.setName("ceshi");
//        abClass.cannel();
//        Iterator<ABClass> iterator = bAclass.cancelSet.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getName());
//        }

//        Test test = new Test();
//        test.set(13);
//
//        System.out.println(test.addAndGet(11));

        TreeMap treeMap = new TreeMap(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        treeMap.put(5, "123");
        treeMap.put(2, "123");
        treeMap.put(4, "123");

        Iterator<Map.Entry<Integer, String>> iterator =  treeMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey());
        }


    }





    public static class Test {
        private volatile int a;

        AtomicIntegerFieldUpdater<Test> atomicIntegerFieldUpdater;

        public Test(){
            atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Test.class, "a");

        }

        public void set(int num) {
            atomicIntegerFieldUpdater.set(this, num);
        }


        public int addAndGet(int num){
            return atomicIntegerFieldUpdater.addAndGet(this, num);
        }

        public int getA() {
            return a;
        }
    }





}
