package com.netty.test;

import io.netty.buffer.*;

/**
*
* @author qushiwen
* @create 2017-11-30
* @version 1.0
**/
public class ClientMain {

    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        ByteBuf byteBuf = Unpooled.buffer(1024);
//        compositeByteBuf.addComponents(2048, byteBuf, byteBuf);

        byte[] data = new byte[1];
        data[0] = 0x1f;

        ByteBuf bufData = Unpooled.wrappedBuffer(data);
        byte[] recoverByte = new byte[bufData.readableBytes()];
        bufData.readBytes(recoverByte);
        System.out.println(Integer.toHexString(recoverByte[0]));

        ByteBuf duplBuf = bufData.duplicate();
        duplBuf.copy();
        duplBuf.refCnt();

        PooledByteBufAllocator allocator = new PooledByteBufAllocator();
        ByteBuf byteBuf1 = allocator.buffer(1024);

        UnpooledByteBufAllocator unPooledByteBufAllocator = new UnpooledByteBufAllocator(false);
        unPooledByteBufAllocator.buffer(1024);





    }


}
