package com.netty.protocol.netty.client;

import com.netty.protocol.netty.NettyConstant;
import com.netty.protocol.netty.codec.NettyMessageDecoder;
import com.netty.protocol.netty.codec.NettyMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class NettyClient {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    public void connect(int port, String host) throws Exception{
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            channelPipeline.addLast(new NettyMessageDecoder(1024*1024, 4, 4));
                            channelPipeline.addLast("NettyMessageEncoder", new NettyMessageEncoder());
                            channelPipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            channelPipeline.addLast("LoginAuthHandler", new LoginAuthReqHandler());
                            channelPipeline.addLast("HeartBeatReqHandler", new HeartBeatReqHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port),
                new InetSocketAddress(NettyConstant.localIp, NettyConstant.local_port)
            ).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            scheduledExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        connect(NettyConstant.port, NettyConstant.ip);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public static void main(String[] args) throws Exception{
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect(NettyConstant.port, NettyConstant.ip);
    }
}
