package com.eladnet.ServiceHandling;

import java.io.Serializable;
import java.net.Socket;

public class UserSocket implements Serializable {
    private String nickname;
    private String description;
    private transient Socket socket;

    public UserSocket(String nickname, String description){
        this.nickname = nickname;
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
