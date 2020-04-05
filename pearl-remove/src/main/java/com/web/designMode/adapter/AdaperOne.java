package com.web.designMode.adapter;

/**
 * 适配器1
 *
 *
 * Created by jrqushiwen on 2017-10-10.
 */
public class AdaperOne extends RiseConcrete implements Target {

    @Override
    public void request() {
        riseRequest();
    }
}
