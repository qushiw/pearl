package com.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.Buffer;

/**
*
* @author qushiwen
* @create 2017-11-21
* @version 1.0
**/
public class ByteBufTest {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.buffer(1024);

        byte[] data = new byte[1024];
        data[0] = 1;
        byteBuf.writeBytes(data);

        Buffer buffer = byteBuf.nioBuffer();



        ByteBuf dupBuf = byteBuf.copy();

        byte[] yuandata = new byte[1024];
        dupBuf.readBytes(yuandata);

        dupBuf.release();
        System.out.println(dupBuf);

        System.out.println(yuandata[0]);



        String a = "asdasd";

        String b = "asdasd";

        System.out.println(a.hashCode() + ":" + b.hashCode());

    }
}
