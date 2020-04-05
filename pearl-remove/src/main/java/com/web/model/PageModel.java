package com.web.model;

import org.apache.catalina.websocket.WsOutbound;

/**
 * Created by jrqushiwen on 2017-10-01.
 */
public class PageModel {

    private String id;

    private WsOutbound wsOutbound;


    private String url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WsOutbound getWsOutbound() {
        return wsOutbound;
    }


    public void setWsOutbound(WsOutbound wsOutbound) {
        this.wsOutbound = wsOutbound;
    }

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


}
