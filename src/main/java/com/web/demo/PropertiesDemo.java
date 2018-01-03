package com.web.demo;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;

/**
*
* @author qushiwen
* @create 2017-12-26
* @version 1.0
**/
public class PropertiesDemo extends GenericApplicationContext {

    public static void main(String[] args) {
//        long maxMemory = Runtime.getRuntime().maxMemory();
//        System.out.println(maxMemory);

            System.out.println(new Date().getTime());




    }


    public static void init(){
        ConstructorArgumentValues constructorArgumentValues=new ConstructorArgumentValues();


    }

}
