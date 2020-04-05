package com.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author qushiwen
 * @create 2017-11-18 11:39
 */
public class NettyTimeClient {

    public static void main(String[] args) {

        new NettyTimeClient().connect(8000, "127.0.0.1");

    }


    public void connect(int port, String host) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder", new LineBasedFrameDecoder(1024));

                            socketChannel.pipeline().addLast(new TimerClientHandler());
                        }
                    });


            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }


    private class TimerClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

        private int counter;
//        private final ByteBuf firstMessage;
        private byte[] req;

        public TimerClientHandler(){
//            byte[] req = "QUERY TIME ORDER".getBytes();
//            firstMessage = Unpooled.buffer(req.length);
//            firstMessage.writeBytes(req);

            req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
        }


        @Override
        protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            String body = new String(bytes, "UTF-8");
            System.out.println("NOW is : " + body + ",the counter is" + (++counter));
        }


        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {

            ByteBuf message = null;
            for (int i=0; i<100; i++) {
                message = Unpooled.buffer(req.length);
                message.writeBytes(req);
                ctx.writeAndFlush(message);
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}
