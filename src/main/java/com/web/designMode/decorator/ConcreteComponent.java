package com.web.designMode.decorator;/**
*
* @author qushiwen
* @create 2018-02-21
* @version 1.0
**/
public class ConcreteComponent implements Component {

    @Override
    public void operator() {
        System.out.println("i am concrete Component");
    }
}
