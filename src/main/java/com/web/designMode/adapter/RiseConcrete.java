package com.web.designMode.adapter;

/**
 * Created by jrqushiwen on 2017-10-10.
 */
public class RiseConcrete implements RiseTarget{

    @Override
    public void riseRequest() {
        System.out.println("源使的具体类");
    }
}
