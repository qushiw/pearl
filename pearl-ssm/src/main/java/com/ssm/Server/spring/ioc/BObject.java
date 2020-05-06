package com.ssm.Server.spring.ioc;

/**
 * @author jrqushiwen
 * @date 2020/5/4 22:33
 */
public class BObject {

    private AObject aObject;

 /*   public BObject(AObject aObject) {
        this.aObject = aObject;
    }*/

    public void setaObject(AObject aObject) {
        this.aObject = aObject;
    }
}
