package com.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;


import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * @author qushiwen
 * @create 2017-11-18 20:24
 */
public class HttpFileServer {

    public static void main(String[] args) {
        new HttpFileServer().run(8080, "/logs/aa.txt");
    }


    public void run(final int port,final String url) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline channelPipeline = socketChannel.pipeline();
                            channelPipeline.addLast("http-decoder",new HttpRequestDecoder());
                            channelPipeline.addLast("http-aggregator",new HttpObjectAggregator(65535));
                            channelPipeline.addLast("http-encoder",new HttpResponseEncoder());
                            channelPipeline.addLast("http-chunked", new ChunkedWriteHandler());
                            channelPipeline.addLast("fileServerHandler",new HttpFileServerHandler(url));

                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind("192.168.1.100", port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        }
    }




    private class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

        private String url;
        public HttpFileServerHandler(String url){
            this.url = url;
        }



        @Override
        protected void messageReceived(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
            if (!fullHttpRequest.getDecoderResult().isSuccess()) {
                sendError(channelHandlerContext, BAD_REQUEST);
                return;
            }

            if (fullHttpRequest.getMethod() != HttpMethod.GET) {
                sendError(channelHandlerContext, METHOD_NOT_ALLOWED);
                return;
            }
            final String uri = fullHttpRequest.getUri();
            final String path = sanitizeUri(uri);
            if (path == null) {
                sendError(channelHandlerContext, FORBIDDEN);
                return;
            }

            File file = new File(path);
            if (file.isHidden() || !file.exists()) {
                sendError(channelHandlerContext, NOT_FOUND);
                return;
            }

            if (file.isDirectory()){
                if (uri.endsWith("/")){
                    sendListing(channelHandlerContext, file);
                } else {
                    sendRedirect(channelHandlerContext, uri + "/");
                }
                return;
            }

            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(file, "r");
            } catch (Exception e) {
                sendError(channelHandlerContext, NOT_FOUND);
                return;
            }

            long fileLength = randomAccessFile.length();
            HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            setContentLength(httpResponse, fileLength);
            setContentTypeHeader(httpResponse, file);
            if (isKeepAlive(fullHttpRequest)) {
                httpResponse.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }

            channelHandlerContext.writeAndFlush(httpResponse);
            ChannelFuture channelFuture = channelHandlerContext.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192)
                    , channelHandlerContext.newProgressivePromise());

            channelFuture.addListener(new ChannelProgressiveFutureListener() {
                @Override
                public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long progress, long total) throws Exception {
                    if (total < 0) { // total unknown
                        System.err.println("Transfer progress: " + progress);
                    } else {
                        System.err.println("Transfer progress: " + progress + " / " + total);
                    }
                }

                @Override
                public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
                    System.out.println("Transfer complete.");
                }
            });
            ChannelFuture lastContentFuture = channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            if (!isKeepAlive(fullHttpRequest)) {
                lastContentFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }

        private final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");


        private String sanitizeUri(String uri) {
            try {
                uri = URLDecoder.decode(uri, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                try {
                    uri = URLDecoder.decode(uri, "ISO-8859-1");
                } catch (UnsupportedEncodingException e1) {
                    throw new Error();
                }
            }
            if (!uri.startsWith(url)) {
                return null;
            }
            if (!uri.startsWith("/")) {
                return null;
            }
            uri = uri.replace('/', File.separatorChar);
            if (uri.contains(File.separator + '.')
                    || uri.contains('.' + File.separator) || uri.startsWith(".")
                    || uri.endsWith(".") || INSECURE_URI.matcher(uri).matches()) {
                return null;
            }

            System.out.println("System.getProperty(\"user.dir\")" + System.getProperty("user.dir"));
            return "D:\\" + File.separator + uri;
        }

        private void sendError(ChannelHandlerContext channelHandlerContext, HttpResponseStatus httpResponseStatus) {

            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, httpResponseStatus
                    , Unpooled.copiedBuffer("Failure:" + httpResponseStatus.toString()+"\r\n", CharsetUtil.UTF_8));
            fullHttpResponse.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
            channelHandlerContext.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);

        }

        private void setContentTypeHeader(HttpResponse response, File file) {
            MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
            response.headers().set(CONTENT_TYPE, mimeTypesMap.getContentType(file.getPath()));
        }

        private final Pattern ALLOWED_FILE_NAME = Pattern
                .compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

        private void sendListing(ChannelHandlerContext ctx, File dir) {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK);
            response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
            StringBuilder buf = new StringBuilder();
            String dirPath = dir.getPath();
            buf.append("<!DOCTYPE html>\r\n");
            buf.append("<html><head><title>");
            buf.append(dirPath);
            buf.append(" 目录：");
            buf.append("</title></head><body>\r\n");
            buf.append("<h3>");
            buf.append(dirPath).append(" 目录：");
            buf.append("</h3>\r\n");
            buf.append("<ul>");
            buf.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
            for (File f : dir.listFiles()) {
                if (f.isHidden() || !f.canRead()) {
                    continue;
                }
                String name = f.getName();
                if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                    continue;
                }
                buf.append("<li>链接：<a href=\"");
                buf.append(name);
                buf.append("\">");
                buf.append(name);
                buf.append("</a></li>\r\n");
            }
            buf.append("</ul></body></html>\r\n");
            ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
            response.content().writeBytes(buffer);
            buffer.release();
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }

        private void sendRedirect(ChannelHandlerContext ctx, String newUri) {
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, FOUND);
            response.headers().set(LOCATION, newUri);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }






}
