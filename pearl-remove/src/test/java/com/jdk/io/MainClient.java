package com.jdk.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
*
* @author qushiwen
* @create 2017-12-02
* @version 1.0
**/
public class MainClient {

    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.bind(new InetSocketAddress(8080));
        } catch (IOException e) {
            e.printStackTrace();
        }

        socket.isBound();

    }


}
