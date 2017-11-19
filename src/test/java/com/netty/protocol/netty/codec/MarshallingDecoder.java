package com.netty.protocol.netty.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class MarshallingDecoder {

    private Unmarshaller unmarshaller;

    public MarshallingDecoder() throws Exception {
        unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    protected Object decode(ByteBuf byteBuf) throws IOException, ClassNotFoundException {
        int objectSize = byteBuf.readInt();
        ByteBuf buf = byteBuf.slice(byteBuf.readerIndex(), objectSize); //返回一块区域，修改会影响整部区域
        ByteInput byteInput = new ChannelBufferByteInput(buf);
        try {
            unmarshaller.start(byteInput);
            Object object = unmarshaller.readObject();
            unmarshaller.finish();
            byteBuf.readerIndex(byteBuf.readerIndex() + objectSize);
            return object;
        } finally {
            unmarshaller.close();
        }

    }


}
