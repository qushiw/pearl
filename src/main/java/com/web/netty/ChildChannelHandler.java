package com.web.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by jrqushiwen on 2017-09-17.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    private SocketServerMsgRequestHandler socketServerMsgRequestHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast("decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
        channelPipeline.addLast("encoder", new ObjectEncoder());
        channelPipeline.addLast(socketServerMsgRequestHandler);
    }


    public void setSocketServerMsgRequestHandler(SocketServerMsgRequestHandler socketServerMsgRequestHandler) {
        this.socketServerMsgRequestHandler = socketServerMsgRequestHandler;
    }
}
