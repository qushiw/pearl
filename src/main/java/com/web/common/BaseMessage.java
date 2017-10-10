package com.web.common;

import java.io.Serializable;

/**
 * @author qushiwen on 2017-09-17.
 */
public class BaseMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    private String messageId;

    private String messageContext;


    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }

    public String getMessageContext() {
        return messageContext;
    }


    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }


    public String getMessageId() {
        return messageId;
    }
}
