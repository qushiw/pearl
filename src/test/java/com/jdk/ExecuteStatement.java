package com.jdk;/**
*
* @author qushiwen
* @create 2017-11-23
* @version 1.0
**/
public class ExecuteStatement {











    public <T> T execute(ConnCallBack<T> connCallBack) {
        return connCallBack.callBack();
    }



}
