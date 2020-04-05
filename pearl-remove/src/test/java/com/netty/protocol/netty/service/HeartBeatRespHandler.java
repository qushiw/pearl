package com.netty.protocol.netty.service;

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
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage nettyMessage = (NettyMessage)msg;
        NettyMessage respMessage = null;
        if (nettyMessage.getHeader() != null && nettyMessage.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("----------------------recive client heartbeat message-------------");
            respMessage = buildHeartRepnMessage((byte)0);
            System.out.println("-------------- send service message ------------" + respMessage);
            ctx.writeAndFlush(respMessage);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    public NettyMessage buildHeartRepnMessage(byte result){
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        nettyMessage.setHeader(header);
        nettyMessage.setBody(result);
        return nettyMessage;
    }
}
