package com.netty.protocol.netty.codec;


import com.netty.protocol.netty.Header;
import com.netty.protocol.netty.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qushiwen
 * @version 1.0
 * @create 2017-11-19
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    MarshallingDecoder marshallingDecoder;
    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws Exception {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        marshallingDecoder = new MarshallingDecoder();
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        ByteBuf frame = (ByteBuf)super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());
        int size = frame.readInt();
        if (size > 0) {
            Map<String, Object> attach = new HashMap<String, Object>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i=0; i<size; i++) {
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                frame.readBytes(keyArray);
                key = new String(keyArray, "utf-8");
                attach.put(key, marshallingDecoder.decode(frame));
            }
            key = null;
            keyArray = null;
            header.setAttachment(attach);
        }

        if (frame.readableBytes() > 4) {
            nettyMessage.setBody(marshallingDecoder.decode(frame));
        }
        nettyMessage.setHeader(header);
        return nettyMessage;
    }
}
