package com.training.spring.order.models;


public class SendMessage {

    private String dest;
    private String message;

    public String getDest() {
        return this.dest;
    }

    public void setDest(final String destParam) {
        this.dest = destParam;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String messageParam) {
        this.message = messageParam;
    }

    @Override
    public String toString() {
        return "SendMessage [dest=" + this.dest + ", message=" + this.message + "]";
    }


}
