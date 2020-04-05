package com.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-12-09
 */
public class AsyncSocketServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private Object object = new Object();


    public static void main(String[] args) {
        final AsyncSocketServer asyncSocketServer = new AsyncSocketServer();
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
                try {
                    asyncSocketServer.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            }
//        });
//        thread.start();
        asyncSocketServer.workTask();

    }



    public AsyncSocketServer(){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void accept() throws IOException {
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("InetAddress : " + socketChannel.socket().getInetAddress());
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer  = ByteBuffer.allocate(1024);
            synchronized (object) {
                selector.wakeup();
                socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, byteBuffer);
            }
        }

    }


    /**
     * 将channel中的数据读取出来 放到bytebuffer中</div>
     *
     * @param selectionKey
     * @throws IOException
     */
    private void receive(SelectionKey selectionKey) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer)selectionKey.attachment();
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(readBuffer);
        readBuffer.flip();

        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.put(readBuffer);
    }



    /**
     * 将数据写入管道 发送客户端</div>
     *
     * @param selectionKey
     * @throws IOException
     */
    private void send(SelectionKey selectionKey) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        byteBuffer.flip();
        String data = CharsetUtil.decode(byteBuffer);
        if (data.indexOf("\r\n") == -1) {
            return;
        }

        String outputData = data.substring(0, data.indexOf("\n") + 1);
        System.out.println("output data" + outputData);

        ByteBuffer outputBuffer = CharsetUtil.encode("echo" + data);
        while (outputBuffer.hasRemaining()) {
            socketChannel.write(outputBuffer);
        }

        ByteBuffer temp = CharsetUtil.encode(data);
        byteBuffer.position(temp.limit());
        byteBuffer.compact();

    }


    private void workTask() {
        while (true) {

            SelectionKey selectionKey = null;
            try {
                synchronized (object) {

                }
                int n = selector.select();
                if (n <= 0) {
                    continue;
                }

                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeySet.iterator();

                while (it.hasNext()) {
                    selectionKey = it.next();
                    it.remove();
                    if (selectionKey.isReadable()) {
                        receive(selectionKey);
                    }

                    if (selectionKey.isWritable()) {
                        send(selectionKey);
                    }
                }
            } catch (IOException e) {
                if (selectionKey != null) {
                    selectionKey.cancel();
                    try {
                        selectionKey.channel().close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
