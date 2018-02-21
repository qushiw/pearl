package com.web.demo;/**
*
* @author qushiwen
* @create 2018-02-03
* @version 1.0
**/
public class InnerClassDemo {

    private final int a = 11;


    public static void main(String[] args) {
        StaticClass staticClass = new StaticClass();
    }

    static class StaticClass {

        public void a(){
            System.out.println("i am static inner class");
        }
    }


    class InnerClass{

        public void a(){
            System.out.println("i am a common class");
        }
    }



}
