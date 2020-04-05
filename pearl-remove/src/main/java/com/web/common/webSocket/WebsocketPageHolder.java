package com.web.common.webSocket;

import com.web.model.PageModel;
import org.apache.catalina.websocket.WsOutbound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jrqushiwen on 2017-10-01.
 */
public class WebsocketPageHolder {

    private static WebsocketPageHolder webSocketPageHoler = new WebsocketPageHolder();

    private static Map<String, PageModel> ws = new ConcurrentHashMap<String, PageModel>();

    private static List<WsOutbound> wsOutboundList = Collections.synchronizedList(new ArrayList<WsOutbound>());

    public static WebsocketPageHolder getInstance(){
        return webSocketPageHoler;
    }

    public void register(String id, PageModel pageModel){
        ws.put(id, pageModel);
    }

    public void addWs(WsOutbound wsOutbound){
        wsOutboundList.add(wsOutbound);
    }


    public List<WsOutbound> getWsOutboundList(){
        return wsOutboundList;
    }


    public PageModel get(String id){
        return ws.get(id);
    }

}
