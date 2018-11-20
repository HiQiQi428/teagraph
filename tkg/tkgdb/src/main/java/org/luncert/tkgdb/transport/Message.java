package org.luncert.tkgdb.transport;

public class Message {

    private String uri;
    private String content;

    public Message(String uri, String content) {
        this.uri = uri;
        this.content = content;
    }

    public String uri() {
        return uri;
    }

    public String content() {
        return content;
    }

}