package com.arithmetic;

import java.util.Arrays;

/**
*
* @author qushiwen
* @create 2017-11-23
* @version 1.0
**/
public class ArrangeCombination {


    public static void main(String[] args) {
        String[] strs = {"a", "b", "c", "d"};
        sort(strs, 0, strs.length);
    }



    public static void sort(String[] strs, int start, int end) {

        if (start == end) {
            System.out.println(Arrays.toString(strs));
        }

        for (int i=start; i<end; i++) {
            exchange(strs, start, i);
            sort(strs, start + 1, end);
            exchange(strs, start, i);
        }


    }


    public static void exchange(String[] strs, int a, int b){
        String temp = strs[a];
        strs[a] = strs[b];
        strs[b] = temp;
    }

}
