package com.web.common;

import com.web.common.webSocket.WebSocketSession;
import com.web.common.webSocket.WebsocketPageHolder;
import com.web.model.PageModel;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by jrqushiwen on 2017-09-28.
 */


public class TomcatWebSocketServer extends WebSocketServlet{

    private static final Logger logger = LoggerFactory.getLogger(TomcatWebSocketServer.class);

    private WebsocketPageHolder websocketPageHolder = WebsocketPageHolder.getInstance();

    private WebSocketSession webSocketSession;

    private WsOutbound wsOutbound;

    @Override
    protected StreamInbound createWebSocketInbound(String s, final HttpServletRequest httpServletRequest) {

        return new MessageInbound() {

            @Override
            protected void onClose(int status) {
                System.out.println("web socket close");
            }

            @Override
            protected void onOpen(WsOutbound outbound) {
                wsOutbound = outbound;
                webSocketSession = new WebSocketSession();
                webSocketSession.setWsOutbound(outbound);
//                websocketPageHolder.addWs(outbound);
                logger.info("web socket open");
            }

            @Override
            protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {
                logger.info("web socket Received : !" + byteBuffer.remaining());
            }

            @Override
            protected void onTextMessage(CharBuffer charBuffer) throws IOException {
                String id = charBuffer.toString();
                PageModel pageModel = new PageModel();
                pageModel.setWebSocketSession(webSocketSession);
                pageModel.setId(id);
                pageModel.setWsOutbound(wsOutbound);
//                pageModel.setUrl(httpServletRequest.getRequestURI());
                websocketPageHolder.register(id, pageModel);
            }
        };
    }
}
