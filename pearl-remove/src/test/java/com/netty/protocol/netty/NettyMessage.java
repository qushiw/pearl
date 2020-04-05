package com.netty.protocol.netty;

/**
 * @author qushiwen
 * @create 2017-11-19 11:34
 */
public class NettyMessage {
    private Header header;
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "header" + header.toString();
    }
}
