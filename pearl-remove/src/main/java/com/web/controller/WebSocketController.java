package com.web.controller;

import com.web.common.webSocket.WebSocketSession;
import com.web.common.webSocket.WebsocketPageHolder;
import com.web.model.PageModel;
import org.apache.catalina.websocket.WsOutbound;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Created by jrqushiwen on 2017-10-01.
 */

@Controller
@RequestMapping("/springMVC/webSocket")
public class WebSocketController {

    private WebsocketPageHolder websocketPageHolder = WebsocketPageHolder.getInstance();


    @RequestMapping("/sendMesage.do")
    public void sendMessage(String id){
        PageModel pageModel = websocketPageHolder.get(id);
        /*try {
            WebSocketSession webSocketSession = pageModel.getWebSocketSession();
            WsOutbound wsOutbound = webSocketSession.getWsOutbound();
            WsOutbound wsOutbound1 = pageModel.getWsOutbound();
            wsOutbound1.writeTextMessage(CharBuffer.wrap(id.toCharArray()));

//            List<WsOutbound> wsOutbounds = websocketPageHolder.getWsOutboundList();
//
//            for (WsOutbound wsOutbound1 : wsOutbounds) {
//                wsOutbound1.writeTextMessage(CharBuffer.wrap(id.toCharArray()));
//            }

        } catch ( IOException e) {
            e.printStackTrace();
        }*/
    }


    @RequestMapping("/requestPage.do")
    public ModelAndView requestSocketPage(String pageName){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(pageName);
        return modelAndView;
    }



}
