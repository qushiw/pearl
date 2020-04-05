package com.netty.protocol.netty.codec;

import com.netty.protocol.netty.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * @author qushiwen
 * @create 2017-11-19 11:44
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage nettyMessage, ByteBuf sendBuf)
            throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new RuntimeException("the encode message is null");
        }

        sendBuf.writeInt((nettyMessage.getHeader().getCrcCode()));
        sendBuf.writeInt((nettyMessage.getHeader().getLength()));//先预留，随后更新成全部消息的长度
        sendBuf.writeLong((nettyMessage.getHeader().getSessionID()));
        sendBuf.writeByte((nettyMessage.getHeader().getType()));
        sendBuf.writeByte((nettyMessage.getHeader().getPriority()));
        sendBuf.writeInt((nettyMessage.getHeader().getAttachment().size())); //attachment key-value 的个数

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : nettyMessage.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);//keyArray的长度
            sendBuf.writeBytes(keyArray); // keyArray的值
            value = param.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }

        key = null;
        keyArray = null;
        value = null;
        if (nettyMessage.getBody() != null) {
            marshallingEncoder.encode(nettyMessage.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
        }

        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }

}
