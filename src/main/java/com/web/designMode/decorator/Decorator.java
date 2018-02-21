package com.web.designMode.decorator;/**
*
* @author qushiwen
* @create 2018-02-21
* @version 1.0
**/
public abstract class Decorator implements Component{

    private Component component;

    Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operator() {
        component.operator();
    }
}
