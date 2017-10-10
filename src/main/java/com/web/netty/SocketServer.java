package com.web.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by jrqushiwen on 2017-09-17.
 */
public class SocketServer {

    private ChildChannelHandler childChannelHandler;
    private EventLoopGroup eventLoopGroup;
    private EventLoopGroup workerloopGroup;

    private Channel channel;

    public void init(){
        eventLoopGroup = new NioEventLoopGroup();
        workerloopGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(eventLoopGroup, workerloopGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(childChannelHandler);
            channel = serverBootstrap.bind(8088).sync().channel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
            workerloopGroup.shutdownGracefully();
        }
    }


    private void close(){
        channel.closeFuture().awaitUninterruptibly(5000);
        if (eventLoopGroup != null) {
            eventLoopGroup.shutdownGracefully();
        }
        if (workerloopGroup != null) {
            workerloopGroup.shutdownGracefully();
        }
    }


    public void setChildChannelHandler(ChildChannelHandler childChannelHandler) {
        this.childChannelHandler = childChannelHandler;
    }

    public ChildChannelHandler getChildChannelHandler() {
        return childChannelHandler;
    }
}
