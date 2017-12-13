package com.jdk.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-12-09
 */
public class AsyncSocketClient {

    private static SocketChannel socketChannel;
    private Selector selector;
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
    private static ByteBuffer reciveBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        AsyncSocketClient asyncSocketClient = new AsyncSocketClient();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                userimport();
            }
        });

        t.start();
        asyncSocketClient.talk();

    }


    public AsyncSocketClient(){
        try {
            socketChannel = SocketChannel.open();
            InetAddress inetAddress = InetAddress.getLocalHost();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, 8080);
            socketChannel.connect(inetSocketAddress);
            socketChannel.configureBlocking(false);
            System.out.println("与服务器建立连接");
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void userimport(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String message = "";
            while ((message = bufferedReader.readLine()) != null) {
                synchronized (sendBuffer) {
                    sendBuffer.put(CharsetUtil.encode(message));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据到channel</div>
     *
     * @param selectionKey
     * @throws IOException
     */
    public void send(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
        System.out.println(CharsetUtil.decode(sendBuffer));
        synchronized (sendBuffer) {
            sendBuffer.flip();
            socketChannel.write(sendBuffer);
            sendBuffer.compact();
        }
    }


    /**
     * 从channel中获取数据</div>
     *
     * @param selectionKey
     * @throws IOException
     */
    public void receive(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        socketChannel.read(reciveBuffer);
        reciveBuffer.flip();
        String data = CharsetUtil.decode(reciveBuffer);
        if (data.indexOf("\n") == -1) {
            return;
        }
        String outputData = data.substring(0, data.indexOf("\n")+1);
        System.out.println("receive data" + outputData);
        ByteBuffer temp = CharsetUtil.encode(data);
        reciveBuffer.position(temp.limit());
        reciveBuffer.compact();
    }

    public void talk(){
        try {
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            System.out.println("sendBuffer.hasRemaining()----------" + sendBuffer.hasRemaining());

            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()) {
                        receive(selectionKey);
                    }

                    if (selectionKey.isWritable()) {
                        send(selectionKey);
                    }
                }


            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
