package com.arithmetic.recursion;
/**
*
* @author qushiwen
* @create 2017-12-06
* @version 1.0
**/
public class Demo1 {

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.compute(1,1,1);
    }



    public void compute(long a, long b, int index){
        if (index == 30) {
            return;
        }
        long next = a + b;
        System.out.println("next: " + next);
        compute(b, next, ++index);
    }



}
