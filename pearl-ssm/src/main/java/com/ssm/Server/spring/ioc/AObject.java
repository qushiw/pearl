package com.ssm.Server.spring.ioc;

/**
 * @author jrqushiwen
 * @date 2020/5/4 22:33
 */
public class AObject {

    private BObject bObject;

    /*public AObject(BObject bObject) {
        this.bObject = bObject;
    }*/

    public void setbObject(BObject bObject) {
        this.bObject = bObject;
    }



    public void a() {
        System.out.println("a object");
    }
}
