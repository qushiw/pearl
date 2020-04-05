package com.web.demo;

/**
*
* @author qushiwen
* @create 2018-01-20
* @version 1.0
**/
public enum SingletonDemo implements SingletonInterface {
    INSTANCE {
        @Override
        public void doSomeThing() {
            System.out.println("doSomeThing");
        }
    };

    public static SingletonDemo getInstance(){
        return SingletonDemo.INSTANCE;
    }


    public static void main(String[] args) {
        SingletonDemo.getInstance();
    }

}
