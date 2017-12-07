package com.jdk;

import java.io.IOException;


/**
*
* @author qushiwen
* @create 2017-11-23
* @version 1.0
**/
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        short s = 1;
        Object obj = new Short(s);
        System.out.println( obj.getClass().getName());

    }



}
