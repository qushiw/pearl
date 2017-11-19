package com.netty.protocol.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19 12:03
 */

@ChannelHandler.Sharable
public class MarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        marshaller = MarshallingCodecFactory.buildMarshalling();
    }


    protected void encode(Object msg, ByteBuf out) throws IOException {
        try {
            int length = out.writerIndex();
            out.writeBytes(LENGTH_PLACEHOLDER);

            // 将message序列化放入out中
            ChannelBufferByteOutput channelBufferOutputStream = new ChannelBufferByteOutput(out);
            marshaller.start(channelBufferOutputStream);
            marshaller.writeObject(msg);
            marshaller.flush();

            out.setInt(length, out.writerIndex() - length - 4); //attachment value 的长度
        } finally {
            marshaller.close();
        }
    }

}
