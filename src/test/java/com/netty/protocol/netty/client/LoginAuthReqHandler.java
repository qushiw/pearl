package com.netty.protocol.netty.client;

import com.netty.protocol.netty.Header;
import com.netty.protocol.netty.MessageType;
import com.netty.protocol.netty.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage nettyMessage = (NettyMessage)msg;
        if (nettyMessage.getHeader() != null && nettyMessage.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            byte loginResult = (byte)nettyMessage.getBody();
            if (loginResult != (byte)0) {
                System.out.println("握手失败");
                ctx.close();
            } else {
                System.out.println("握手成功");
                ctx.fireChannelRead(msg);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildLoginReq() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        nettyMessage.setHeader(header);
        return nettyMessage;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
