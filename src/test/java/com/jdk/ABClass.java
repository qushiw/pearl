package com.jdk;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-12-09
 */
public class ABClass {

    private BAclass bAclass;

    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ABClass(BAclass bAclass) {
        this.bAclass = bAclass;
    }


    public void cannel(){
        bAclass.cancel(this);
    }

}
