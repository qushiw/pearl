package com.netty.protocol.netty.service;

import com.netty.protocol.netty.NettyConstant;
import com.netty.protocol.netty.codec.NettyMessageDecoder;
import com.netty.protocol.netty.codec.NettyMessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class NettyServer {

    public void bind() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        channelPipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        channelPipeline.addLast("NettyMessageEncoder", new NettyMessageEncoder());
                        channelPipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                        channelPipeline.addLast("LoginAuthRespHandler", new LoginAuthRespHandler());
                        channelPipeline.addLast("HeartBeatRespHandler", new HeartBeatRespHandler());
                    }
                });


        serverBootstrap.bind(NettyConstant.ip, NettyConstant.port).sync();
        System.out.println("netty start service ok");

    }

    public static void main(String[] args) {
        try {
            new NettyServer().bind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
