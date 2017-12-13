package com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.util.Date;

/**
 * @author qushiwen
 * @create 2017-11-18 11:06
 */
public class NettyTimeServer {


    public static void main(String[] args) throws Exception {
        int port = 8000;
        new NettyTimeServer().bind(port);
    }


    public void bind(int port) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

//            channelFuture.addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                    System.out.println("--------------------------- ces");
//                }
//            });
        } catch (Exception e) {

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //配置属性
//            socketChannel.config().setAllocator( ByteBufAllocator4Cds.getBufallocator() );

            ChannelPipeline channelPipeline = socketChannel.pipeline();

            channelPipeline.addLast("decoder", new LineBasedFrameDecoder(1024));
//            socketChannel.pipeline().addLast("encoder", new MySQLEncoder(ByteBufAllocator4Cds.isDirectbuffer()));
            channelPipeline.addLast(new TimeServerHandler());
        }
    }

    private class TimeServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
        private int counter;

        @Override
        protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            String body = new String(bytes, "UTF-8").substring(0, (bytes.length-System.getProperty("line.separator").length()));

            System.out.println("the time server receive order : " + body + ", the counter is" + (++counter));

            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            currentTime = currentTime + System.getProperty("line.separator");

            ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
            channelHandlerContext.write(resp);
        }


        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }



}
