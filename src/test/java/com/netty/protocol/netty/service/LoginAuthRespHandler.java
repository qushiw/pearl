package com.netty.protocol.netty.service;

import com.netty.protocol.netty.Header;
import com.netty.protocol.netty.MessageType;
import com.netty.protocol.netty.NettyMessage;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    private ConcurrentMap<String, Boolean> loginCheck = new ConcurrentHashMap<String, Boolean>();
    private String[] writeList = {"127.0.0.1", "192.168.1.100"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        NettyMessage nettyMessage = (NettyMessage)msg;

        if (nettyMessage.getHeader() != null
                && nettyMessage.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage message = null;
            if (loginCheck.containsKey(nodeIndex)) {
                message = buildRspnMessage((byte)-1);
            } else {
                InetSocketAddress inetSocketAddress = (InetSocketAddress)ctx.channel().remoteAddress();
                String ip = inetSocketAddress.getAddress().getHostAddress();
                boolean isOk = false;
                for (String wip : writeList) {
                    if (wip.equals(ip)) {
                        isOk = true;
                        break;
                    }
                }
                if (isOk) {
                    loginCheck.put(ip, true);
                }

                message = isOk ? buildRspnMessage((byte)0) : buildRspnMessage((byte)-1);
                System.out.println("the login response is : " + message);

                ctx.writeAndFlush(message);
            }
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        loginCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireChannelRead(cause);
    }

    public NettyMessage buildRspnMessage(byte result){
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        nettyMessage.setHeader(header);
        nettyMessage.setBody(result);
        return nettyMessage;
    }
}
