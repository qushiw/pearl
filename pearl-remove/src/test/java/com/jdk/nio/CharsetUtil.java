package com.jdk.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-12-09
 */
public class CharsetUtil {
    static Charset charset = Charset.forName("GBK");

    public static String decode(ByteBuffer byteBuffer){
        CharBuffer charBuffer = charset.decode(byteBuffer);
        return charBuffer.toString();
    }


    public static ByteBuffer encode(String data){
        return charset.encode(data);
    }



}
