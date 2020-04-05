package web.netty;

import com.web.common.BaseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by jrqushiwen on 2017-09-17.
 */
public class SocketServerMsgRequestHandler extends SimpleChannelInboundHandler<BaseMessage>{


    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMessage baseMessage) throws Exception {
        String context = baseMessage.getMessageContext();
        ByteBuf byteBuf = Unpooled.copiedBuffer(context.getBytes());
        channelHandlerContext.writeAndFlush(byteBuf);
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        super.handlerAdded(ctx);
    }
}
