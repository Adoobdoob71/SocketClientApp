package com.eladnet.ServiceHandling.Threads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.eladnet.Chat;
import com.eladnet.ServiceHandling.UserSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class ClientThread extends Thread {

    private UserSocket user;
    private static Socket socket;
    private Context context;
    public static BufferedReader input;
    public static PrintWriter output;
    public ClientThread(UserSocket user, Socket socket, Context context){
        this.user = user;
        ClientThread.socket = socket;
        this.context = context;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public UserSocket getUser() {
        return user;
    }

    public void setUser(UserSocket user) {
        this.user = user;
    }

    public void sendChoiceToServer(String key) throws IOException{
        output.println(key);
    }

    public void handleInputFromServer() throws IOException{
        String nickname = input.readLine();
        Intent intent = new Intent(context, Chat.class);
        context.startActivity(intent);
    }
    @Override
    public void run() {
        try {
            handleInputFromServer();
            run();
        }catch (IOException exception){
            Log.e("ERROR", Objects.requireNonNull(exception.getMessage()));
        }
    }
}
