package com.web.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
*
* @author qushiwen
* @create 2018-01-06
* @version 1.0
**/
public class WeightSelectorAlgorigthm<T> {

    /**
     * 所有权重值的和
     */
    private Integer allWeight = 0;
    /**
     * 权重成员
     */
    private T[] keyArray;
    /**
     * 权重成员的值
     */
    private Integer[] weightArray;
    /**
     * 读写锁
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 随机数产生器
     */
    private java.util.Random idxRandom = new java.util.Random();

    public static void main(String[] args) {
        Map<String,Integer> data = new HashMap<String, Integer>();
        data.put("a",2);
        data.put("b",2);
        data.put("c",3);
        data.put("d",4);
        data.put("e",5);
        data.put("f",6);
        data.put("g",5);
        WeightSelectorAlgorigthm<String> compute = new WeightSelectorAlgorigthm<String>();
        compute.init(data);

//        for (int i=0; i<10; i++) {
//            String selectData = compute.probabilitySelect();
//            System.out.println(selectData);
//        }

        System.out.println(10 >>> 1);

    }




    @SuppressWarnings("unchecked")
    public void init(Map<T, Integer> origData) {
        // 获取写锁
        this.readWriteLock.writeLock().lock();
        try {
            this.keyArray = (T[]) new Object[origData.size()];
            this.weightArray = new Integer[origData.size()];
            int i = 0;
            this.allWeight = 0;
            for (Map.Entry<T, Integer> entry : origData.entrySet()) {
                this.keyArray[i] = entry.getKey();
                this.allWeight += entry.getValue();
                this.weightArray[i] = allWeight;
                i++;
            }
        } finally {
            // 释放锁
            this.readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 对外接口，概率选择
     *
     * @return
     */
    public T probabilitySelect() {
        // 获取读锁
        this.readWriteLock.readLock().lock();
        try {
            if( keyArray.length==0 ){
                return null;
            }
            int randVal = idxRandom.nextInt(allWeight) + 1;
            int selectIdx = getRandIdx(0, weightArray.length, randVal);
            return keyArray[selectIdx];
        } finally {
            //释放读锁
            this.readWriteLock.readLock().unlock();
        }
    }

    /**
     * 递归算法
     *
     * @param begin
     * @param end
     * @param randValue
     * @return
     */
    private int getRandIdx(int begin, int end, int randValue) {
        if (begin >= end) {
            return begin;
        }

        int mid = (begin + end) >>> 1;
        if (this.weightArray[mid] >= randValue) {
            return getRandIdx(begin, mid, randValue);
        } else {
            return getRandIdx(mid + 1, end, randValue);
        }
    }


}
