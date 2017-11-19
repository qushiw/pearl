package com.netty.protocol.netty.client;

import com.netty.protocol.netty.Header;
import com.netty.protocol.netty.MessageType;
import com.netty.protocol.netty.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    private volatile ScheduledFuture scheduledFuture;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage nettyMessage = (NettyMessage)msg;
        if (nettyMessage.getHeader() != null && nettyMessage.getHeader().getType() == MessageType.LOGIN_RESP.value()) {
            scheduledFuture = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        } else if (nettyMessage.getHeader() != null && nettyMessage.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
            System.out.println("----------- client recive heartBeat message" + nettyMessage);
        } else {
            ctx.fireChannelRead(msg);
        }

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }

        ctx.fireChannelRead(cause);
    }

    private class HeartBeatTask implements Runnable {

        private ChannelHandlerContext ctx;
        public HeartBeatTask(final ChannelHandlerContext ctx){
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage nettyMessage = buildHeartMessage();
            System.out.println("client sent heartbeat message to Server" + nettyMessage);
            ctx.writeAndFlush(nettyMessage);
        }


        private NettyMessage buildHeartMessage(){
            NettyMessage nettyMessage = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.value());
            nettyMessage.setHeader(header);
            return nettyMessage;
        }
    }
}
