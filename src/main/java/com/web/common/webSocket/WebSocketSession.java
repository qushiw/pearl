package com.web.common.webSocket;

import org.apache.catalina.websocket.WsOutbound;

/**
 * Created by jrqushiwen on 2017-10-03.
 */
public class WebSocketSession {



    private WsOutbound wsOutbound;


    public WsOutbound getWsOutbound() {
        return wsOutbound;
    }


    public void setWsOutbound(WsOutbound wsOutbound) {
        this.wsOutbound = wsOutbound;
    }
}
