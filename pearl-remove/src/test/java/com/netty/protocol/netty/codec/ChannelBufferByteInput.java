package com.netty.protocol.netty.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;

import java.io.IOException;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class ChannelBufferByteInput implements ByteInput {

    ByteBuf byteBuf;

    ChannelBufferByteInput(ByteBuf byteBuf){
        this.byteBuf = byteBuf;
    }

    @Override
    public int read() throws IOException {
        if (byteBuf.isReadable()) {
            return byteBuf.readByte() & 0xff;
        }
        return 0;
    }

    @Override
    public int read(byte[] bytes) throws IOException {

        return read(bytes, 0, bytes.length);

    }

    @Override
    public int read(byte[] bytes, int index, int length) throws IOException {
        int available = available();
        if (available == 0) {
            return -1;
        }

        length = Math.min(available, length);
        byteBuf.readBytes(bytes, index, length);
        return length;
    }

    @Override
    public int available() throws IOException {
        return byteBuf.readableBytes();
    }

    @Override
    public long skip(long bytes) throws IOException {
        int readAbleLength = byteBuf.readableBytes();
        if (readAbleLength < bytes) {
            bytes = readAbleLength;
        }
        byteBuf.readerIndex((int)(byteBuf.readerIndex() + bytes));
        return bytes;
    }

    @Override
    public void close() throws IOException {

    }
}
