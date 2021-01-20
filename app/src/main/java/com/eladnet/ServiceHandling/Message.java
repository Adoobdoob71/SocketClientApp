package com.eladnet.ServiceHandling;

public class Message {
    private String nickname;
    private String text;

    public Message(String nickname, String text){
        this.nickname = nickname;
        this.text = text;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
